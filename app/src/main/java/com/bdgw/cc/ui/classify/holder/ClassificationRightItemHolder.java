package com.bdgw.cc.ui.classify.holder;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.Constants;
import com.bdgw.cc.ui.adapter.AdapterPool;
import com.bdgw.cc.ui.classify.bean.ClassificationInfo;
import com.socks.library.KLog;
import com.trecyclerview.TRecyclerView;
import com.trecyclerview.adapter.DelegateAdapter;
import com.trecyclerview.holder.AbsHolder;
import com.trecyclerview.holder.AbsItemHolder;
import com.trecyclerview.listener.OnItemClickListener;

import java.util.HashMap;
import java.util.Map;

import me.goldze.common.base.event.LiveBus;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ActivityToActivity;
import me.goldze.common.utils.ResourcesUtils;
import me.goldze.common.widget.MultipleItemView;

/**
 * @author GuoFeng
 * @date : 2019/1/21 11:22
 * @description: 三级listview
 */
public class ClassificationRightItemHolder extends AbsItemHolder<ClassificationInfo.DataBean.ChildrenBeanX,
        ClassificationRightItemHolder.ViewHolder> {

//    private DelegateAdapter adapter;

    private int selectIndex;

    public ClassificationRightItemHolder(Context context) {
        super(context);

        LiveBus.getDefault().subscribe(Constants.Listview.POSTION_RIGHT_EVENT_KEY[0],
                Constants.Listview.POSTION_RIGHT_EVENT_KEY[1]).observeForever(new Observer<Object>() {
            @Override
            public void onChanged(@Nullable Object o) {
                selectIndex = (int) o;
            }
        });
    }


    @Override
    public int getLayoutResId() {
        return R.layout.classify_item_of_list;
    }

    @Override
    public ClassificationRightItemHolder.ViewHolder createViewHolder(View view) {
        return new ClassificationRightItemHolder.ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ClassificationRightItemHolder.ViewHolder holder,
                                    @NonNull final ClassificationInfo.DataBean.ChildrenBeanX dataBean) {

        holder.tvText.setText(dataBean.getName());
        boolean isSelect = getPosition(holder) == selectIndex;
        holder.tvText.setTextColor(ResourcesUtils.getInstance().getColor(isSelect ? R.color.white : R.color.black));
        holder.tvText.setBackgroundColor(ResourcesUtils.getInstance().getColor(isSelect ? R.color.orange : R.color.transparent));
        holder.tvText.setVisibility(View.GONE);

        //右边
        holder.miv.setVisibility(View.VISIBLE);
        holder.miv.setLeftText(dataBean.getName());
        DelegateAdapter adapter = AdapterPool.newInstance().getRightAdapter1(mContext)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int postion, Object object) {
                        ClassificationInfo.DataBean.ChildrenBeanX.ChildrenBean data =
                                dataBean.getChildren().get(postion);
                        KLog.i(data.getName() + " ");

                        Map<String, String> map = new HashMap<>();
                        map.put("id", String.valueOf(data.getId()));
                        ActivityToActivity.toActivity(ARouterConfig.classify.PRODUCTSACTIVITY, map);
                    }
                }).build();
        adapter.setDatas(dataBean.getChildren());
        GridLayoutManager layout = new GridLayoutManager(mContext, 3);
        holder.recyclerView.setLayoutManager(layout);
        holder.recyclerView.setVisibility(View.VISIBLE);
        holder.recyclerView.setAdapter(adapter);
//        holder.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
//        holder.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL));
    }

    static class ViewHolder extends AbsHolder {
        private final  TextView tvText;
        private final MultipleItemView miv;
        private final  TRecyclerView recyclerView;

        private ViewHolder(View itemView) {
            super(itemView);
            tvText = getViewById(R.id.tv_text);
            miv = getViewById(R.id.miv_1);
            recyclerView = getViewById(R.id.rv_right);
        }
    }


}


/**
 * @author GuoFeng
 * @date : 2019/1/21 11:22
 * @description: 二级listview
 */
//public class ClassificationRightItemHolder extends AbsItemHolder<ClassificationInfo.DataBean.ChildrenBean,
//        ClassificationItemHolder.ViewHolder> {
//
//    private int selectIndex;
//
//    public ClassificationRightItemHolder(Context context) {
//        super(context);
//
//        LiveBus.getDefault().subscribe(Constants.Listview.POSTION_RIGHT_EVENT_KEY[0],
//                Constants.Listview.POSTION_RIGHT_EVENT_KEY[1]).observeForever(new Observer<Object>() {
//            @Override
//            public void onChanged(@Nullable Object o) {
//                selectIndex = (int) o;
//            }
//        });
//    }
//
//    @Override
//    public int getLayoutResId() {
//        return R.layout.classify_item_of_list;
//    }
//
//    @Override
//    public ClassificationItemHolder.ViewHolder createViewHolder(View view) {
//        return new ClassificationItemHolder.ViewHolder(view);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull ClassificationItemHolder.ViewHolder holder,
//                                    @NonNull ClassificationInfo.DataBean.ChildrenBean dataBean) {
//        holder.tvText.setText(dataBean.getName());
//
//        boolean isSelect = getPosition(holder) == selectIndex;
//        holder.tvText.setTextColor(ResourcesUtils.getInstance().getColor(isSelect ? R.color.white : R.color.black));
//        holder.tvText.setBackgroundColor(ResourcesUtils.getInstance().getColor(isSelect ? R.color.orange : R.color.transparent));
//    }
//
//}
