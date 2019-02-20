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
}
