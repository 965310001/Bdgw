package com.bdgw.cc.ui.classify;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bdgw.cc.ui.ApiData;
import com.bdgw.cc.ui.adapter.AdapterPool;
import com.bdgw.cc.ui.home.vm.OrderItemViewModel;
import com.bdgw.cc.ui.shopping.bean.GoodsInfo;
import com.trecyclerview.adapter.DelegateAdapter;
import com.trecyclerview.listener.OnItemClickListener;

import java.util.HashMap;
import java.util.Map;

import me.goldze.common.base.mvvm.base.BaseListFragment;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ActivityToActivity;

public class ProductsItemFragment extends BaseListFragment<OrderItemViewModel> implements OnItemClickListener {

   private final static String STATUS = "status";

    public ProductsItemFragment() {
    }

    public static ProductsItemFragment newInstance(String status) {
        ProductsItemFragment fragment = new ProductsItemFragment();
        Bundle args = new Bundle();
        args.putString(STATUS, status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        showSuccess();
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();

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
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getProductsAdapter(getActivity())
                .setOnItemClickListener(this)
                .build();
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    @Override
    protected Object getStateEventKey() {
        return "";
    }

    @Override
    public void onItemClick(View view, int i, Object object) {
        GoodsInfo data = (GoodsInfo) object;
        Map<String, String> map = new HashMap<>();
        map.put("id", ""+data.getGoodsId());
        ActivityToActivity.toActivity(ARouterConfig.home.SHOPPINGDETAILSACTIVITY, map);
    }
}