package com.bdgw.cc.ui.me;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;

import me.goldze.common.base.mvvm.base.test.BaseActivity;
import me.goldze.common.constants.ARouterConfig;

/**
 * 关于我们
 */
@Route(path = ARouterConfig.Me.ABOUTUSACTIVITY)
public class AboutUsActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showSuccess();
    }
}
