package com.bdgw.cc.ui.me.vm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bdgw.cc.R;
import com.bdgw.cc.ui.me.bean.AfterSalesInfo;
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
 * @date : 2019/2/1 17:23
 * @description:
 */
public class AfterSalesInfoItemHolder extends AbsItemHolder<AfterSalesInfo, AfterSalesInfoItemHolder.ViewHolder> {

    private final boolean is;

    public AfterSalesInfoItemHolder(Context context) {
        this(context, true);
    }

    public AfterSalesInfoItemHolder(Context context, boolean is) {
        super(context);
        this.is = is;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_aftersales;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, @NonNull final AfterSalesInfo data) {
        holder.tvDes.setText(data.getDes());
        holder.tvReason.setText(data.getReason());
        holder.tvState.setText(data.getStatus());
        holder.tvTypeservice.setText(data.getTypeservice());
        ImageUtils.loadImage(holder.ivIcon, data.getImg());

        if (!is) {//False
            holder.tvCanner.setText("我要售后");
            holder.tvDetailed.setVisibility(View.GONE);
            holder.tvCanner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    KLog.i("我要售后");
                    ActivityToActivity.toActivity(ARouterConfig.Me.ASKACTIVITY, "data", data);
                }
            });
        } else {
            /*取消*/
            holder.tvCanner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MaterialDialogUtils.showBasicDialogNoTitle(mContext, "你确定要取消售后申请")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    // TODO: 2019/2/1 取消订单
                                }
                            }).show();
                }
            });
            /*详情*/
            holder.tvDetailed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 2019/2/1 详情
                    ActivityToActivity.toActivity(ARouterConfig.Me.AFTERSALESDETAILSDCTIVITY, "data", data);
                }
            });
        }
    }

    static class ViewHolder extends AbsHolder {

        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.tv_reason)
        TextView tvReason;
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_typeservice)
        TextView tvTypeservice;

        @BindView(R.id.tv_detailed)
        TextView tvDetailed;
        @BindView(R.id.tv_canner)
        TextView tvCanner;

        private ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}