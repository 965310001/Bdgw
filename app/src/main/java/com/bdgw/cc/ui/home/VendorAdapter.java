//package com.bdgw.cc.ui.home;
//
//import android.content.Context;
//import android.graphics.Paint;
//import android.support.annotation.Nullable;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bdgw.cc.R;
//import com.bdgw.cc.ui.Constants;
//import com.bdgw.cc.ui.shopping.ShoppingCartUtils;
//import com.bdgw.cc.ui.shopping.bean.VendorInfo;
//
//import java.util.List;
//
//import me.goldze.common.adapter.BaseRecyclerAdapter;
//import me.goldze.common.base.event.LiveBus;
//import me.goldze.common.base.mvvm.base.BaseViewHolder;
//import me.goldze.common.utils.ImageUtils;
//
///**
// * @author GuoFeng
// * @date : 2019/1/24 17:14
// * @description:
// */
//public class VendorAdapter extends BaseRecyclerAdapter<VendorInfo.GoodsInfo> {
//
//    public VendorAdapter(Context context, @Nullable List<VendorInfo.GoodsInfo> list) {
//        super(context, list, R.layout.item_of_vendor);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder holder, final VendorInfo.GoodsInfo data, int position, List<Object> payloads) {
//        ImageUtils.loadImage((ImageView) holder.getView(R.id.iv_img), data.getGoodsMasterImg());
//        holder.setText(R.id.tv_title, data.getGoodsName());
//        holder.setText(R.id.tv_price, data.getGoodsPrice());
//        TextView tvOldPrice = holder.getView(R.id.tv_old_price);
//        tvOldPrice.setText(data.getGoodsOldPrice());
//
////        final GoodsInfo goodsInfo = data;
//        //设置文字中间一条横线,
//        tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        holder.getView(R.id.iv_add_cart).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //加入购物车
//                ShoppingCartUtils.addCartGoods(data);
////                EventBusUtils.sendEvent(new Event(EventAction.EVENT_SHOPPING_CART_CHANGED));
//                LiveBus.getDefault().postEvent(Constants.Shopping.EVENT_SHOPPING_CART_CHANGED, Constants.Shopping.EVENT_SHOPPING_CART_CHANGED,
//                        Constants.Shopping.EVENT_SHOPPING_CART_CHANGED);
//            }
//        });
//    }
//}
