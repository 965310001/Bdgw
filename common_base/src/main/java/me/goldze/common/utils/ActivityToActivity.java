package me.goldze.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import me.goldze.common.base.bean.BaseBean;
import me.goldze.common.constants.ARouterConfig;

/**
 * @author GuoFeng
 * @date :2019/1/17 10:01
 * @description: Activity跳转
 */
public final class ActivityToActivity {
    /****************************************** 封装 start ************************************/
    public static void toWebView(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        toActivity(ARouterConfig.WEBVIEWACTIVITY, "URL", url);
    }

    /**
     * ARouter跳转Activity
     *
     * @param url 目标Activity Url
     */
    public static void toActivity(String url) {
        toActivity(url, null);
    }

    public static void toActivity(String url, String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        toActivity(url, map);
    }


    /**
     * 普通Activity之间跳转
     *
     * @param activity activity
     * @param clazz    目标activity
     */
    public static void toActivity(Context activity, Class<? extends Activity> clazz) {
        toActivity(activity, clazz, null);
    }

    /* public static void toActivity(Context activity, String url) {
     *//*toActivity(activity, clazz, null);*//*
        ARouter.getInstance()
                .build(url)
                .withString("URL", url)
                .navigation(activity, new LoginInterceptor());
    }
*/

    /**
     * 普通Activity之间跳转
     *
     * @param activity activity
     * @param clazz    目标activity
     * @param params   参数
     */
    public static void toActivity(Context activity, Class<? extends Activity> clazz,
                                  Map<String, ?> params) {
        Intent intent = new Intent(activity, clazz);
        if (params != null) {
            for (Map.Entry<String, ?> entry : params.entrySet()) {
                String key = entry.getKey();
                Object value = params.get(key);
                if (value instanceof String) {
                    intent.putExtra(key, (String) value);
                } else if (value instanceof Boolean) {
                    intent.putExtra(key, (boolean) value);
                } else if (value instanceof Integer) {
                    intent.putExtra(key, (int) value);
                } else if (value instanceof Float) {
                    intent.putExtra(key, (float) value);
                } else if (value instanceof Double) {
                    intent.putExtra(key, (double) value);
                } else if (value instanceof Long) {
                    intent.putExtra(key, (long) value);
                } else if (value instanceof Short) {
                    intent.putExtra(key, (short) value);
                } else if (value instanceof BaseBean) {
                    intent.putExtra(key, (BaseBean) value);
                } else if (value instanceof ArrayList) {
                    intent.putExtra(key, (ArrayList) value);
                } else if (value instanceof HashMap) {
                    intent.putExtra(key, (HashMap) value);
                }
            }
        }
        activity.startActivity(intent);
    }


    /********************************************返回数据的activity**************************************/
    public static void toActivityForResult(Activity activity, Class<? extends Activity> clazz,
                                           Map<String, ?> params, int requestCode) {
        Intent intent = new Intent(activity, clazz);
        if (params != null) {
            for (Map.Entry<String, ?> entry : params.entrySet()) {
                String key = entry.getKey();
                Object value = params.get(key);
                if (value instanceof String) {
                    intent.putExtra(key, (String) value);
                } else if (value instanceof Boolean) {
                    intent.putExtra(key, (boolean) value);
                } else if (value instanceof Integer) {
                    intent.putExtra(key, (int) value);
                } else if (value instanceof Float) {
                    intent.putExtra(key, (float) value);
                } else if (value instanceof Double) {
                    intent.putExtra(key, (double) value);
                } else if (value instanceof Long) {
                    intent.putExtra(key, (long) value);
                } else if (value instanceof Short) {
                    intent.putExtra(key, (short) value);
                } else if (value instanceof BaseBean) {
                    intent.putExtra(key, (BaseBean) value);
                } else if (value instanceof ArrayList) {
                    intent.putExtra(key, (ArrayList) value);
                } else if (value instanceof HashMap) {
                    intent.putExtra(key, (HashMap) value);
                }
            }
        }
        activity.startActivityForResult(intent, requestCode);
    }

    public static void setResult(Activity activity, Intent data) {
        activity.setResult(Activity.RESULT_OK, data);
        activity.finish();
    }
    /******************************************** end **************************************/


    /**
     * ARouter跳转Activity
     *
     * @param url    目标Activity Url
     * @param params 参数
     */
    public static void toActivity(String url, Map<String, ?> params) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Postcard postcard = ARouter.getInstance()
                .build(url);
        if (params != null) {
            for (Map.Entry<String, ?> entry : params.entrySet()) {
                String key = entry.getKey();
                Object value = params.get(key);
                if (value instanceof String) {
                    postcard.withString(key, (String) value);
                } else if (value instanceof Boolean) {
                    postcard.withBoolean(key, (boolean) value);
                } else if (value instanceof Integer) {
                    postcard.withInt(key, (int) value);
                } else if (value instanceof Float) {
                    postcard.withFloat(key, (float) value);
                } else if (value instanceof Double) {
                    postcard.withDouble(key, (double) value);
                } else if (value instanceof Long) {
                    postcard.withLong(key, (long) value);
                } else if (value instanceof Short) {
                    postcard.withShort(key, (short) value);
                } else if (value instanceof BaseBean) {
                    postcard.withSerializable(key, (BaseBean) value);
                } else if (value instanceof ArrayList) {
                    postcard.withSerializable(key, (ArrayList) value);
                } else if (value instanceof HashMap) {
                    postcard.withSerializable(key, (HashMap) value);
                }
            }
        }
        KLog.i("----------------------");
        postcard.navigation();
    }


    /************************************************************** 封装 end **************************************/

//    /**
//     * 通过模板跳转Activity
//     *
//     * @param
//     * @param
//     */
//    @SuppressWarnings("unchecked")
////    public static void toActivity(Context activity, Template template) {
////        switch (template.getType()) {
////            case 0:
////                //跳转Activity
////                toActivity(activity, template.getClazz(), template.getParams());
////                break;
////            case 1:
////                //跳转其他业务模块
////                toActivity(template.getUrl(), template.getParams());
////                break;
////            case 2:
////                //跳转普通WebView
////                toWebView(activity, template.getUrl());
////                break;
////        }
////    }
//
//    /**
//     * 跳转普通WebView
//     *
//     * @param activity activity
//     * @param url      目标Url
//     */
////    public static void toWebView(Context activity, String url) {
//////        WebViewActivity.actionStart(activity, url);
////        ARouter.getInstance()
////                .build(ARouterConfig.WEBVIEWACTIVITY)
////                .withString("URL", url)
////                .navigation();
////    }
//
//    public static void toWebView(String url) {
//        if (TextUtils.isEmpty(url)) {
//            return;
//        }
//        ARouter.getInstance()
//                .build(ARouterConfig.WEBVIEWACTIVITY)
//                .withString("URL", url)
//                .navigation();
//    }
//
//
//    /**
//     * 普通Activity之间跳转
//     *
//     * @param activity activity
//     * @param clazz    目标activity
//     */
//    public static void toActivity(Context activity, Class<? extends Activity> clazz) {
//        toActivity(activity, clazz, null);
//    }
//
//    /* public static void toActivity(Context activity, String url) {
//     *//*toActivity(activity, clazz, null);*//*
//        ARouter.getInstance()
//                .build(url)
//                .withString("URL", url)
//                .navigation(activity, new LoginInterceptor());
//    }
//*/
//
//    /**
//     * 普通Activity之间跳转
//     *
//     * @param activity activity
//     * @param clazz    目标activity
//     * @param params   参数
//     */
//    public static void toActivity(Context activity, Class<? extends Activity> clazz,
//                                  Map<String, ?> params) {
//        Intent intent = new Intent(activity, clazz);
//        if (params != null) {
//            for (Map.Entry<String, ?> entry : params.entrySet()) {
//                String key = entry.getKey();
//                Object value = params.get(key);
//                if (value instanceof String) {
//                    intent.putExtra(key, (String) value);
//                } else if (value instanceof Boolean) {
//                    intent.putExtra(key, (boolean) value);
//                } else if (value instanceof Integer) {
//                    intent.putExtra(key, (int) value);
//                } else if (value instanceof Float) {
//                    intent.putExtra(key, (float) value);
//                } else if (value instanceof Double) {
//                    intent.putExtra(key, (double) value);
//                } else if (value instanceof Long) {
//                    intent.putExtra(key, (long) value);
//                } else if (value instanceof Short) {
//                    intent.putExtra(key, (short) value);
//                } else if (value instanceof BaseBean) {
//                    intent.putExtra(key, (BaseBean) value);
//                } else if (value instanceof ArrayList) {
//                    intent.putExtra(key, (ArrayList) value);
//                } else if (value instanceof HashMap) {
//                    intent.putExtra(key, (HashMap) value);
//                }
//            }
//        }
//        activity.startActivity(intent);
//    }
//
//
//    /********************************************返回数据的activity**************************************/
//    public static void toActivityForResult(Activity activity, Class<? extends Activity> clazz,
//                                           Map<String, ?> params, int requestCode) {
//        Intent intent = new Intent(activity, clazz);
//        if (params != null) {
//            for (Map.Entry<String, ?> entry : params.entrySet()) {
//                String key = entry.getKey();
//                Object value = params.get(key);
//                if (value instanceof String) {
//                    intent.putExtra(key, (String) value);
//                } else if (value instanceof Boolean) {
//                    intent.putExtra(key, (boolean) value);
//                } else if (value instanceof Integer) {
//                    intent.putExtra(key, (int) value);
//                } else if (value instanceof Float) {
//                    intent.putExtra(key, (float) value);
//                } else if (value instanceof Double) {
//                    intent.putExtra(key, (double) value);
//                } else if (value instanceof Long) {
//                    intent.putExtra(key, (long) value);
//                } else if (value instanceof Short) {
//                    intent.putExtra(key, (short) value);
//                } else if (value instanceof BaseBean) {
//                    intent.putExtra(key, (BaseBean) value);
//                } else if (value instanceof ArrayList) {
//                    intent.putExtra(key, (ArrayList) value);
//                } else if (value instanceof HashMap) {
//                    intent.putExtra(key, (HashMap) value);
//                }
//            }
//        }
//        activity.startActivityForResult(intent, requestCode);
//    }
//
//    public static void setResult(Activity activity, Intent data) {
//        activity.setResult(Activity.RESULT_OK, data);
//        activity.finish();
//    }
//    /******************************************** end **************************************/
//
//    /**
//     * ARouter跳转Activity
//     *
//     * @param url 目标Activity Url
//     */
//    public static void toActivity(String url) {
//        toActivity(url, null);
//    }
//
//    public static void toActivity(String url, String key, Object value) {
//        Map<String, Object> map = new HashMap<>();
//        map.put(key, value);
//        toActivity(url, map);
//    }
//
//    /**
//     * ARouter跳转Activity
//     *
//     * @param url    目标Activity Url
//     * @param params 参数
//     */
//    public static void toActivity(String url, Map<String, ?> params) {
//        if (TextUtils.isEmpty(url)) {
//            return;
//        }
//        Postcard postcard = ARouter.getInstance()
//                .build(url);
//        if (params != null) {
//            for (Map.Entry<String, ?> entry : params.entrySet()) {
//                String key = entry.getKey();
//                Object value = params.get(key);
//                if (value instanceof String) {
//                    postcard.withString(key, (String) value);
//                } else if (value instanceof Boolean) {
//                    postcard.withBoolean(key, (boolean) value);
//                } else if (value instanceof Integer) {
//                    postcard.withInt(key, (int) value);
//                } else if (value instanceof Float) {
//                    postcard.withFloat(key, (float) value);
//                } else if (value instanceof Double) {
//                    postcard.withDouble(key, (double) value);
//                } else if (value instanceof Long) {
//                    postcard.withLong(key, (long) value);
//                } else if (value instanceof Short) {
//                    postcard.withShort(key, (short) value);
//                } else if (value instanceof BaseBean) {
//                    postcard.withSerializable(key, (BaseBean) value);
//                } else if (value instanceof ArrayList) {
//                    postcard.withSerializable(key, (ArrayList) value);
//                } else if (value instanceof HashMap) {
//                    postcard.withSerializable(key, (HashMap) value);
//                }
//            }
//        }
//        postcard.navigation();
//    }
}

