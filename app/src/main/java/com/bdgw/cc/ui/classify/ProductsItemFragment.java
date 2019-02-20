package com.bdgw.cc.ui.classify;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bdgw.cc.ui.ApiRepo;
import com.bdgw.cc.ui.adapter.AdapterPool;
import com.bdgw.cc.ui.home.vm.OrderItemViewModel;
import com.bdgw.cc.ui.shopping.bean.GoodsInfo;
import com.bdgw.cc.ui.shopping.bean.GoodsListInfo;
import com.socks.library.KLog;
import com.trecyclerview.adapter.DelegateAdapter;
import com.trecyclerview.listener.OnItemClickListener;

import me.goldze.common.base.mvvm.base.BaseListFragment;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.http.rx.RxSubscriber;
import me.goldze.common.utils.ActivityToActivity;

public class ProductsItemFragment extends BaseListFragment<OrderItemViewModel> implements OnItemClickListener {

    private final static String STATUS = "status";
    private final static String SORTKEY = "sortKey";

    public ProductsItemFragment() {
    }

    public static ProductsItemFragment newInstance(String status, int sortKey) {
        ProductsItemFragment fragment = new ProductsItemFragment();
        Bundle args = new Bundle();
        args.putString(STATUS, status);
        args.putInt(SORTKEY, sortKey);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        showLoading();
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();

        page = 1;
        getRemoteData();
    }

    /*long page = 1;*/

    @Override
    protected void getRemoteData() {
        super.getRemoteData();

        ApiRepo.getProductList(Long.parseLong(getArguments().getString(STATUS)), page, getArguments().getInt(SORTKEY)).subscribeWith(new RxSubscriber<GoodsListInfo>() {
            @Override
            public void onSuccess(GoodsListInfo data) {
                showSuccess();
                setData(data.getGoodsInfos());
            }

            @Override
            public void onFailure(String msg) {
                KLog.i(msg);
                showErrorState();
            }
        });
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
        if (object instanceof GoodsInfo) {
            GoodsInfo data = (GoodsInfo) object;
            ActivityToActivity.toActivity(ARouterConfig.home.SHOPPINGDETAILSACTIVITY, "id", String.valueOf(data.getGoodsId()));
        }
    }
}