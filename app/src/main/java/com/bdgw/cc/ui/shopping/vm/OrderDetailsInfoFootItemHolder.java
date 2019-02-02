package com.bdgw.cc.ui.shopping.vm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.shopping.bean.OrderDetailsInfo;
import com.socks.library.KLog;
import com.trecyclerview.holder.AbsHolder;
import com.trecyclerview.holder.AbsItemHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.goldze.common.widget.MultipleItemView;

/**
 * @author GuoFeng
 * @date : 2019/1/30 17:08
 * @description:
 */
public class OrderDetailsInfoFootItemHolder extends AbsItemHolder<OrderDetailsInfo.OrderInfoBean, OrderDetailsInfoFootItemHolder.ViewHolder> {

    public OrderDetailsInfoFootItemHolder(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_order_info;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull OrderDetailsInfoFootItemHolder.ViewHolder holder, @NonNull OrderDetailsInfo.OrderInfoBean data) {
        holder.mvOrderNumber.setLeftText("订单编号".concat(data.getCustomerId()));
        holder.mvTime.setLeftText("下单时间".concat(data.getCreateTime()));
        holder.mvBuyStatus.setLeftText("支付方式".concat(" 支付宝"));
        holder.mvDistribution.setLeftText("配送信息".concat(" 中通快递"));
        holder.mvInvoiceInfo.setLeftText("发票信息".concat(" 暂无信息"));
        holder.mvInvoiceHead.setLeftText("发票抬头".concat(" 暂无信息"));
        holder.mvInvoiceContent.setLeftText("发票内容".concat(" 暂无信息"));
        holder.mvPrice.setRightText("商品金额 333");
        holder.mvFreight.setRightText("运费 333");
        holder.mvTaxes.setRightText("税费 333");
        holder.tvActualPrice.setText("1种，3件商品 小计(含税费):46.18");

        holder.tvSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KLog.i("提交");
            }
        });
    }

    static class ViewHolder extends AbsHolder {
        @BindView(R.id.mv_order_number)
        MultipleItemView mvOrderNumber;
        @BindView(R.id.mv_time)
        MultipleItemView mvTime;
        @BindView(R.id.mv_buy_status)
        MultipleItemView mvBuyStatus;
        @BindView(R.id.mv_distribution)
        MultipleItemView mvDistribution;
        @BindView(R.id.mv_invoice_info)
        MultipleItemView mvInvoiceInfo;
        @BindView(R.id.mv_invoice_head)
        MultipleItemView mvInvoiceHead;
        @BindView(R.id.mv_invoice_content)
        MultipleItemView mvInvoiceContent;
        @BindView(R.id.mv_price)
        MultipleItemView mvPrice;
        @BindView(R.id.mv_freight)
        MultipleItemView mvFreight;
        @BindView(R.id.mv_taxes)
        MultipleItemView mvTaxes;
        @BindView(R.id.tv_actual_price)
        TextView tvActualPrice;
        @BindView(R.id.tv_sub)
        TextView tvSub;

        private ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}

