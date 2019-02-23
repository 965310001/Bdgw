package com.bdgw.cc;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

import me.goldze.common.base.mvvm.base.test.BaseActivity;
import me.goldze.common.constants.ARouterConfig;

@Route(path = ARouterConfig.REGISTERACTIVITY2)
public class RegisterActivity2 extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register2;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showSuccess();
        setTitle("注册");
    }
}
