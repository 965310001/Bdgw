package com.bdgw.cc.ui.shopping.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

import me.goldze.common.base.bean.BaseBean;

/**
 * @author GuoFeng
 * @date : 2019/1/24 20:59
 * @description:
 */
@Entity
public class GoodsInfo extends BaseBean {

    @Id
    private String goodsId;
    private String goodsName;
    private String goodsPrice;//商品当前价格
    private String goodsOldPrice;//老价格
    private String goodsMasterImg;//商品主图
    private String praiseRate;//好评率
    private String commentCount;//用户点评数
    private int num;//数量

    //该字段不入库
    @Transient
    private boolean checked;//是否选择

    @Transient//该字段不入库
    private List<String> goodsHeadImg;//商品头图
    String vendorId;//供应商ID

    @Generated(hash = 931718353)
    public GoodsInfo(String goodsId, String goodsName, String goodsPrice, String goodsOldPrice, String goodsMasterImg, String praiseRate,
            String commentCount, int num, String vendorId) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsOldPrice = goodsOldPrice;
        this.goodsMasterImg = goodsMasterImg;
        this.praiseRate = praiseRate;
        this.commentCount = commentCount;
        this.num = num;
        this.vendorId = vendorId;
    }

    @Generated(hash = 1227172248)
    public GoodsInfo() {
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsOldPrice() {
        return goodsOldPrice;
    }

    public void setGoodsOldPrice(String goodsOldPrice) {
        this.goodsOldPrice = goodsOldPrice;
    }

    public String getGoodsMasterImg() {
        return goodsMasterImg;
    }

    public void setGoodsMasterImg(String goodsMasterImg) {
        this.goodsMasterImg = goodsMasterImg;
    }

    public String getPraiseRate() {
        return praiseRate;
    }

    public void setPraiseRate(String praiseRate) {
        this.praiseRate = praiseRate;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<String> getGoodsHeadImg() {
        return goodsHeadImg;
    }

    public void setGoodsHeadImg(List<String> goodsHeadImg) {
        this.goodsHeadImg = goodsHeadImg;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    @Override
    public String toString() {
        return "GoodsInfo{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice='" + goodsPrice + '\'' +
                ", goodsOldPrice='" + goodsOldPrice + '\'' +
                ", goodsMasterImg='" + goodsMasterImg + '\'' +
                ", praiseRate='" + praiseRate + '\'' +
                ", commentCount='" + commentCount + '\'' +
                ", num=" + num +
                ", checked=" + checked +
                ", goodsHeadImg=" + goodsHeadImg +
                ", vendorId='" + vendorId + '\'' +
                '}';
    }

}
