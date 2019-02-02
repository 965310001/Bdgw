package com.bdgw.cc.ui.home;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bdgw.cc.ui.Constants;

import java.util.ArrayList;
import java.util.List;

import me.goldze.common.activity.HorizontalTabActivity;
import me.goldze.common.base.mvvm.base.BaseFragment;
import me.goldze.common.constants.ARouterConfig;

/**
 * 订单
 */
@Route(path = ARouterConfig.home.ORDERACTIVITY)
public class OrderActivity extends HorizontalTabActivity {

    @Autowired
    int position = 0;

    @Override
    protected String[] getTabTitles() {
        return Constants.Order.ORDER_TABBAR_TITLE;
    }

    @Override
    protected List<BaseFragment> getTabFragments() {
        List<BaseFragment> list = new ArrayList<>();
        for (String status : Constants.Order.ORDER_TABBAR_STATUS) {
            list.add(OrderItemFragment.newInstance(status));
        }
        return list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        ARouter.getInstance().inject(this);

        setText("我的订单");

        onPageSelected(position);



    }


}
