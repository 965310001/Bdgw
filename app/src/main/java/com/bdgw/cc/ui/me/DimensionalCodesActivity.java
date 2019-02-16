package com.bdgw.cc.ui.me;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;
import com.socks.library.KLog;

import me.goldze.common.base.mvvm.base.test.BaseActivity;
import me.goldze.common.constants.ARouterConfig;

/**
 * 我的推广二维码
 */
@Route(path = ARouterConfig.Me.DIMENSIONALCODESACTIVITY)
public class DimensionalCodesActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dimensional_codes;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showSuccess();
        setTitle("我的推广二维码");

        KLog.i("我的推广二维码");
    }
}
