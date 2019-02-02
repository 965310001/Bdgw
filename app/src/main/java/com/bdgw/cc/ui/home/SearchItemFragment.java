package com.bdgw.cc.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bdgw.cc.ui.ApiRepo;
import com.bdgw.cc.ui.adapter.AdapterPool;
import com.bdgw.cc.ui.home.bean.SearchInfo;
import com.bdgw.cc.ui.home.vm.OrderItemViewModel;
import com.socks.library.KLog;
import com.trecyclerview.adapter.DelegateAdapter;
import com.trecyclerview.listener.OnItemClickListener;

import java.util.HashMap;
import java.util.Map;

import me.goldze.common.base.mvvm.base.BaseListFragment;
import me.goldze.common.base.mvvm.stateview.LoadingState;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.http.rx.RxSubscriber;
import me.goldze.common.utils.ActivityToActivity;

/**
 * 搜索列表
 */
public class SearchItemFragment extends BaseListFragment<OrderItemViewModel> implements OnItemClickListener {

    private static final String STATUS = "status";

    int page = 1;

    public static SearchItemFragment newInstance(String status) {
        SearchItemFragment fragment = new SearchItemFragment();
        Bundle args = new Bundle();
        args.putString(STATUS, status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();

        getRemoteData();
    }

    @Override
    protected void getRemoteData() {
        super.getRemoteData();
        mViewModel.getOrderData(page,
                getArguments().getString(STATUS));

        loadManager.showStateView(LoadingState.class);

        ApiRepo.getSearchListData("sab").subscribeWith(new RxSubscriber<SearchInfo>() {
            @Override
            public void onSuccess(SearchInfo searchInfo) {
              /*  if (orderInfo.getPaged().getMore() == 1) {
                    page++;
                }*/
                KLog.i(searchInfo.toString());
                loadManager.showSuccess();
                setData(searchInfo.getData().getDatas());
            }

            @Override
            public void onFailure(String msg) {
                KLog.i(msg);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
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
        return AdapterPool.newInstance().getSearchListAdapter(getActivity())
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
    public void onItemClick(View view, int i, Object o) {
        SearchInfo.DataBean.DatasBean datasBean = (SearchInfo.DataBean.DatasBean) o;
        KLog.i(datasBean.getChapterName());


        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(datasBean.getId()));
        ActivityToActivity.toActivity(ARouterConfig.home.ShoppingDetailsActivity, map);
    }

}
