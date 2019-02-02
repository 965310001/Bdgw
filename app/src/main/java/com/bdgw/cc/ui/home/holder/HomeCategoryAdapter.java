package com.bdgw.cc.ui.home.holder;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Override
    protected void convert(BaseViewHolder holder, final CatagoryInfo.Catagory catagory, int position, List payloads) {
        ImageView categroyIcon = holder.getView(R.id.iv_classify);
        TextView categroyName = holder.getView(R.id.tv_classify);

        categroyName.setText(catagory.title);
        ImageUtils.loadImage(categroyIcon, catagory.resId);
    }
}
