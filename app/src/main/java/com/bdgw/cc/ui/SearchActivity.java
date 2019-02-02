package com.bdgw.cc.ui;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.internal.FlowLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;
import com.socks.library.KLog;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.event.LiveBus;
import me.goldze.common.base.mvvm.base.BaseActivity;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.http.rx.RxSubscriber;
import me.goldze.common.utils.ColorUtil;
import me.goldze.common.utils.SharePreferenceUtil;
import me.goldze.common.utils.ToastUtils;

/**
 * 搜索
 */
@Route(path = ARouterConfig.home.SEARCHACTIVITY)
public class SearchActivity extends BaseActivity {

    @BindView(R.id.tv_hot_title)
    TextView tvHotTitle;

    @BindView(R.id.layout_flow)
    FlowLayout flowLayout;
    @BindView(R.id.layout_flow_or_loading)
    FrameLayout layoutFlowOrLoading;
    @BindView(R.id.tv_search_history_title)
    TextView tvSearchHistoryTitle;
    @BindView(R.id.recycler_search_history)
    RecyclerView recyclerSearchHistory;

    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ed_search)
    MaterialEditText edInput;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;

    private List<String> items = new ArrayList<>();
    private SearchHistoryAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        dataObserver();
        loadManager.showSuccess();

        ivSearch.setVisibility(View.VISIBLE);
        edInput.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.VISIBLE);
        rlTitleBar.setVisibility(View.VISIBLE);

//        edInput = titleBar.getCenterCustomView().findViewById(R.id.ed_input);

//        titleBar.getLeftImageView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//        titleBar.getRightImageView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                search();
//            }
//        });

        recyclerSearchHistory.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchHistoryAdapter(this, items);

//        adapter.setOnItemClickListener(this);
//        adapter.setOnItemChildClickListener(this);
//        adapter.bindToRecyclerView(recyclerSearchHistory);
//        adapter.setEmptyView(R.layout.layout_empty_top);
//        recyclerSearchHistory.addItemDecoration(new ItemDecorationFactory.DividerBuilder()
//                .dividerColor(getResources().getColor(R.color.divider))
//                .build(recyclerSearchHistory));

        recyclerSearchHistory.setAdapter(adapter);

        loadHotSearch();
        loadSearchHistory();
    }

    private void dataObserver() {
        //点击搜索
        LiveBus.getDefault().subscribe(Constants.SEARCH_EVENT_KEY[0], Constants.SEARCH_EVENT_KEY[1],
                String.class).observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String content) {
                KLog.i(content);
                saveSearchKey(content);
                edInput.setText(content);
                search();
            }
        });
        //删除
        LiveBus.getDefault().subscribe(Constants.SEARCH_EVENT_KEY[2], Constants.SEARCH_EVENT_KEY[3],
                Integer.class).observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer position) {
                items.remove((int) position);
                KLog.i("" + position + " " + items.size());

                adapter.notifyDataSetChanged();
                SharePreferenceUtil.saveSearchList(items);
            }
        });
    }

    private void search() {
        String content = edInput.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            ToastUtils.showLong("搜索内容不能为空");
            return;
        }
        // TODO: 2019/1/23 搜索
        KLog.i("搜索");
        saveSearchKey(content);
    }

    /*加载更多的搜索数据*/
    private void loadHotSearch() {
        ApiRepo.getHotKeys().subscribeWith(new RxSubscriber<HotKeyInfo>() {

            @Override
            public void onSuccess(HotKeyInfo response) {
                addHotKey(response.getData());
            }

            @Override
            public void onFailure(String msg) {
                KLog.i(msg);
            }

            @Override
            public void onError(Throwable t) {
                KLog.i(t.getMessage());
            }
        });
    }

    private void addHotKey(List<HotKeyInfo.DataBean> data) {
        Random random = new Random();
        for (final HotKeyInfo.DataBean entity : data) {
            View child = View.inflate(this, R.layout.layout_tag_navi, null);
            TextView textView = child.findViewById(R.id.tv_tag);
            textView.setText(entity.getName());
            textView.setTextColor(ColorUtil.getRandomColor(random));
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    toSearchArticleListActivity(entity.getName());
                    KLog.i(entity.getName());
                    edInput.setText(entity.getName());
                    saveSearchKey(entity.getName());


                }
            });
            flowLayout.addView(child);
        }
    }

    /*获取SharePreference数据*/
    private void loadSearchHistory() {
        items.clear();
        items.addAll(SharePreferenceUtil.getSearchList());
        adapter.notifyDataSetChanged();
    }

    /*保存SharePreference数据*/
    private void saveSearchKey(String searchKey) {
        boolean exit = false;
        for (String item : items) {
            if (item.equals(searchKey)) {
                exit = true;
                break;
            }
        }
        if (exit) {
            items.remove(searchKey);
        }
        items.add(0, searchKey);
        adapter.notifyDataSetChanged();
        SharePreferenceUtil.saveSearchList(items);
    }

    @OnClick({R.id.iv_back, R.id.ed_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.ed_search:
                search();
                break;
        }
    }
}