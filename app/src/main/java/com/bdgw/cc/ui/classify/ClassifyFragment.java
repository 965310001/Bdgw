package com.bdgw.cc.ui.classify;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.ApiData;
import com.bdgw.cc.ui.Constants;
import com.bdgw.cc.ui.adapter.AdapterPool;
import com.bdgw.cc.ui.classify.bean.ClassificationInfo;
import com.socks.library.KLog;
import com.trecyclerview.TRecyclerView;
import com.trecyclerview.adapter.DelegateAdapter;
import com.trecyclerview.adapter.ItemData;
import com.trecyclerview.listener.OnItemClickListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.event.LiveBus;
import me.goldze.common.base.mvvm.base.BaseFragment;
import me.goldze.common.base.mvvm.stateview.LoadingState;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ActivityToActivity;

/**
 * 三级分类
 */
public class ClassifyFragment extends BaseFragment implements OnItemClickListener {

    @BindView(R.id.rv_left)
    TRecyclerView rvLeftRecyclerView;
    @BindView(R.id.rv_right)
    TRecyclerView rvRightRecyclerView;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;

    private DelegateAdapter leftAdapter, rightAdapter;

    ItemData oldItems, newItems;

    private final ItemData leftData = new ItemData();
    private final ItemData rightData = new ItemData();

    public static ClassifyFragment newInstance() {
        return new ClassifyFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_classify;
    }

    @Override
    protected int getContentResId() {
        return R.id.content_layout;
    }

    @Override
    public void initView(Bundle state) {
        loadManager.showStateView(LoadingState.class);

        tvTitle.setText("分类");
        rlTitleBar.setVisibility(View.VISIBLE);
        ivSearch.setVisibility(View.VISIBLE);

        leftAdapter = AdapterPool.newInstance().getLeftAdapter(getActivity())
                .setOnItemClickListener(this).build();

        rightAdapter = AdapterPool.newInstance().getRightAdapter(getActivity())
                .setOnItemClickListener(this)
                .build();

        leftAdapter.setDatas(leftData);
        rightAdapter.setDatas(rightData);

        rvLeftRecyclerView.setAdapter(leftAdapter);
        rvRightRecyclerView.setAdapter(rightAdapter);

        rvLeftRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvRightRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();

        loadManager.showSuccess();

        ClassificationInfo info = ApiData.getClasszInfo();
        leftData.clear();
        rightData.clear();

        leftData.addAll(info.getData());
        rightData.addAll(info.getData().get(0).getChildren());

        leftAdapter.notifyDataSetChanged();
        rightAdapter.notifyDataSetChanged();

//        ApiRepo.getTreeData().subscribeWith(new RxSubscriber<ClassificationInfo>() {
//            @Override
//            public void onSuccess(ClassificationInfo info) {
//                KLog.i(info.toString());
//                loadManager.showSuccess();
//                leftData.clear();
//                rightData.clear();
//                leftData.addAll(info.getData());
//                rightData.addAll(info.getData().get(0).getChildren());
//                KLog.i("右边onSuccess" + rightData.size());
//                leftAdapter.notifyDataSetChanged();
//                rightAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                KLog.i(msg);
//                loadManager.showStateView(ErrorState.class);
//            }
//        });
    }

    @Override
    protected void onStateRefresh() {
        lazyLoad();
    }

    private int leftPostion;

    @Override
    public void onItemClick(View view, int postion, Object object) {
        if (object instanceof ClassificationInfo.DataBean) {
            if (leftPostion != postion) {
                rightData.clear();
                rightData.addAll(((ClassificationInfo.DataBean) object).getChildren());
                LiveBus.getDefault().postEvent(Constants.Listview.POSTION_LEF_EVENT_KEY[0],
                        Constants.Listview.POSTION_LEF_EVENT_KEY[1], postion);
                LiveBus.getDefault().postEvent(Constants.Listview.POSTION_RIGHT_EVENT_KEY[0],
                        Constants.Listview.POSTION_RIGHT_EVENT_KEY[1], 0);
                leftAdapter.notifyDataSetChanged();
                rightAdapter.notifyDataSetChanged();
                leftPostion = postion;
            }
        } else if (object instanceof ClassificationInfo.DataBean.ChildrenBeanX) {
            LiveBus.getDefault().postEvent(Constants.Listview.POSTION_RIGHT_EVENT_KEY[0],
                    Constants.Listview.POSTION_RIGHT_EVENT_KEY[1], postion);
            rightAdapter.notifyDataSetChanged();
            ClassificationInfo.DataBean.ChildrenBeanX datasBean = (ClassificationInfo.DataBean.ChildrenBeanX) rightData.get(postion);
            KLog.i(datasBean.getName() + " ");
            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(datasBean.getId()));
            ActivityToActivity.toActivity(ARouterConfig.classify.PRODUCTSACTIVITY, map);
        }
    }

    @OnClick(R.id.iv_search)
    public void onClick() {
        ActivityToActivity.toActivity(ARouterConfig.home.SEARCHACTIVITY);
    }
}

/**
 * 二级listiiew
 */
//public class ClassifyFragment extends BaseFragment implements OnItemClickListener {
//
//    @BindView(R.id.rv_left)
//    TRecyclerView rvLeftRecyclerView;
//    @BindView(R.id.rv_right)
//    TRecyclerView rvRightRecyclerView;
//
//    @BindView(R.id.tv_title)
//    TextView tvTitle;
//    @BindView(R.id.iv_search)
//    ImageView ivSearch;
//    @BindView(R.id.rl_title_bar)
//    RelativeLayout rlTitleBar;
//
//    private DelegateAdapter leftAdapter, rightAdapter;
//
//    private ItemData leftData = new ItemData();
//    private ItemData rightData = new ItemData();
//
//    public static ClassifyFragment newInstance() {
//        return new ClassifyFragment();
//    }
//
//    @Override
//    protected int getLayoutResId() {
//        return R.layout.fragment_classify;
//    }
//
//    @Override
//    protected int getContentResId() {
//        return R.id.content_layout;
//    }
//
//    @Override
//    public void initView(Bundle state) {
//        loadManager.showStateView(LoadingState.class);
//
//        tvTitle.setText("分类");
//        rlTitleBar.setVisibility(View.VISIBLE);
//        ivSearch.setVisibility(View.VISIBLE);
//
//        leftAdapter = AdapterPool.newInstance().getLeftAdapter(getActivity())
//                .setOnItemClickListener(this).build();
//
//        rightAdapter = AdapterPool.newInstance().getRightAdapter(getActivity())
//                .setOnItemClickListener(this)
//                .build();
//
//        leftAdapter.setDatas(leftData);
//        rightAdapter.setDatas(rightData);
//
//        rvLeftRecyclerView.setAdapter(leftAdapter);
//        rvRightRecyclerView.setAdapter(rightAdapter);
//
//        rvLeftRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        rvRightRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//    }
//
//    @Override
//    protected void lazyLoad() {
//        super.lazyLoad();
//        ApiRepo.getTreeData().subscribeWith(new RxSubscriber<ClassificationInfo>() {
//            @Override
//            public void onSuccess(ClassificationInfo info) {
//                KLog.i(info.toString());
//                loadManager.showSuccess();
//                leftData.clear();
//                rightData.clear();
//                leftData.addAll(info.getData());
//                rightData.addAll(info.getData().get(0).getChildren());
//                KLog.i("右边onSuccess" + rightData.size());
//                leftAdapter.notifyDataSetChanged();
//                rightAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                KLog.i(msg);
//                loadManager.showStateView(ErrorState.class);
//            }
//        });
//    }
//
//    @Override
//    protected void onStateRefresh() {
//        lazyLoad();
//    }
//
//    @Override
//    public void onItemClick(View view, int postion, Object object) {
//        if (object instanceof ClassificationInfo.DataBean) {
//            rightData.clear();
//            rightData.addAll(((ClassificationInfo.DataBean) object).getChildren());
//            LiveBus.getDefault().postEvent(Constants.Listview.POSTION_LEF_EVENT_KEY[0],
//                    Constants.Listview.POSTION_LEF_EVENT_KEY[1], postion);
//            LiveBus.getDefault().postEvent(Constants.Listview.POSTION_RIGHT_EVENT_KEY[0],
//                    Constants.Listview.POSTION_RIGHT_EVENT_KEY[1], 0);
//            leftAdapter.notifyDataSetChanged();
//            rightAdapter.notifyDataSetChanged();
//        } else if (object instanceof ClassificationInfo.DataBean.ChildrenBean) {
//            LiveBus.getDefault().postEvent(Constants.Listview.POSTION_RIGHT_EVENT_KEY[0],
//                    Constants.Listview.POSTION_RIGHT_EVENT_KEY[1], postion);
//            rightAdapter.notifyDataSetChanged();
//            ClassificationInfo.DataBean.ChildrenBean datasBean = (ClassificationInfo.DataBean.ChildrenBean) rightData.get(postion);
//            KLog.i(datasBean.getName() + " ");
//
//            Map<String, String> map = new HashMap<>();
//            map.put("id", String.valueOf(datasBean.getId()));
//            ActivityToActivity.toActivity(ARouterConfig.classify.PRODUCTSACTIVITY, map);
//        }
//    }
//
//    @OnClick(R.id.iv_search)
//    public void onClick() {
//        ActivityToActivity.toActivity(ARouterConfig.home.SEARCHACTIVITY);
//    }
//}

