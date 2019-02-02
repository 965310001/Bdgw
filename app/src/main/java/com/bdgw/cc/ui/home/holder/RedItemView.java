package com.bdgw.cc.ui.home.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.home.bean.RedItemInfo;
import com.trecyclerview.holder.AbsHolder;
import com.trecyclerview.holder.AbsItemHolder;

import me.goldze.common.utils.DateUtils;

/**
 * @author GuoFeng
 * @date : 2019/1/22 10:23
 * @description:
 */
public class RedItemView extends AbsItemHolder<RedItemInfo.CashgiftsBean, RedItemView.ViewHolder> {

    public RedItemView(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.menu_item_red;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull RedItemInfo.CashgiftsBean bean) {
        holder.tvTime.setText(String.format("%s-%s", DateUtils.getFormatDate(bean.getEffective(), "yyyy-MM-dd"),
                DateUtils.getFormatDate(bean.getExpires(), "yyyy-MM-dd")));
        holder.tvPrice.setText(Html.fromHtml(String.
                format("<body style='font-size:15px;color:#fa800a'>%s<br/>%s</body>", bean.getCondition(), "优惠券")));
        holder.tvName.setText(bean.getName());
    }

    class ViewHolder extends AbsHolder {

        private  TextView tvName, tvPrice, tvTime;

        private ViewHolder(View view) {
            super(view);
            tvName = getViewById(R.id.tv_name);
            tvPrice = getViewById(R.id.tv_price);
            tvTime = getViewById(R.id.tv_time);
        }
    }
}
