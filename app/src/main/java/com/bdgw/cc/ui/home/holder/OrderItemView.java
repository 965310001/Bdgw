package com.bdgw.cc.ui.home.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bdgw.cc.R;
import com.bdgw.cc.ui.Constants;
import com.bdgw.cc.ui.home.bean.OrderInfo;
import com.socks.library.KLog;
import com.trecyclerview.holder.AbsHolder;
import com.trecyclerview.holder.AbsItemHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ActivityToActivity;
import me.goldze.common.utils.ImageUtils;
import me.goldze.common.widget.dialog.MaterialDialogUtils;

/**
 * @author GuoFeng
 * @date : 2019/1/22 14:58
 * @description:
 */
public class OrderItemView extends AbsItemHolder<OrderInfo.OrdersBean, OrderItemView.ViewHolder> implements View.OnClickListener {

    private int status = 0;
    private OrderInfo.OrdersBean bean;
    private TextView tv;
    private String str;

    public OrderItemView(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.menu_item_order;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    //        "待付款", "已取消", "配送中", "待评价", "待发货"
    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, @NonNull final OrderInfo.OrdersBean bean) {
        status = bean.getStatus();
        ImageUtils.loadImage(holder.ivImg, bean.getThumb());
        holder.tvPrice.setText("(共计1件商品) 合计 : ￥ " + bean.getTotal()
                + " (含运费:￥144.00)" + bean.getShippingprice());
        KLog.i(status + " " + status);
        holder.tvState.setText(Constants.Order.ORDER_STATUS[status]);

        holder.tvName.setText(bean.getGoodsName());
        switch (status) {
            case 0:
                this.bean = bean;
                holder.tvLeftState.setVisibility(View.VISIBLE);
                holder.tvRightState.setVisibility(View.VISIBLE);
                holder.tvRightState.setText("取消订单");
                holder.tvLeftState.setText("去支付");
                holder.tvLeftState.setOnClickListener(this);
                holder.tvRightState.setOnClickListener(this);
                this.status = 0;
                break;
            case 2:
                this.status = 2;
                this.bean = bean;
                holder.tvLeftState.setVisibility(View.VISIBLE);
                holder.tvRightState.setVisibility(View.VISIBLE);
                holder.tvRightState.setText("查看物流");
                holder.tvLeftState.setText("确认收货");
                holder.tvLeftState.setOnClickListener(this);
                holder.tvRightState.setOnClickListener(this);
                break;
            case 3:
                this.status = 3;
                this.bean = bean;
                holder.tvRightState.setVisibility(View.VISIBLE);
                holder.tvRightState.setText("评价晒单");
                holder.tvLeftState.setVisibility(View.INVISIBLE);
                holder.tvRightState.setOnClickListener(this);
                break;
        }
    }


    @Override
    public void onClick(View v) {
        tv = v.findViewById(v.getId());
        str = tv.getText().toString().trim();
        if ("取消订单".equals(str)) {
            KLog.i("取消订单");
            MaterialDialogUtils.showBasicDialog(mContext, "取消订单", "是否真的取消订单")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            KLog.i("取消订单");
                        }
                    }).show();
        } else if ("去支付".equals(str)) {
            KLog.i("去支付");
        } else if ("查看物流".equals(str)) {
            ActivityToActivity.toActivity(ARouterConfig.Shopping.DELIVERYACTIVITY, "id", bean.getGoodsid());
        } else if ("确认收货".equals(str)) {
            MaterialDialogUtils.showBasicDialog(mContext, "确认收货", "是否真的收到货了")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            KLog.i("确认收货");
                        }
                    }).show();
        } else if ("评价晒单".equals(str)) {
            KLog.i("评价晒单");

            ActivityToActivity.toActivity(ARouterConfig.Shopping.EVALUATIONACTIVITY, "bean", bean);
        }
    }

    public static class ViewHolder extends AbsHolder {

        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_left_state)
        TextView tvLeftState;
        @BindView(R.id.tv_right_state)
        TextView tvRightState;
        @BindView(R.id.tv_name)
        TextView tvName;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
