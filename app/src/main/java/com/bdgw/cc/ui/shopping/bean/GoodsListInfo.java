package com.bdgw.cc.ui.shopping.bean;

import com.google.gson.annotations.SerializedName;

import me.goldze.common.base.bean.BaseBean;

/**
 * @author GuoFeng
 * @date : 2019/2/20 10:49
 * @description:
 */
public class GoodsListInfo extends BaseBean {

    @SerializedName("product")
    private GoodsInfo data;


    public GoodsInfo getData() {
        return data;
    }

    public void setData(GoodsInfo data) {
        this.data = data;
    }
}
