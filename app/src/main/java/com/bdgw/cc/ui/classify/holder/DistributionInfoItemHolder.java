package com.bdgw.cc.ui.classify.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.classify.bean.DistributionInfo;
import com.trecyclerview.holder.AbsHolder;
import com.trecyclerview.holder.AbsItemHolder;

/**
 * @author GuoFeng
 * @date : 2019/1/27 18:43
 * @description:
 */
public class DistributionInfoItemHolder extends AbsItemHolder<DistributionInfo.VendorsBean,
        DistributionInfoItemHolder.ViewHolder> {

    public DistributionInfoItemHolder(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_distributioin;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull DistributionInfo.VendorsBean data) {
        holder.tvName.setText(data.getName().concat("  配送费:").concat("￥").concat(data.getFee().toString()));
        /*holder.tvState.setText(data.getName());*/
        holder.tvDes.setText(data.getDesc());
        holder.ivCheck.setSelected(data.isSelect());
    }

    static class ViewHolder extends AbsHolder {
        private final   TextView tvName, tvState, tvDes;
        private final    ImageView ivCheck;

        private ViewHolder(View itemView) {
            super(itemView);
            tvName = getViewById(R.id.tv_name);
            tvState = getViewById(R.id.tv_state);
            tvDes = getViewById(R.id.tv_des);
            ivCheck = getViewById(R.id.iv_check);
        }
    }

}
