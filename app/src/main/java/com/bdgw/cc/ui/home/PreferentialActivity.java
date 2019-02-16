package com.bdgw.cc.ui.home;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;
import com.socks.library.KLog;

import me.goldze.common.base.mvvm.base.test.BaseActivity;
import me.goldze.common.constants.ARouterConfig;

/**
 * 优惠活动
 */
@Route(path = ARouterConfig.home.PREFERENTIALACTIVITY)
public class PreferentialActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_preferential;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showSuccess();
        setTitle("优惠活动");
        KLog.i("优惠活动");
    }
}