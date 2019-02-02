package me.goldze.common.base.mvvm.base;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.trecyclerview.TRecyclerView;
import com.trecyclerview.adapter.DelegateAdapter;
import com.trecyclerview.adapter.ItemData;
import com.trecyclerview.listener.OnRefreshListener;
import com.trecyclerview.listener.OnScrollStateListener;

import java.util.Collection;
import java.util.List;

import me.goldze.common.R;
import me.goldze.common.base.core.banner.BannerList;
import me.goldze.common.base.mvvm.AbsLifecycleFragment;
import me.goldze.common.base.mvvm.AbsViewModel;

/**
 * @author GuoFeng
 * @date :2019/1/17 17:06
 * @description:
 */
public abstract class BaseListFragment<T extends AbsViewModel> extends AbsLifecycleFragment<T> implements OnRefreshListener {

    protected TRecyclerView mRecyclerView;

    protected RelativeLayout mTitleBar;

    protected TextView mTitle;

    protected RecyclerView.LayoutManager layoutManager;

    protected DelegateAdapter adapter;

    protected String lastId = null;

    protected boolean isLoadMore = true;

    protected boolean isLoading = true;

    protected boolean isRefresh = false;

    protected ItemData oldItems;

    protected ItemData newItems;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_list;
    }

    @Override
    public int getContentResId() {
        return R.id.content_layout;
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        mRecyclerView = getViewById(R.id.recycler_view);
        mTitleBar = getViewById(R.id.rl_title_bar);
        mTitle = getViewById(R.id.tv_title);
        oldItems = new ItemData();
        newItems = new ItemData();
        adapter = createAdapter();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(createLayoutManager());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        mRecyclerView.addOnRefreshListener(this);
        mRecyclerView.addOnScrollStateListener(new OnScrollStateListener() {
            @Override
            public void onScrollStateChanged(int state1) {
                if (state1 == RecyclerView.SCROLL_STATE_IDLE) {
                    if (activity != null) {
                        Glide.with(activity).resumeRequests();
                    }
                } else {
                    if (activity != null) {
                        Glide.with(activity).pauseRequests();
                    }
                }
            }
        });
    }

    @Override
    protected void lazyLoad() {
        isLoadMore = false;
        getRemoteData();
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        onRefresh();
    }

    protected void setData(List<?> collection) {
        if (isLoadMore) {
            onLoadMoreSuccess(collection);
        } else {
            onRefreshSuccess(collection);
        }
    }

    @Override
    public void onRefresh() {
        lastId = null;
        isRefresh = true;
        isLoadMore = false;
        getRemoteData();
    }

    @Override
    public void onLoadMore() {
        isLoadMore = true;
        getLoadMoreData();
    }

    protected void setBannerData(BannerList headAdList) {
        newItems.add(headAdList);
    }

    protected void onRefreshSuccess(Collection<?> collection) {
        newItems.clear();
        newItems.addAll(collection);
        oldItems.clear();
        oldItems.addAll(newItems);
        if (collection.size() < 20) {
            mRecyclerView.refreshComplete(oldItems, true);
        } else {
            mRecyclerView.refreshComplete(oldItems, false);
        }
        isRefresh = false;
    }

    protected void onLoadMoreSuccess(List<?> collection) {
        isLoading = true;
        isLoadMore = false;
        oldItems.addAll(collection);
        if (collection.size() < 20) {
            mRecyclerView.loadMoreComplete(collection, true);
        } else {
            mRecyclerView.loadMoreComplete(collection, false);
        }
    }

    /**
     * adapter
     *
     * @return DelegateAdapter
     */
    protected abstract DelegateAdapter createAdapter();

    /**
     * LayoutManager
     *
     * @return LayoutManager
     */
    protected abstract RecyclerView.LayoutManager createLayoutManager();

    protected void setTitle(String titleName) {
        mTitleBar.setVisibility(View.VISIBLE);
        mTitle.setText(titleName);
    }


    /**
     * 获取更多网络数据t
     */
    protected void getLoadMoreData() {

    }

}
