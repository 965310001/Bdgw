package com.bdgw.cc.ui.home;

import android.content.Context;
import android.support.annotation.Nullable;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.home.bean.GoodsConfigBean;

import java.util.List;

import me.goldze.common.adapter.BaseRecyclerAdapter;
import me.goldze.common.base.mvvm.base.BaseViewHolder;

/**
 * @author GuoFeng
 * @date : 2019/1/28 17:49
 * @description:
 */
public class GoodsConfigAdapter extends BaseRecyclerAdapter<GoodsConfigBean> {

    public GoodsConfigAdapter(Context context, @Nullable List<GoodsConfigBean> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    protected void convert(BaseViewHolder holder, GoodsConfigBean data, int position, List<Object> payloads) {
        holder.setText(R.id.tv_config_key, data.getKeyProp());
        holder.setText(R.id.tv_config_value, data.getValue());
    }
}
