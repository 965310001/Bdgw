package com.bdgw.cc;

import android.content.Context;

import com.xuexiang.xui.XUI;

import me.goldze.common.base.BaseApplication;
import me.goldze.common.utils.Utils;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * @author GuoFeng
 * @date : 2019/1/18 17:33
 * @description:
 */
public class MyApplication extends BaseApplication {

    @Override
    public void run() {
        //xui
        XUI.init(Utils.getApplication());
        XUI.debug(BuildConfig.DEBUG);  //开启UI框架调试日志
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        //注入字体
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
