package com.bdgw.cc.ui.me;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;

import me.goldze.common.base.mvvm.base.test.BaseActivity;
import me.goldze.common.constants.ARouterConfig;

/**
 * 客服中心
 */
@Route(path = ARouterConfig.Me.CUSTOMERSERVICEACTIVITY)
public class CustomerServiceActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_service;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showSuccess();
    }
}
