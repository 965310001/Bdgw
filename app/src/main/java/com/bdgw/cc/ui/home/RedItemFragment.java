package com.bdgw.cc.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bdgw.cc.ui.ApiData;
import com.bdgw.cc.ui.Constants;
import com.bdgw.cc.ui.adapter.AdapterPool;
import com.bdgw.cc.ui.home.bean.RedItemInfo;
import com.bdgw.cc.ui.home.vm.RedItemViewModel;
import com.socks.library.KLog;
import com.trecyclerview.adapter.DelegateAdapter;
import com.trecyclerview.listener.OnItemClickListener;

import me.goldze.common.base.mvvm.base.BaseListFragment;

/**
 * 红包对应Fragment
 */
public class RedItemFragment extends BaseListFragment<RedItemViewModel> implements OnItemClickListener {

    private static final String STATUS = "status";

    public RedItemFragment() {
    }

    public static RedItemFragment newInstance(String status) {
        RedItemFragment fragment = new RedItemFragment();
        Bundle args = new Bundle();
        args.putString(STATUS, status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        loadManager.showSuccess();
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();

        getRemoteData();
    }

    @Override
    protected void getRemoteData() {
        super.getRemoteData();

        setData(ApiData.getRedList());
    }

    @Override
    protected void getLoadMoreData() {
        super.getLoadMoreData();

        getRemoteData();
    }

    @Override
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getRedAdapter(getActivity())
                .setOnItemClickListener(this)
                .build();
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    @Override
    protected Object getStateEventKey() {
        return Constants.Red.EVENT_KEY_RED_LIST[1];
    }

    @Override
    public void onItemClick(View view, int i, Object o) {
        KLog.i(((RedItemInfo.CashgiftsBean) o).getName() + " " + ((RedItemInfo.CashgiftsBean) o).getCondition());

        // TODO: 2019/1/22 跳转activity

    }
}
