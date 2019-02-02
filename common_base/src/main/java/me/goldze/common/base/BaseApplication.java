package me.goldze.common.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.socks.library.KLog;
import com.tqzhang.stateview.core.LoadState;

import me.goldze.common.BuildConfig;
import me.goldze.common.base.mvvm.stateview.ErrorState;
import me.goldze.common.base.mvvm.stateview.LoadingState;
import me.goldze.common.manage.AppManager;
import me.goldze.common.utils.Utils;

/**
 * @author GuoFeng
 * @date :2019/1/15 10:42
 * @description:
 */
public abstract class BaseApplication extends Application implements Runnable {

    @Override
    public void onCreate() {
        super.onCreate();

        setApplication(this, this);

        init();
    }

    protected void init() {
        //ARouter
        if (BuildConfig.DEBUG) {
            ARouter.openLog();  // 打印日志
            ARouter.openDebug(); // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(Utils.getApplication());// 尽可能早，推荐在Application中初始化

        //KLog
        KLog.init(BuildConfig.DEBUG, "TAG11");

        //CrashManage
        if (!BuildConfig.DEBUG) {
          /*  CrashHandlerManage.getInstance()
                    .init(getApplicationContext());*/
        }

        //注册 LoadState
        new LoadState.Builder()
                .register(new ErrorState())
                .register(new LoadingState())
                .setDefaultCallback(LoadingState.class)
                .build();
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex分包方法 必须最先初始化
        MultiDex.install(this);
    }

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();

        //退出应用
        AppManager.getInstance().AppExit();
    }

    /**
     * @param application
     * @param runnable    用于初始化第三方库，防止耗时初始化操作
     */
    public static synchronized void setApplication(@NonNull Application application, @NonNull Runnable runnable) {
        runnable.run();
        setApplication(application);
    }

    /**
     * 当主工程没有继承BaseApplication时，可以使用setApplication方法初始化BaseApplication
     *
     * @param application
     */
    public static synchronized void setApplication(@NonNull Application application) {
        Utils.init(application);
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                AppManager.getInstance().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                AppManager.getInstance().removeActivity(activity);
            }
        });
    }


}