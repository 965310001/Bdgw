package com.bdgw.cc.ui.me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;

import me.goldze.common.constants.ARouterConfig;

/**
 * 我的推广二维码
 */
@Route(path = ARouterConfig.Me.DIMENSIONALCODESACTIVITY)
public class DimensionalCodesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dimensional_codes);
    }
}
