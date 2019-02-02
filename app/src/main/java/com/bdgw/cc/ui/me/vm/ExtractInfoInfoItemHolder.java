package com.bdgw.cc.ui.me.vm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.me.bean.ExtractInfo;
import com.trecyclerview.holder.AbsHolder;
import com.trecyclerview.holder.AbsItemHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author GuoFeng
 * @date : 2019/1/28 14:30
 * @description:
 */
public class ExtractInfoInfoItemHolder extends AbsItemHolder<ExtractInfo.DataBean, ExtractInfoInfoItemHolder.ViewHolder> {

    public ExtractInfoInfoItemHolder(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_extract;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, @NonNull final ExtractInfo.DataBean data) {
        holder.tvState.setText(data.getStatus());
        holder.tvTime.setText(data.getTime());
        holder.tvSurplusMoney.setText("余额:".concat(data.getSurplusmoney()));
        holder.tvMoney.setText("-"+data.getMoney());
    }

    public static class ViewHolder extends AbsHolder {

        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_surplus_money)
        TextView tvSurplusMoney;
        @BindView(R.id.tv_money)
        TextView tvMoney;

        private ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
