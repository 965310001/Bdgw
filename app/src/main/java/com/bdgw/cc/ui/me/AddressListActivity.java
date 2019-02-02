package com.bdgw.cc.ui.me;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;
import com.bdgw.cc.ui.ApiData;
import com.bdgw.cc.ui.Constants;
import com.bdgw.cc.ui.adapter.AdapterPool;
import com.bdgw.cc.ui.me.bean.AddressInfo;
import com.bumptech.glide.Glide;
import com.socks.library.KLog;
import com.trecyclerview.TRecyclerView;
import com.trecyclerview.adapter.DelegateAdapter;
import com.trecyclerview.adapter.ItemData;
import com.trecyclerview.listener.OnItemClickListener;
import com.trecyclerview.listener.OnRefreshListener;
import com.trecyclerview.listener.OnScrollStateListener;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.event.LiveBus;
import me.goldze.common.base.mvvm.base.BaseActivity;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ActivityToActivity;
import me.goldze.common.utils.ToastUtils;
import me.goldze.common.utils.Utils;
import me.goldze.common.widget.dialog.MaterialDialogUtils;

/**
 * 管理收货地址
 */
@Route(path = ARouterConfig.Me.ADDRESSLISTACTIVITY)
public class AddressListActivity extends BaseActivity implements OnRefreshListener, OnItemClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.recycler_view)
    TRecyclerView mRecyclerView;

    private DelegateAdapter adapter;

    private ItemData oldItems, newItems;
    private boolean isRefresh = false,isLoading;
    private boolean isLoadMore = true;

    private int status = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        loadManager.showSuccess();

        Intent intent = getIntent();
        if (intent != null) {
            status = getIntent().getIntExtra("status", 0);
        }

        if (status == 0) {
            tvTitle.setText("管理收货地址");
        } else if (status == 1) {
            tvTitle.setText("选择收货地址");
        }
        tvRight.setText("添加");

        ivBack.setVisibility(View.VISIBLE);
        tvRight.setVisibility(View.VISIBLE);
        rlTitleBar.setVisibility(View.VISIBLE);

        oldItems = new ItemData();
        newItems = new ItemData();
        mRecyclerView.setAdapter(adapter = createAdapter());
        mRecyclerView.setLayoutManager(createLayoutManager());
        mRecyclerView.addOnRefreshListener(this);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration
                .VERTICAL));
        mRecyclerView.addOnScrollStateListener(new OnScrollStateListener() {
            @Override
            public void onScrollStateChanged(int state1) {
                if (state1 == RecyclerView.SCROLL_STATE_IDLE)
                    Glide.with(Utils.getApplication()).resumeRequests();
                else Glide.with(Utils.getApplication()).pauseRequests();
            }
        });
        isLoadMore = false;
        dataObserver();
        getRemoteData();
    }

    private void dataObserver() {
        LiveBus.getDefault().subscribe(Constants.Me.EVENT_EDIT_ADDRESS_CHANGED,
                Constants.Me.EVENT_EDIT_ADDRESS_CHANGED, AddressInfo.class)
                .observe(this, new Observer<AddressInfo>() {
                    @Override
                    public void onChanged(@Nullable final AddressInfo data) {
                        String status = data.status;
                        if (status.equals("del")) {
                            if (data.isDefault) {
                                ToastUtils.showLong("不能删除默认地址");
                            } else {
                                MaterialDialogUtils.showBasicDialog(AddressListActivity.this,
                                        "删除地址", "是否真的删除")
                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                                            @Override
                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                if (oldItems.remove(data))
                                                    adapter.notifyDataSetChanged();
                                            }
                                        }).show();
                            }
                        } else if (status.equals("check")) {
                            for (Object item : newItems) {
                                AddressInfo info = (AddressInfo) item;
                                if (info.isDefault) {
                                    info.isDefault = false;
                                }
                            }
                            data.isDefault = true;
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LiveBus.getDefault().clear(Constants.Me.EVENT_EDIT_ADDRESS_CHANGED);
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        onRefresh();
    }

    /*获取网络数据*/
    private void getRemoteData() {
        setData(ApiData.getAddressInfos());

        // TODO: 2019/1/26 取消
//        ApiRepo.getAddressList(" ")
//                .subscribeWith(new RxSubscriber<BaseResponse<AddressInfo>>() {
//                    @Override
//                    public void onSuccess(BaseResponse<AddressInfo> data) {
//                        setData(data.getData());
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//
//                    }
//                });
    }

    private RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(this);
    }

    private DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getAddressAdapter(this)
                .setOnItemClickListener(this)
                .build();
    }

    @Override
    public void onRefresh() {
        KLog.i("onRefresh");
        isRefresh = true;
        isLoadMore = false;
        getRemoteData();
    }

    @Override
    public void onLoadMore() {
        KLog.i("onLoadMore");
        isLoadMore = true;
        getRemoteData();
    }

    /* *//*获取更多网络数据t*//*
    protected void getLoadMoreData() {
        getRemoteData();
    }*/

    private void setData(List<?> collection) {
        if (isLoadMore) {
            onLoadMoreSuccess(collection);
        } else {
            onRefreshSuccess(collection);
        }
    }

    private void onRefreshSuccess(Collection<?> collection) {
        KLog.i(oldItems.size() + " " + newItems.size());

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

    private void onLoadMoreSuccess(List<?> collection) {
        KLog.i(oldItems.size() + " " + newItems.size());

        isLoading = true;
        isLoadMore = false;
        oldItems.addAll(collection);
        mRecyclerView.loadMoreComplete(collection, collection.size() < 20);
    }

    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                Map<String, Object> map = new HashMap<>();
                map.put("status", 0);
                ActivityToActivity.toActivity(ARouterConfig.Me.MODIFYACTIVITY, map);
                break;
        }
    }

    @Override
    public void onItemClick(View view, int i, Object object) {
        if (status == 1) {
            AddressInfo data = (AddressInfo) object;
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", data);
            Intent intent = new Intent();
            intent.putExtras(bundle);
            ActivityToActivity.setResult(this, intent);
        }
    }
}