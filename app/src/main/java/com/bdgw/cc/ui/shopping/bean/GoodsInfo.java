package com.bdgw.cc.ui.shopping.bean;

import com.google.gson.annotations.SerializedName;

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
    @SerializedName("product")
    private GoodsInfo data;
    @Id
    @SerializedName("goods_id")
    private long goodsId;
//    private String goodsId;
    @SerializedName("goods_name")
    private String goodsName;
    @SerializedName("show_price")
    private String goodsPrice;//商品当前价格
    private String goodsOldPrice;//老价格
    @SerializedName("goods_image")
    private String goodsMasterImg;//商品主图
    private String praiseRate;//好评率
    private String commentCount;//用户点评数
    @SerializedName("goods_number")
    private int num;//数量

//    private String goods_img;
    private int is_promote;
    private int promote_start_date;
    private int promote_end_date;
//    private int goods_number;
    private String goods_thumb;
    private int sales_count;
//    private String show_price;
    /*private String goods_image;*/

    //该字段不入库
    @Transient
    private boolean checked;//是否选择

    @Transient//该字段不入库
    private List<String> goodsHeadImg;//商品头图
    String vendorId;//供应商ID

    @Generated(hash = 325643493)
    public GoodsInfo(long goodsId, String goodsName, String goodsPrice, String goodsOldPrice, String goodsMasterImg, String praiseRate,
            String commentCount, int num, int is_promote, int promote_start_date, int promote_end_date, String goods_thumb, int sales_count,
            String vendorId) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsOldPrice = goodsOldPrice;
        this.goodsMasterImg = goodsMasterImg;
        this.praiseRate = praiseRate;
        this.commentCount = commentCount;
        this.num = num;
        this.is_promote = is_promote;
        this.promote_start_date = promote_start_date;
        this.promote_end_date = promote_end_date;
        this.goods_thumb = goods_thumb;
        this.sales_count = sales_count;
        this.vendorId = vendorId;
    }

    @Generated(hash = 1227172248)
    public GoodsInfo() {
    }

    public GoodsInfo getData() {
        return data;
    }

    public void setData(GoodsInfo data) {
        this.data = data;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
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

    public int getIs_promote() {
        return this.is_promote;
    }

    public void setIs_promote(int is_promote) {
        this.is_promote = is_promote;
    }

    public int getPromote_start_date() {
        return this.promote_start_date;
    }

    public void setPromote_start_date(int promote_start_date) {
        this.promote_start_date = promote_start_date;
    }

    public int getPromote_end_date() {
        return this.promote_end_date;
    }

    public void setPromote_end_date(int promote_end_date) {
        this.promote_end_date = promote_end_date;
    }

    public String getGoods_thumb() {
        return this.goods_thumb;
    }

    public void setGoods_thumb(String goods_thumb) {
        this.goods_thumb = goods_thumb;
    }

    public int getSales_count() {
        return this.sales_count;
    }

    public void setSales_count(int sales_count) {
        this.sales_count = sales_count;
    }

}
