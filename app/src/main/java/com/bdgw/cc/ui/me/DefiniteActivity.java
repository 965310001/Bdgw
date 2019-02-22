package com.bdgw.cc.ui.me;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.ui.Constants;

import java.util.ArrayList;
import java.util.List;

import me.goldze.common.activity.HorizontalTabActivity;
import me.goldze.common.base.mvvm.base.BaseFragment;
import me.goldze.common.constants.ARouterConfig;

@Route(path = ARouterConfig.Me.DEFINITEACTIVITY, extras = ARouterConfig.LOGIN_NEEDED)
public class DefiniteActivity extends HorizontalTabActivity {

    @Override
    protected String[] getTabTitles() {
        return Constants.Me.DEFINITE_TITLE;
    }

    @Override
    protected List<BaseFragment> getTabFragments() {
        List<BaseFragment> list = new ArrayList<>();
        for (String status : Constants.Me.DEFINITE_STATUS) {
            list.add(DefiniteItemFragment.newInstance(status));
        }
        return list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        setText("资金明细");
    }
}
