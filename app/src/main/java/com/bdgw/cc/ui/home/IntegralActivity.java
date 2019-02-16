package com.bdgw.cc.ui.home;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;
import com.socks.library.KLog;

import me.goldze.common.base.mvvm.base.test.BaseActivity;
import me.goldze.common.constants.ARouterConfig;

/**
 * 积分商城
 */
@Route(path = ARouterConfig.home.INTEGRALACTIVITY)
public class IntegralActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_integral;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showSuccess();
        setTitle("积分商城");
        KLog.i("积分商城");
    }

}
