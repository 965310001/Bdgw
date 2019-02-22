package com.bdgw.cc.ui.me;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.ui.ApiData;
import com.bdgw.cc.ui.adapter.AdapterPool;
import com.trecyclerview.adapter.DelegateAdapter;

import me.goldze.common.base.mvvm.base.BaseListActivity;
import me.goldze.common.constants.ARouterConfig;

@Route(path = ARouterConfig.Me.EXTRACTLISTACTIVITY, extras = ARouterConfig.LOGIN_NEEDED)
public class ExtractListActivity extends BaseListActivity {

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        setTitle("提现记录");
        showBack();
    }

    @Override
    protected void initData() {
        super.initData();
        showSuccess();
        getRemoteData();
    }

    @Override
    protected void getRemoteData() {
        super.getRemoteData();

        setData(ApiData.getExtractInfoList());
    }
    @Override
    protected void getLoadMoreData() {
        super.getLoadMoreData();
        getRemoteData();
    }

    @Override
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getExtractListAdapter(this).build();
    }

}
