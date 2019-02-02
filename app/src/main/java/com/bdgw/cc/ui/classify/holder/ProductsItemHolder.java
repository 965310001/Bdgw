package com.bdgw.cc.ui.classify.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.shopping.bean.GoodsInfo;
import com.trecyclerview.holder.AbsHolder;
import com.trecyclerview.holder.AbsItemHolder;

import me.goldze.common.utils.ImageUtils;

/**
 * @author GuoFeng
 * @date : 2019/1/28 19:20
 * @description:
 */
public class ProductsItemHolder extends AbsItemHolder<GoodsInfo, ProductsItemHolder.ViewHolder> {

    public ProductsItemHolder(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_products;
    }

    @Override
    public ProductsItemHolder.ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductsItemHolder.ViewHolder holder, @NonNull GoodsInfo data) {
        holder.tvName.setText(data.getGoodsName());
        holder.tvPrice.setText(data.getGoodsPrice().concat(" ").concat(data.getGoodsOldPrice()));
        holder.tvEvaluation.setText("100%".concat("好评"));
        holder.tvNum.setText("销量 ".concat(String.valueOf(data.getNum())));
        holder.ivAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2019/1/28   加入购物车
            }
        });

        ImageUtils.loadImage(holder.ivIcon, data.getGoodsMasterImg());
    }

    class ViewHolder extends AbsHolder {
        private TextView tvName, tvPrice, tvEvaluation, tvNum;
        private ImageView ivIcon, ivAddCart;

        private ViewHolder(View itemView) {
            super(itemView);
            tvName = getViewById(R.id.tv_name);
            ivIcon = getViewById(R.id.iv_icon);
            tvPrice = getViewById(R.id.tv_price);
            tvEvaluation = getViewById(R.id.tv_evaluation);
            tvNum = getViewById(R.id.tv_num);
            ivAddCart = getViewById(R.id.iv_add_cart);
        }
    }

}
