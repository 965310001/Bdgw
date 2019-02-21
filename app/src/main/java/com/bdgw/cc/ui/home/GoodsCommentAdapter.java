package com.bdgw.cc.ui.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.home.bean.GoodsComment;

import java.util.List;

import me.goldze.common.adapter.BaseRecyclerAdapter;
import me.goldze.common.base.mvvm.base.BaseViewHolder;
import me.goldze.common.utils.ImageUtils;

/**
 * @author GuoFeng
 * @date : 2019/1/28 16:29
 * @description:
 */
public class GoodsCommentAdapter extends BaseRecyclerAdapter<GoodsComment> {

    public GoodsCommentAdapter(Context context, @Nullable List<GoodsComment> list) {
        super(context, list, R.layout.item_of_goods_comment_list);
    }

    @Override
    protected void convert(BaseViewHolder holder, GoodsComment data, int position, List<Object> payloads) {
        ImageUtils.loadImageCircle((ImageView) holder.getView(R.id.iv_head), data.getUserHead());
        holder.setText(R.id.tv_name, data.getUserName());
        holder.setText(R.id.tv_comment, data.getComment());
        holder.setText(R.id.tv_time, data.getTime());
    }
}
