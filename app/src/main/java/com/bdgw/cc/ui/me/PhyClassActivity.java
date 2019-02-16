package com.bdgw.cc.ui.me;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;

import me.goldze.common.base.mvvm.base.test.BaseActivity;
import me.goldze.common.constants.ARouterConfig;

/**
 * 系统分类
 */
@Route(path = ARouterConfig.Me.PHYCLASSACTIVITY)
public class PhyClassActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_phy_class;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showSuccess();
        setTitle("系统分类");
    }
}
