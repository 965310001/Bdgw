package com.bdgw.cc.ui.home.holder;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.Constants;
import com.bdgw.cc.ui.shopping.ShoppingCartUtils;
import com.bdgw.cc.ui.shopping.bean.GoodsInfo;
import com.trecyclerview.holder.AbsHolder;
import com.trecyclerview.holder.AbsItemHolder;

import me.goldze.common.base.event.LiveBus;
import me.goldze.common.utils.DisplayUtil;
import me.goldze.common.utils.ImageUtils;

/**
 * @author GuoFeng
 * @date : 2019/1/24 17:04
 * @description:
 */
public class VendorItemView extends AbsItemHolder<GoodsInfo, VendorItemView.ViewHolder> {

    private int commonWidth;

    public VendorItemView(Context context) {
        super(context);
        commonWidth = (int) ((float) DisplayUtil.getScreenWidth(mContext) / 2);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_of_vendor;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new VendorItemView.ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull final GoodsInfo data) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                commonWidth, (int) (0.56 * commonWidth));
//        holder.mLiveLayout.setLayoutParams(params);

        ImageUtils.loadImage((ImageView) holder.getViewById(R.id.iv_img), data.getGoodsMasterImg());
        ((TextView) holder.getViewById(R.id.tv_title)).setText(data.getGoodsName());
        ((TextView) holder.getViewById(R.id.tv_price)).setText(data.getGoodsPrice());
        ((TextView) holder.getViewById(R.id.tv_old_price)).setText(data.getGoodsOldPrice());

//        holder.setText(R.id.tv_price, data.getGoodsPrice());
        TextView tvOldPrice = holder.getViewById(R.id.tv_old_price);
//        tvOldPrice.setText(data.getGoodsOldPrice());

//        final GoodsInfo goodsInfo = data;
        //设置文字中间一条横线,
        tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.getViewById(R.id.iv_add_cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //加入购物车
                ShoppingCartUtils.addCartGoods(data);
//                EventBusUtils.sendEvent(new Event(EventAction.EVENT_SHOPPING_CART_CHANGED));
                LiveBus.getDefault().postEvent(Constants.Shopping.EVENT_SHOPPING_CART_CHANGED, Constants.Shopping.EVENT_SHOPPING_CART_CHANGED,
                        Constants.Shopping.EVENT_SHOPPING_CART_REFRESH);
            }
        });


      /*  GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2);

//        holder.setText(R.id.tv_vendor_name, vendorName);
        getVendorName();
        KLog.i(getVendorName());

//        list.addAll(vendorInfo.getGoodsInfos());
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(adapter);
        holder.recyclerView.setNestedScrollingEnabled(false);
        adapter.setList(vendorInfo.goodsInfos);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                ActivityToActivity.toActivity(ARouterConfig.home.MAIN_MENU_ITEM[position]);
            }
        });*/
    }

    public static class ViewHolder extends AbsHolder {

        private RecyclerView recyclerView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = getViewById(R.id.recycler_view);
        }
    }
}
