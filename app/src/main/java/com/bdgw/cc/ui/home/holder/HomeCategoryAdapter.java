package com.bdgw.cc.ui.home.holder;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bdgw.cc.R;

import java.util.List;

import me.goldze.common.adapter.BaseRecyclerAdapter;
import me.goldze.common.base.mvvm.base.BaseViewHolder;
import me.goldze.common.utils.ImageUtils;

/**
 * @author GuoFeng
 * @date : 2019/1/20 16:18
 * @description:
 */
public class HomeCategoryAdapter extends BaseRecyclerAdapter<CatagoryInfo.Catagory> {

    public HomeCategoryAdapter(Context context, @Nullable List<CatagoryInfo.Catagory> list) {
        super(context, list, R.layout.item_classify);
    }

    ImageView categroyIcon;

    @Override
    protected void convert(BaseViewHolder holder, final CatagoryInfo.Catagory data, int position, List payloads) {
        categroyIcon = holder.getView(R.id.iv_classify);
        holder.setText(R.id.tv_classify, data.getTitle());
        // TODO: 2019/2/20 删除
        data.setLink("https://img14.360buyimg.com/focus/s140x140_jfs/t27136/183/1628977274/31007/a6f7ed55/5be6ebd8Nb07ef492.png");
        ImageUtils.loadImage(categroyIcon, data.getLink());
    }
}
