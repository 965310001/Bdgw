package com.bdgw.cc.ui.shopping.vm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.shopping.bean.DeliverInfo;
import com.trecyclerview.holder.AbsHolder;
import com.trecyclerview.holder.AbsItemHolder;

/**
 * @author GuoFeng
 * @date : 2019/1/30 10:52
 * @description:
 */
public class DeliverInfoItemHolder extends AbsItemHolder<DeliverInfo.TracesBean, DeliverInfoItemHolder.ViewHolder> {

    public DeliverInfoItemHolder(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_deliver;
    }

    @Override
    public DeliverInfoItemHolder.ViewHolder createViewHolder(View view) {
        return new DeliverInfoItemHolder.ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull DeliverInfo.TracesBean data) {
        holder.tvAddress.setText(data.getAcceptStation());
        holder.tvTime.setText(data.getAcceptTime());
        holder.ivStatus.setSelected(holder.getLayoutPosition() == 1);
    }

    static class ViewHolder extends AbsHolder {
        private final  TextView tvAddress, tvTime;
        private final  ImageView ivStatus;

        private ViewHolder(View itemView) {
            super(itemView);
            tvAddress = getViewById(R.id.tv_address);
            ivStatus = getViewById(R.id.iv_status);
            tvTime = getViewById(R.id.tv_time);
        }
    }

}
