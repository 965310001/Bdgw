package com.bdgw.cc.ui.home;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;

import me.goldze.common.base.mvvm.base.BaseActivity;
import me.goldze.common.constants.ARouterConfig;

@Route(path = ARouterConfig.Shopping.SHOPPINGACTIVITY)
public class ShoppingActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopping;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        loadManager.showSuccess();
    }
}
