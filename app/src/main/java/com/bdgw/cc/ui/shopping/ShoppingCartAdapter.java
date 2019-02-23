package com.bdgw.cc.ui.shopping;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.ApiRepo;
import com.bdgw.cc.ui.Constants;
import com.bdgw.cc.ui.UserInfo;
import com.bdgw.cc.ui.shopping.bean.GoodsInfo;
import com.bdgw.cc.ui.shopping.bean.VendorInfo;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.goldze.common.adapter.BaseRecyclerAdapter;
import me.goldze.common.base.event.LiveBus;
import me.goldze.common.base.mvvm.base.BaseViewHolder;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.http.rx.RxSubscriber;
import me.goldze.common.utils.ActivityToActivity;
import me.goldze.common.utils.ImageUtils;
import me.goldze.common.utils.ToastUtils;
import me.goldze.common.widget.CountClickView;

/**
 * @author GuoFeng
 * @date : 2019/1/24 10:48
 * @description: 购物车Adapter
 */
public class ShoppingCartAdapter extends BaseRecyclerAdapter<VendorInfo> implements View.OnClickListener {

    private boolean isEdit;

    public void setEdit(boolean edit) {
        isEdit = edit;
        notifyDataSetChanged();
    }

    public ShoppingCartAdapter(Context context, @Nullable List<VendorInfo> list) {
        super(context, list, R.layout.market_item_of_shopping_cart_list);
    }

    @Override
    protected void convert(BaseViewHolder holder, VendorInfo data, int position, List<Object> payloads) {
        holder.getView(R.id.ll_vendor).setVisibility(View.GONE);
        if (null != data.goodsInfos && data.goodsInfos.size() > 0) {
            holder.getView(R.id.ll_vendor).setVisibility(View.VISIBLE);
            holder.setText(R.id.tv_vendor_name, data.getVendorName());
            LinearLayout goodsLayout = holder.getView(R.id.goods_list);
            ImageView ivVendor = holder.getView(R.id.iv_check_vendor);
            ivVendor.setSelected(data.checked);
            ivVendor.setTag(holder.getLayoutPosition());
            ivVendor.setOnClickListener(this);
            goodsLayout.removeAllViews();

            for (int i = 0; i < data.goodsInfos.size(); i++) {
                View view = View.inflate(getContext(), R.layout.market_item_of_shopping_cart_list_goods_list, null);
                new ViewHolder(view).onBindData(holder.getLayoutPosition(), i);
                goodsLayout.addView(view);

                view.setTag(getList().get(holder.getLayoutPosition()).getGoodsInfos().get(i).getGoodsId());
//            getList().get(holder.getLayoutPosition()).getGoodsInfos().get(i).getGoodsId();
                view.setOnClickListener(this);
            }
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_check_vendor) {
            //供应商被点击了
            ShoppingCartUtils.checkVendor(getList(), (Integer) v.getTag());
            updateCart();
        } else if (v.getId() == R.id.iv_goods_check) {
            //商品被点击了
            String[] tag = ((String) v.getTag()).split(",");
            ShoppingCartUtils.checkGoods(getList(), Integer.parseInt(tag[0]), Integer.parseInt(tag[1]));
            updateCart();
        } else {
            KLog.i(v.getTag());
            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(v.getTag()));
            ActivityToActivity.toActivity(ARouterConfig.home.SHOPPINGDETAILSACTIVITY, map);
        }
    }

    public class ViewHolder {
        @BindView(R.id.iv_goods)
        ImageView ivGoods;

        @BindView(R.id.iv_goods_check)
        ImageView ivGoodsCheck;

        @BindView(R.id.tv_goods_name)
        TextView tvGoodsName;

        @BindView(R.id.tv_price)
        TextView tvPrice;

        @BindView(R.id.ccv_click)
        CountClickView ccvClick;


        ViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }

        void onBindData(final int parent, final int child) {

            final GoodsInfo goodsInfo = getList().get(parent).getGoodsInfos().get(child);

            ImageUtils.loadImage(ivGoods, goodsInfo.getGoodsMasterImg());
            ivGoodsCheck.setSelected(goodsInfo.isChecked());
            ivGoodsCheck.setTag(parent + "," + child);
            ivGoodsCheck.setOnClickListener(ShoppingCartAdapter.this);
            tvGoodsName.setText(goodsInfo.getGoodsName());
            tvPrice.setText(String.format("¥%s", goodsInfo.getGoodsPrice()));
            ccvClick.setMinCount(1);
            ccvClick.setMaxCount(99);
            ccvClick.setInput(true);
            ccvClick.setCurrCount(goodsInfo.getNum() < 1 ? 1 : goodsInfo.getNum());
            ccvClick.setAfterClickListener(new CountClickView.OnClickAfterListener() {
                @Override
                public void onAfter(int value) {
                    //修改源数据
                    getList().get(parent).getGoodsInfos().get(child).setNum(value);
                    //修改本地数据库数据
                    goodsInfo.setNum(value);
                    ShoppingCartUtils.updateCartGoodsNum(goodsInfo);
                    //修改本地数据库
                    /*updateCart();*/

                    KLog.i("修改数据库的数量");
                    updateCart(String.valueOf(goodsInfo.getId()), value);
                }

                @Override
                public void onMin() {
                }
            });
            tvPrice.setVisibility(isEdit ? View.GONE : View.VISIBLE);
            ccvClick.setVisibility(isEdit ? View.GONE : View.VISIBLE);
        }
    }

    private void updateCart() {
        //刷新购物车
        LiveBus.getDefault().postEvent(Constants.Shopping.EVENT_SHOPPING_CART_CHANGED,
                Constants.Shopping.EVENT_SHOPPING_CART_CHANGED,
                Constants.Shopping.EVENT_SHOPPING_CART_CHANGED);
    }

    /*修改购物车商品数量*/
    private void updateCart(String id, int amount) {
        ApiRepo.updateCart(id, amount).subscribeWith(new RxSubscriber<UserInfo>() {

            @Override
            public void onSuccess(UserInfo response) {
                KLog.i(response.getErrorMsg() + response.getError_desc());
                if (!response.isSuccess()) {
                    ToastUtils.showLong(response.getErrorMsg());
                } else {
                    /*修改成功*/
                    updateCart();
                }
            }

            @Override
            public void onFailure(String msg) {
                KLog.i(msg);
                ToastUtils.showLong(msg);
            }

            @Override
            public void onError(Throwable t) {
                KLog.i(t.getMessage());
                ToastUtils.showLong("请稍后再试");
            }
        });
    }
}
