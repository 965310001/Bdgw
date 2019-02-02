package com.bdgw.cc.ui.classify.holder;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.Constants;
import com.bdgw.cc.ui.classify.bean.ClassificationInfo;
import com.trecyclerview.holder.AbsHolder;
import com.trecyclerview.holder.AbsItemHolder;

import me.goldze.common.base.event.LiveBus;
import me.goldze.common.utils.ResourcesUtils;

/**
 * @author GuoFeng
 * @date : 2019/1/21 10:22
 * @description:
 */
public class ClassificationItemHolder extends AbsItemHolder<ClassificationInfo.DataBean,
        ClassificationItemHolder.ViewHolder> {

    private int selectIndex;

    public ClassificationItemHolder(Context context) {
        super(context);

        LiveBus.getDefault().subscribe(Constants.Listview.POSTION_LEF_EVENT_KEY[0],
                Constants.Listview.POSTION_LEF_EVENT_KEY[1]).observeForever(new Observer<Object>() {
            @Override
            public void onChanged(@Nullable Object o) {
                selectIndex = (int) o;
            }
        });
    }

    @Override
    public int getLayoutResId() {
        return R.layout.classify_item_of_list;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ClassificationInfo.DataBean dataBean) {
        holder.tvText.setText(dataBean.getName());


        boolean isSelect = getPosition(holder) == selectIndex;
        holder.tvText.setTextColor(ResourcesUtils.getInstance().getColor(isSelect ? R.color.white : R.color.black));
        holder.tvText.setBackgroundColor(ResourcesUtils.getInstance().getColor(isSelect ? R.color.orange : R.color.transparent));
    }

    static class ViewHolder extends AbsHolder {
        private final TextView tvText;

        private ViewHolder(View itemView) {
            super(itemView);
            tvText = getViewById(R.id.tv_text);
        }
    }


}
