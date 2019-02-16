package com.bdgw.cc.ui.me;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;
import com.socks.library.KLog;

import me.goldze.common.base.mvvm.base.test.BaseActivity;
import me.goldze.common.constants.ARouterConfig;

/**
 * 我的推荐
 */
@Route(path = ARouterConfig.Me.RECOMMENDATIONACTIVITY)
public class RecommendationActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recommendation;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showSuccess();
        setTitle("我的推荐");

        KLog.i("我的推荐");
    }

}