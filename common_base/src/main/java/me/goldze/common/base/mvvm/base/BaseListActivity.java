package me.goldze.common.base.mvvm.base;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.R;
import me.goldze.common.R2;
import me.goldze.common.base.core.banner.BannerList;
import me.goldze.common.utils.Utils;

/**
 * @author GuoFeng
 * @date : 2019/1/26 13:56
 * @description:
 */
public abstract class BaseListActivity extends BaseActivity implements OnRefreshListener {

    @BindView(R2.id.recycler_view)
    protected TRecyclerView mRecyclerView;

    @BindView(R2.id.rl_title_bar)
    protected RelativeLayout mTitleBar;

    @BindView(R2.id.tv_title)
    protected TextView mTitle;

    @BindView(R2.id.iv_back)
    protected ImageView ivBack;

    protected RecyclerView.LayoutManager layoutManager;

    protected DelegateAdapter adapter;

    protected String lastId = null;

    protected boolean isLoadMore = true;

    protected boolean isLoading = true;

    protected boolean isRefresh = false;

    protected ItemData oldItems;

    protected ItemData newItems;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list;
    }


    @Override
    protected void initViews(Bundle savedInstanceState) {
        oldItems = new ItemData();
        newItems = new ItemData();
        adapter = createAdapter();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(createLayoutManager());
        mRecyclerView.addOnRefreshListener(this);
        mRecyclerView.addOnScrollStateListener(new OnScrollStateListener() {
            @Override
            public void onScrollStateChanged(int state1) {
                if (state1 == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(Utils.getApplication()).resumeRequests();
                } else {
                    Glide.with(Utils.getApplication()).pauseRequests();
                }
            }
        });
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration
                .VERTICAL));

        onRefresh();
    }

    @Override
    public void onLoadMore() {
        isLoadMore = true;
        getLoadMoreData();
    }

    @Override
    public void onRefresh() {
        lastId = null;
        isRefresh = true;
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

    protected abstract DelegateAdapter createAdapter();

    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(this);
    }

    protected void setTitle(String titleName) {
        mTitleBar.setVisibility(View.VISIBLE);
        mTitle.setText(titleName);
    }

    protected void showBack() {
        ivBack.setVisibility(View.VISIBLE);
    }


    /**
     * 获取更多网络数据t
     */
    protected void getLoadMoreData() {
    }

    /**
     * 获取网络数据
     */
    protected void getRemoteData() {
    }

    @OnClick(R2.id.iv_back)
    public void onClick() {
        finish();
    }
}
