package com.bdgw.cc.ui.home;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.ui.Constants;

import java.util.ArrayList;
import java.util.List;

import me.goldze.common.activity.HorizontalTabActivity;
import me.goldze.common.base.mvvm.base.BaseFragment;
import me.goldze.common.constants.ARouterConfig;

@Route(path = ARouterConfig.home.SEARCHLISTACTIVITY)
public class SearchListActivity extends HorizontalTabActivity {

    @Override
    protected String[] getTabTitles() {
        return Constants.Home.SEARCH_TITLE;
    }

    @Override
    protected List<BaseFragment> getTabFragments() {
        List<BaseFragment> list = new ArrayList<>();
        for (String status : Constants.Home.SEARCH_TABBAR_STATUS) {
            list.add(SearchItemFragment.newInstance(status));
        }
        return list;
    }
}
