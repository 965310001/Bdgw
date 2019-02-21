package com.bdgw.cc.ui.shopping.bean;

import com.bdgw.cc.ui.bean.Paged;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import me.goldze.common.base.bean.BaseBean;

/**
 * @author GuoFeng
 * @date : 2019/2/20 10:49
 * @description:
 */
public class GoodsListInfo extends BaseBean {

    @SerializedName("product")
    private GoodsInfo data;

    @SerializedName("products")
    private List<GoodsInfo> goodsInfos;

    /*添加到购物车返回的数据*/
    @SerializedName("cart_goods")
    private GoodsInfo cartGoods;

    private int error_code;
    private String error_desc;

    private Paged paged;

    public GoodsInfo getData() {
        return data;
    }

    public void setData(GoodsInfo data) {
        this.data = data;
    }

    public List<GoodsInfo> getGoodsInfos() {
        return goodsInfos;
    }

    public void setGoodsInfos(List<GoodsInfo> goodsInfos) {
        this.goodsInfos = goodsInfos;
    }

    public Paged getPaged() {
        return paged;
    }

    public void setPaged(Paged paged) {
        this.paged = paged;
    }

    public GoodsInfo getCartGoods() {
        return cartGoods;
    }

    public void setCartGoods(GoodsInfo cartGoods) {
        this.cartGoods = cartGoods;
    }

    public String getError_desc() {
        return error_desc;
    }

    public void setError_desc(String error_desc) {
        this.error_desc = error_desc;
    }

    public boolean isSuccess() {
        return error_code == 0;
    }

    public String getErrorMsg() {
        return error_desc;
    }
}
