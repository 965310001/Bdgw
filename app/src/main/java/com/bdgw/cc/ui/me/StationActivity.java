package com.bdgw.cc.ui.me;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;

import me.goldze.common.base.mvvm.base.test.BaseActivity;
import me.goldze.common.constants.ARouterConfig;

/**
 * 站内公告
 */
@Route(path = ARouterConfig.Me.STATIONACTIVITY)
public class StationActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_station;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showSuccess();
        setTitle("站内公告");
    }
}
