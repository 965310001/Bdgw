package com.bdgw.cc.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bdgw.cc.ui.ApiRepo;
import com.bdgw.cc.ui.Constants;
import com.bdgw.cc.ui.adapter.AdapterPool;
import com.bdgw.cc.ui.home.bean.OrderInfo;
import com.bdgw.cc.ui.home.vm.OrderItemViewModel;
import com.socks.library.KLog;
import com.trecyclerview.adapter.DelegateAdapter;
import com.trecyclerview.listener.OnItemClickListener;

import java.util.HashMap;
import java.util.Map;

import me.goldze.common.base.mvvm.base.BaseListFragment;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.http.rx.RxSubscriber;
import me.goldze.common.utils.ActivityToActivity;

/**
 * 订单对应Fragment
 */
public class OrderItemFragment extends BaseListFragment<OrderItemViewModel> implements OnItemClickListener {

    private static final String STATUS = "status";

//    private int page = 1;

//    private String status;

    public OrderItemFragment() {
    }

    public static OrderItemFragment newInstance(String status) {
        OrderItemFragment fragment = new OrderItemFragment();
        Bundle args = new Bundle();
        args.putString(STATUS, status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected boolean isItemDecoration() {
        return false;
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        getRemoteData();
    }

//    @Override
//    public void initView(Bundle state) {
//        super.initView(state);
//        dataObserver();
//    }

//    @Override
//    protected void dataObserver() {
//        super.dataObserver();
//
////        registerObserver(Constants.Order.EVENT_KEY_ORDER_LIST[0], status, OrderInfo.class)
////                .observe(this, new Observer<OrderInfo>() {
////                    @Override
////                    public void onChanged(@Nullable OrderInfo orderInfo) {
////                        if (orderInfo.getPaged().getMore() == 1) {
////                            page++;
////                        }
////                        if (Integer.parseInt(status) == 0) {
////                            setData(orderInfo.getOrders());
////                        } else {
////                            for (OrderInfo.OrdersBean ordersBean : orderInfo.getOrders()) {
////                                if (ordersBean.getStatus() == Integer.parseInt(status)) {
////                                    oldItems.add(ordersBean);
////                                }
////                            }
////                            if (oldItems.size() > 0) {
////                                setData(oldItems);
////                            } else {
////                                showStateView(ErrorState.class);
////                            }
////                        }
////
////                    }
////                });
//    }

    @Override
    protected void getRemoteData() {
        showLoading();
        /*String status = getArguments().getString(STATUS);*/
        ApiRepo.getOrderListData(page, getArguments().getString(STATUS)).subscribeWith(new RxSubscriber<OrderInfo>() {
            @Override
            public void onSuccess(OrderInfo data) {
                showSuccess();
                if (null != data.getOrders() && data.getOrders().size() > 0) {
                    setData(data.getOrders());
                }
            }

            @Override
            public void onFailure(String msg) {
                KLog.i(msg);
                showErrorState();
            }
        });

//        OrderInfo orderInfo = ApiData.getOrderInfo();
//        if (orderInfo.getPaged().getMore() == 1) {
////            page++;
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
//                showErrorState();
//            }
//        }
    }


    @Override
    protected void getLoadMoreData() {
        getRemoteData();
    }

    @Override
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getOrderAdapter(getActivity())
                .setOnItemClickListener(this)
                .build();
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    @Override
    protected Object getStateEventKey() {
        return Constants.Order.EVENT_KEY_ORDER_LIST[1];
    }

    @Override
    public void onItemClick(View view, int i, Object o) {
        Map<String, String> map = new HashMap<>();
//        map.put("id", "" + ((OrderInfo.OrdersBean) o).getGoodsid());
        KLog.i("订单详情");

        ActivityToActivity.toActivity(ARouterConfig.Shopping.ORDERDETAILSACTIVITY, "id", 100);


    }


}
