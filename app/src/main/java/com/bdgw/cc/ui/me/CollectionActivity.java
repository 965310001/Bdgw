package com.bdgw.cc.ui.me;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.ui.ApiData;
import com.bdgw.cc.ui.adapter.AdapterPool;
import com.bdgw.cc.ui.shopping.bean.GoodsInfo;
import com.trecyclerview.adapter.DelegateAdapter;
import com.trecyclerview.listener.OnItemClickListener;

import java.util.HashMap;
import java.util.Map;

import me.goldze.common.base.mvvm.base.BaseListActivity;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ActivityToActivity;

/**
 * 我的收藏
 */

@Route(path = ARouterConfig.Me.COLLECTIONACTIVITY)
public class CollectionActivity extends BaseListActivity implements OnItemClickListener {

    @Override
    protected void initData() {
        super.initData();
        loadManager.showSuccess();
        getRemoteData();
    }

    @Override
    protected void getRemoteData() {
        super.getRemoteData();

        setData(ApiData.getGoodsInfos());
    }

    @Override
    protected void getLoadMoreData() {
        super.getLoadMoreData();
        getRemoteData();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        setTitle("我的收藏");
        showBack();
    }

    @Override
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getProductsAdapter(this)
                .setOnItemClickListener(this)
                .build();
    }

    @Override
    public void onItemClick(View view, int i, Object object) {
        GoodsInfo data = (GoodsInfo) object;
        Map<String, String> map = new HashMap<>();
        map.put("id", data.getGoodsId());
        ActivityToActivity.toActivity(ARouterConfig.home.SHOPPINGDETAILSACTIVITY, map);
    }
}
