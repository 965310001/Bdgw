package com.bdgw.cc.ui.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;

import me.goldze.common.constants.ARouterConfig;

/**
 * 积分商城
 */
@Route(path = ARouterConfig.home.INTEGRALACTIVITY)
public class IntegralActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral);
    }
}
