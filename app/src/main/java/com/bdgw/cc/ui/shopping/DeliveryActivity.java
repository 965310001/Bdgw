package com.bdgw.cc.ui.shopping;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bdgw.cc.ui.ApiData;
import com.bdgw.cc.ui.adapter.AdapterPool;
import com.socks.library.KLog;
import com.trecyclerview.adapter.DelegateAdapter;

import me.goldze.common.base.mvvm.base.BaseListActivity;
import me.goldze.common.constants.ARouterConfig;

/**
 * 查看物流
 */

@Route(path = ARouterConfig.Shopping.DELIVERYACTIVITY)
public class DeliveryActivity extends BaseListActivity {

    @Autowired
    int id;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        ARouter.getInstance().inject(this);

        loadManager.showSuccess();
        KLog.i("物流" + id);

        showBack();
        setTitle("物流跟踪");
    }

    @Override
    protected void getRemoteData() {
        super.getRemoteData();

        setData(ApiData.getDeliverInfo().getTraces());
    }

    @Override
    protected void getLoadMoreData() {
        super.getLoadMoreData();
        getRemoteData();
    }

    @Override
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getDeliverInfoAdapter(this)
                .build();
    }
}
