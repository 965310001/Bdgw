package com.bdgw.cc.ui.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;

import me.goldze.common.constants.ARouterConfig;

/**
 * 优惠活动
 */
@Route(path = ARouterConfig.home.PREFERENTIALACTIVITY)
public class PreferentialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferential);
    }
}