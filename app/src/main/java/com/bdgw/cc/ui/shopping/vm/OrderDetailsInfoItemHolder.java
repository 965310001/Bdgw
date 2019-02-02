package com.bdgw.cc.ui.shopping.vm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.shopping.bean.GoodsInfo;
import com.trecyclerview.holder.AbsHolder;
import com.trecyclerview.holder.AbsItemHolder;

/**
 * @author GuoFeng
 * @date : 2019/1/30 15:02
 * @description:
 */
public class OrderDetailsInfoItemHolder extends AbsItemHolder<GoodsInfo, OrderDetailsInfoItemHolder.ViewHolder> {

    public OrderDetailsInfoItemHolder(Context context) {
        super(context);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_order_details;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, @NonNull GoodsInfo data) {

        holder.tvName.setVisibility(View.VISIBLE);
        holder.tvNum.setVisibility(View.VISIBLE);
        holder.tvPrice.setVisibility(View.VISIBLE);
        holder.tvGetAll.setVisibility(View.INVISIBLE);

        holder.tvName.setText(data.getGoodsName());
        holder.tvNum.setText("x" + String.valueOf(data.getNum()));
        holder.tvPrice.setText(data.getGoodsPrice());
    }

    class ViewHolder extends AbsHolder {
        private TextView tvName;
        private TextView tvNum;
        private TextView tvPrice;
        private TextView tvGetAll;

        private ViewHolder(View view) {
            super(view);
            tvName = getViewById(R.id.tv_name);
            tvNum = getViewById(R.id.tv_num);
            tvPrice = getViewById(R.id.tv_price);
            tvGetAll = getViewById(R.id.tv_get_all);
        }
    }

}
