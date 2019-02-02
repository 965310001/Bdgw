package com.bdgw.cc.ui.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.home.bean.TypeInfo;
import com.trecyclerview.holder.AbsHolder;
import com.trecyclerview.holder.AbsItemHolder;

/**
 * @author GuoFeng
 * @date : 2019/1/24 18:04
 * @description:
 */
public class TypeItemView  extends AbsItemHolder<TypeInfo, TypeItemView.ViewHolder> {

    public TypeItemView(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_type;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull TypeInfo typeVo) {
        holder.mClassifyType.setText(typeVo.title);
    }

    @Override
    protected void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        RecyclerView.LayoutParams clp = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        if (clp instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) clp).setFullSpan(true);
        }
    }

    static class ViewHolder extends AbsHolder {

        private TextView mClassifyType;
//        private LinearLayout mRootLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mClassifyType = getViewById(R.id.tv_classify_type);
//            mRootLayout = getViewById(R.id.root_layout);
        }
    }
}
