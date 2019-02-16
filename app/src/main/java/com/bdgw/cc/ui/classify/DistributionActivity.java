package com.bdgw.cc.ui.classify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bdgw.cc.ui.ApiData;
import com.bdgw.cc.ui.adapter.AdapterPool;
import com.bdgw.cc.ui.classify.bean.DistributionInfo;
import com.trecyclerview.adapter.DelegateAdapter;
import com.trecyclerview.listener.OnItemClickListener;

import me.goldze.common.base.mvvm.base.BaseListActivity;
import me.goldze.common.utils.ActivityToActivity;

//@Route(path = ARouterConfig.classify.DISTRIBUTIONACTIVITY)
public class DistributionActivity extends BaseListActivity implements OnItemClickListener {

    private DistributionInfo.VendorsBean data;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        setTitle("配送方式");
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

        setData(ApiData.getDistributionInfo().getVendors());
    }

    @Override
    protected void getLoadMoreData() {
        super.getLoadMoreData();
        getRemoteData();
    }

    @Override
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getDistributionAdapter(this)
                .setOnItemClickListener(this).build();
    }

    @Override
    public void onItemClick(View view, int i, Object object) {
        if (data != null) {
            data.setSelect(false);
        }
        data = (DistributionInfo.VendorsBean) object;
        data.setSelect(true);
        adapter.notifyDataSetChanged();

        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        ActivityToActivity.setResult(this, intent);
    }
}
