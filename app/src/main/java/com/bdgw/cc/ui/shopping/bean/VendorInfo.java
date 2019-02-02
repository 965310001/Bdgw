package com.bdgw.cc.ui.shopping.bean;

import java.util.List;

import me.goldze.common.base.bean.BaseBean;

/**
 * @author GuoFeng
 * @date : 2019/1/24 10:46
 * @description: 供应商
 */
public class VendorInfo extends BaseBean {

    private String vendorId;//供应商ID
    public String vendorName;//供应商名称
    public List<GoodsInfo> goodsInfos;
    public boolean checked;

    public VendorInfo() {
    }

    public VendorInfo(String vendorName, List<GoodsInfo> goodsInfos) {
        this.vendorName = vendorName;
        this.goodsInfos = goodsInfos;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public List<GoodsInfo> getGoodsInfos() {
        return goodsInfos;
    }

    public void setGoodsInfos(List<GoodsInfo> goodsInfos) {
        this.goodsInfos = goodsInfos;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
