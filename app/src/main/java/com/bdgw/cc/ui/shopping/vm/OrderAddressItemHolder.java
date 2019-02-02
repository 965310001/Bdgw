package com.bdgw.cc.ui.shopping.vm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.me.bean.AddressInfo;
import com.trecyclerview.holder.AbsHolder;
import com.trecyclerview.holder.AbsItemHolder;

/**
 * @author GuoFeng
 * @date : 2019/1/30 16:59
 * @description:
 */
public class OrderAddressItemHolder extends AbsItemHolder<AddressInfo, OrderAddressItemHolder.ViewHolder> {

    public OrderAddressItemHolder(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_order_address;
    }

    @Override
    public OrderAddressItemHolder.ViewHolder createViewHolder(View view) {
        return new OrderAddressItemHolder.ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull OrderAddressItemHolder.ViewHolder holder, @NonNull AddressInfo bean) {
        holder.tvAddress.setText(bean.name.concat(bean.phone).concat(" 默认\n").concat(bean.address).concat(bean.details));
    }

    static class ViewHolder extends AbsHolder {
        TextView tvAddress;

        private ViewHolder(View itemView) {
            super(itemView);
            tvAddress = getViewById(R.id.tv_address);
        }
    }

}
