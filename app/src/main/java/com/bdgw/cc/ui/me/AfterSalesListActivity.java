package com.bdgw.cc.ui.me;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bdgw.cc.ui.ApiData;
import com.bdgw.cc.ui.adapter.AdapterPool;
import com.bdgw.cc.ui.me.bean.AfterSalesInfo;
import com.trecyclerview.adapter.DelegateAdapter;
import com.trecyclerview.listener.OnItemClickListener;

import me.goldze.common.base.mvvm.base.BaseListActivity;
import me.goldze.common.constants.ARouterConfig;

/**
 * 售后列表
 */
@Route(path = ARouterConfig.Me.AFTERSALESLISTACTIVITY)
public class AfterSalesListActivity extends BaseListActivity implements OnItemClickListener {

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        ARouter.getInstance().inject(this);

        showSuccess();

        showBack();
        setTitle("售后列表");
    }

    @Override
    protected void getRemoteData() {
        super.getRemoteData();

        /*获取数据*/
        setData(ApiData.getAfterSalesList());
    }

    @Override
    protected void getLoadMoreData() {
        super.getLoadMoreData();
        getRemoteData();
    }

    @Override
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getAfterSalesListAdapter(this)
                .setOnItemClickListener(this)
                .build();
    }

    @Override
    public void onItemClick(View view, int i, Object object) {
        //售后列表
        AfterSalesInfo data = (AfterSalesInfo) object;
//        ActivityToActivity.toActivity(ARouterConfig.Me.AFTERSALESDETAILSDCTIVITY, "data", data);
    }
}
