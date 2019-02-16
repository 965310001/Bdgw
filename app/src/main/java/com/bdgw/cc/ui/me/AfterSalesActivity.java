package com.bdgw.cc.ui.me;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.ui.ApiData;
import com.bdgw.cc.ui.adapter.AdapterPool;
import com.trecyclerview.adapter.DelegateAdapter;

import me.goldze.common.base.mvvm.base.BaseListActivity;
import me.goldze.common.constants.ARouterConfig;

/**
 * 可售后商品
 */
@Route(path = ARouterConfig.Me.AFTERSALESACTIVITY)
public class AfterSalesActivity extends BaseListActivity {

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        showSuccess();

        showBack();
        setTitle("可售后商品");
    }

    @Override
    protected void getRemoteData() {
        super.getRemoteData();

        // TODO: 2019/2/2 获取数据
        setData(ApiData.getAfterSalesList());
    }

    @Override
    protected void getLoadMoreData() {
        super.getLoadMoreData();
        getRemoteData();
    }

    @Override
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getAfterSalesAdapter(this, false)
                .build();
    }
}
