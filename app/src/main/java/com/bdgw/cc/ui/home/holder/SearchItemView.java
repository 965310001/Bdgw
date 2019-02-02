package com.bdgw.cc.ui.home.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.home.bean.SearchInfo;
import com.trecyclerview.holder.AbsHolder;
import com.trecyclerview.holder.AbsItemHolder;

import me.goldze.common.utils.ImageUtils;

/**
 * @author GuoFeng
 * @date : 2019/1/23 16:11
 * @description:
 */
public class SearchItemView extends AbsItemHolder<SearchInfo.DataBean.DatasBean, SearchItemView.ViewHolder> {

    public SearchItemView(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.search_item_home;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull SearchItemView.ViewHolder holder, @NonNull SearchInfo.DataBean.DatasBean
            datasBean) {
        holder.tvName.setText(datasBean.getChapterName());
        ImageUtils.loadImage(holder.ivIcon, datasBean.getEnvelopePic());
    }

    class ViewHolder extends AbsHolder {

        TextView tvName;
        ImageView ivIcon;

        private ViewHolder(View view) {
            super(view);
            tvName = getViewById(R.id.tv_name);
            ivIcon = getViewById(R.id.iv_icon);
        }
    }
}
