package com.bdgw.cc.ui.me;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bdgw.cc.ui.ApiData;
import com.bdgw.cc.ui.adapter.AdapterPool;
import com.bdgw.cc.ui.home.vm.OrderItemViewModel;
import com.trecyclerview.adapter.DelegateAdapter;

import me.goldze.common.base.mvvm.base.BaseListFragment;

public class DefiniteItemFragment extends BaseListFragment<OrderItemViewModel> {

    private static final String STATUS = "status";
    private String status;

    public DefiniteItemFragment() {
    }

    public static DefiniteItemFragment newInstance(String status) {
        DefiniteItemFragment fragment = new DefiniteItemFragment();
        Bundle args = new Bundle();
        args.putString(STATUS, status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            status = getArguments().getString(STATUS);
        }
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        getRemoteData();
    }

    @Override
    protected void getRemoteData() {
        showSuccess();

        setData(ApiData.getDefinite(status).getBalances());

//        status = getArguments().getString(STATUS);
//        OrderInfo orderInfo = ApiData.getOrderInfo();
//        if (orderInfo.getPaged().getMore() == 1) {
//            page++;
//        }
//        if (Integer.parseInt(status) == 0) {
//            setData(orderInfo.getOrders());
//        } else {
//            oldItems.clear();
//            for (OrderInfo.OrdersBean ordersBean : orderInfo.getOrders()) {
//                if (ordersBean.getStatus() == Integer.parseInt(status)) {
//                    oldItems.add(ordersBean);
//                }
//            }
//            if (oldItems.size() > 0) {
//                setData(oldItems);
//            } else {
//                showStateView(ErrorState.class);
//            }
//        }
    }

    @Override
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getDefiniteAdapter(getContext()).build();
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    @Override
    protected Object getStateEventKey() {
        return "";
    }
}
