package com.bdgw.cc.ui.home.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.bdgw.cc.R;
import com.trecyclerview.holder.AbsHolder;
import com.trecyclerview.holder.AbsItemHolder;

import me.goldze.common.adapter.BaseRecyclerAdapter;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ActivityToActivity;

/**
 * @author GuoFeng
 * @date : 2019/1/20 15:56
 * @description:
 */
public class CategoryItemView extends AbsItemHolder<CatagoryInfo, CategoryItemView.ViewHolder> {

    private HomeCategoryAdapter adapter;

    public CategoryItemView(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_category;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull final CatagoryInfo data) {
        adapter = new HomeCategoryAdapter(mContext, data.getData());
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 5);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(adapter);
        holder.recyclerView.setNestedScrollingEnabled(false);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                ActivityToActivity.toActivity(ARouterConfig.classify.PRODUCTSACTIVITY, "id", data.getData().get(position));
            }
        });
    }

    @Override
    protected void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        RecyclerView.LayoutParams clp = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        if (clp instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) clp).setFullSpan(true);
        }
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    public static class ViewHolder extends AbsHolder {

        private final RecyclerView recyclerView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = getViewById(R.id.recycler_view);
        }
    }
}
