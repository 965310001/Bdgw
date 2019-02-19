package com.bdgw.cc.ui.classify.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.classify.bean.ClassificationInfo;
import com.trecyclerview.holder.AbsHolder;
import com.trecyclerview.holder.AbsItemHolder;

import me.goldze.common.utils.ImageUtils;

public class ClassificationRightAdapter extends AbsItemHolder<ClassificationInfo.DataBean.ChildrenBeanX.ChildrenBean,
        ClassificationRightAdapter.ViewHolder> {

    public ClassificationRightAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_classify;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ClassificationInfo.DataBean.ChildrenBeanX.ChildrenBean
            data) {
        holder.tvText.setText(data.getName());
        if (null == data.getIcon()) {
            data.setIcon("https://img14.360buyimg.com/focus/s140x140_jfs/t27136/183/1628977274/31007/a6f7ed55/5be6ebd8Nb07ef492.png");
        }
        ImageUtils.loadImage(holder.ivClassify, data.getIcon());
    }

    class ViewHolder extends AbsHolder {
        private final TextView tvText;
        private final ImageView ivClassify;

        private ViewHolder(View itemView) {
            super(itemView);
            tvText = getViewById(R.id.tv_classify);
            ivClassify = getViewById(R.id.iv_classify);
        }
    }
}
