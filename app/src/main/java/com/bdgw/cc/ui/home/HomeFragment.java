package com.bdgw.cc.ui.home;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.ApiData;
import com.bdgw.cc.ui.Constants;
import com.bdgw.cc.ui.MViewModel;
import com.bdgw.cc.ui.adapter.AdapterPool;
import com.bdgw.cc.ui.home.bean.HomeMerge;
import com.bdgw.cc.ui.home.bean.TypeInfo;
import com.bdgw.cc.ui.home.holder.CatagoryInfo;
import com.bdgw.cc.ui.shopping.bean.GoodsInfo;
import com.socks.library.KLog;
import com.trecyclerview.adapter.DelegateAdapter;
import com.trecyclerview.listener.OnItemClickListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import me.goldze.common.base.mvvm.base.BaseListFragment;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ActivityToActivity;

/**
 * 首页
 */
public class HomeFragment extends BaseListFragment<MViewModel> implements OnItemClickListener {

    @BindView(R.id.tv_right)
    TextView tvRight;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        setTitle(getResources().getString(R.string.main_home));
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("登录");
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityToActivity.toActivity(ARouterConfig.LOGINACTIVITY);
            }
        });
    }

    @Override
    protected Object getStateEventKey() {
        return Constants.EVENT_KEY_WORK_LIST_STATE;
    }

    @Override
    protected void getRemoteData() {
        super.getRemoteData();

        mViewModel.getHomeData();
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();

        mViewModel.getHomeData();
    }

    @Override
    protected void dataObserver() {
        super.dataObserver();

        registerObserver(Constants.EVENT_KEY_HOME, HomeMerge.class)
                .observe(this, new Observer<HomeMerge>() {
                    @Override
                    public void onChanged(@Nullable HomeMerge homeMerge) {
                        if (null != homeMerge) {
                            addItems(homeMerge);
                        }
                    }
                });
    }

    private void addItems(HomeMerge homeMergeVo) {
        newItems.add(homeMergeVo.banner);
        newItems.add(new CatagoryInfo());

        newItems.add(new TypeInfo("热门推荐"));
        newItems.addAll(ApiData.getGoodsInfos());
        newItems.add(new TypeInfo("销量排行"));
        newItems.addAll(ApiData.getGoodsInfos());

        oldItems.clear();
        oldItems.addAll(newItems);
        mRecyclerView.refreshComplete(oldItems, true);
        newItems.clear();
    }

    @Override
    protected boolean isItemDecoration() {
        return false;
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        KLog.i("---------------------");
    }

    @Override
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getHomeAdapter(getActivity())
                .setOnItemClickListener(this)
                .build();
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public void onItemClick(View view, int i, Object object) {
        // TODO: 2019/1/20 点击效果
        if (object != null) {
            if (object instanceof GoodsInfo) {
               /* Intent intent = new Intent(activity, VideoDetailsActivity.class);
                intent.putExtra("course_id", ((CourseInfoVo) object).courseid);
                activity.startActivity(intent);*/
                GoodsInfo info = (GoodsInfo) object;
//                KLog.i(((VendorInfo.GoodsInfo) object).getGoodsName());
                Map<String, String> map = new HashMap<>();
                map.put("id", info.getGoodsId());
                ActivityToActivity.toActivity(ARouterConfig.home.SHOPPINGDETAILSACTIVITY, map);
            }

        }
    }
}
