package com.bdgw.cc.ui.home;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.ui.Constants;

import java.util.ArrayList;
import java.util.List;

import me.goldze.common.activity.HorizontalTabActivity;
import me.goldze.common.base.mvvm.base.BaseFragment;
import me.goldze.common.constants.ARouterConfig;

/**
 * 红包
 */
@Route(path = ARouterConfig.home.REDACTIVITY, extras = ARouterConfig.LOGIN_NEEDED)
public class RedActivity extends HorizontalTabActivity {

    @Override
    protected String[] getTabTitles() {
        return Constants.Red.RED_TABBAR_TITLE;
    }

    @Override
    protected List<BaseFragment> getTabFragments() {
        List<BaseFragment> list = new ArrayList<>();
        for (String status : Constants.Red.RED_TABBAR_STATUS) {
            list.add(RedItemFragment.newInstance(status));
        }
        return list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        setText("我的红包");
    }
}