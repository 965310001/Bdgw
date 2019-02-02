package com.bdgw.cc.ui.me.vm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.me.bean.DefiniteInfo;
import com.trecyclerview.holder.AbsHolder;
import com.trecyclerview.holder.AbsItemHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.goldze.common.utils.DateUtils;

/**
 * @author GuoFeng
 * @date : 2019/1/28 20:58
 * @description:
 */
public class DefiniteInfoItemHolder extends AbsItemHolder<DefiniteInfo.BalancesBean, DefiniteInfoItemHolder.ViewHolder> {

    public DefiniteInfoItemHolder(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_definite;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, @NonNull final DefiniteInfo.BalancesBean data) {
        holder.tvState.setText(data.getMemo());
        holder.tvTime.setText(DateUtils.getFormatDate(data.getCreate_at(), null));
        holder.tvMoney.setText("-" + data.getPrice());

    }

    public static class ViewHolder extends AbsHolder {

        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_money)
        TextView tvMoney;

        private ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}