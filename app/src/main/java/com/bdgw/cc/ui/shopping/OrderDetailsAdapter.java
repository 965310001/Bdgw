package com.bdgw.cc.ui.shopping;

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
 * @date : 2019/1/30 15:53
 * @description:
 */
public class OrderDetailsAdapter extends AbsItemHolder<GoodsInfo, OrderDetailsAdapter.ViewHolder> {

    public OrderDetailsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_test;
    }

    @Override
    public OrderDetailsAdapter.ViewHolder createViewHolder(View view) {
        return new OrderDetailsAdapter.ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull OrderDetailsAdapter.ViewHolder holder, @NonNull GoodsInfo data) {
        holder.tvText.setText(data.getGoodsName());
    }

    class ViewHolder extends AbsHolder {
        TextView tvText;

        private ViewHolder(View itemView) {
            super(itemView);
            tvText = getViewById(R.id.tv_name);
        }
    }
}
