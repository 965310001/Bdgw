package com.bdgw.cc.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;

import com.bdgw.cc.R;

import java.util.List;

import me.goldze.common.adapter.BaseRecyclerAdapter;
import me.goldze.common.base.event.LiveBus;
import me.goldze.common.base.mvvm.base.BaseViewHolder;

/**
 * @author GuoFeng
 * @date : 2019/1/23 10:45
 * @description: 搜索历史Adapter
 */
public class SearchHistoryAdapter extends BaseRecyclerAdapter<String> {

    public SearchHistoryAdapter(Context context, @Nullable List<String> list) {
        super(context, list, R.layout.item_search_history);
    }

    @Override
    protected void convert(BaseViewHolder holder, final String item, final int position, List<Object> payloads) {
        holder.setText(R.id.tv_item, item);

        holder.getView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveBus.getDefault().postEvent(Constants.SEARCH_EVENT_KEY[0],
                        Constants.SEARCH_EVENT_KEY[1], item);
            }
        });

        holder.getView(R.id.img_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveBus.getDefault().postEvent(Constants.SEARCH_EVENT_KEY[2],
                        Constants.SEARCH_EVENT_KEY[3], position);
            }
        });


    }
}
