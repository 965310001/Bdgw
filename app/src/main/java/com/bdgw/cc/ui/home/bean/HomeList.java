package com.bdgw.cc.ui.home.bean;

import com.bdgw.cc.ui.shopping.bean.GoodsInfo;

import java.util.List;

import me.goldze.common.base.bean.BaseBean;

/**
 * @author GuoFeng
 * @date : 2019/1/20 14:40
 * @description:
 */
public class HomeList extends BaseBean {

    /**
     * hot_products : [{"goods_id":1020,"goods_name":"双频智能无线路由器","goods_img":"2018/03/30/1_05757240454179830.jpg","is_promote":0,"promote_start_date":0,"promote_end_date":0,"goods_number":135,"goods_thumb":"2018/03/30/1_05757240454179830.jpg","sales_count":56,"show_price":"126.30","goods_image":"http://www.biandanwang.cc/2018/03/30/1_05757240454179830.jpg"},{"goods_id":4699,"goods_name":"电音 DM-099 语音网络K歌金属话筒 金色","goods_img":"2018/05/04/1_05787592907253831.png","is_promote":0,"promote_start_date":0,"promote_end_date":0,"goods_number":99,"goods_thumb":"2018/05/04/1_05787592907253831.png","sales_count":0,"show_price":"48.40","goods_image":"http://www.biandanwang.cc/2018/05/04/1_05787592907253831.png"}]
     * recently_products : [{"goods_id":6177,"goods_name":"现代 HYSSD固态硬盘 2.5寸SATA3.0 240G","goods_img":"2018/05/08/1_05791201525249978.png","is_promote":0,"promote_start_date":0,"promote_end_date":0,"goods_number":100,"goods_thumb":"2018/05/08/1_05791201525249978.png","sales_count":2,"show_price":"215.00","goods_image":"http://www.biandanwang.cc/2018/05/08/1_05791201525249978.png"}]
     * good_products : [{"goods_id":956,"goods_name":"腾达 S105 5口百兆交换机","goods_img":"2018/03/29/1_05756392311540406.jpg","is_promote":0,"promote_start_date":0,"promote_end_date":0,"goods_number":98,"goods_thumb":"2018/03/29/1_05756392311540406.jpg","sales_count":29,"show_price":"0.01","goods_image":"http://www.biandanwang.cc/2018/03/29/1_05756392311540406.jpg"},{"goods_id":7971,"goods_name":"瀚美奇 H220W 21.5英寸窄边框电脑显示器","goods_img":"2018/05/17/1_05798890033801816.png","is_promote":1,"promote_start_date":1545422040,"promote_end_date":1546239240,"goods_number":98,"goods_thumb":"2018/05/17/1_05798890033801816.png","sales_count":16,"show_price":"480.00","goods_image":"http://www.biandanwang.cc/2018/05/17/1_05798890033801816.png"}]
     * error_code : 0
     * debug_id : 5c67e690df33e
     */

    private int error_code;
    private List<GoodsInfo> hot_products;
    private List<GoodsInfo> recently_products;
    private List<GoodsInfo> good_products;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<GoodsInfo> getHot_products() {
        return hot_products;
    }

    public void setHot_products(List<GoodsInfo> hot_products) {
        this.hot_products = hot_products;
    }

    public List<GoodsInfo> getRecently_products() {
        return recently_products;
    }

    public void setRecently_products(List<GoodsInfo> recently_products) {
        this.recently_products = recently_products;
    }

    public List<GoodsInfo> getGood_products() {
        return good_products;
    }

    public void setGood_products(List<GoodsInfo> good_products) {
        this.good_products = good_products;
    }

    public static class GoodProductsBean extends BaseBean{
        /**
         * goods_id : 956
         * goods_name : 腾达 S105 5口百兆交换机
         * goods_img : 2018/03/29/1_05756392311540406.jpg
         * is_promote : 0
         * promote_start_date : 0
         * promote_end_date : 0
         * goods_number : 98
         * goods_thumb : 2018/03/29/1_05756392311540406.jpg
         * sales_count : 29
         * show_price : 0.01
         * goods_image : http://www.biandanwang.cc/2018/03/29/1_05756392311540406.jpg
         */

        private int goods_id;
        private String goods_name;
        private String goods_img;
        private int is_promote;
        private int promote_start_date;
        private int promote_end_date;
        private int goods_number;
        private String goods_thumb;
        private int sales_count;
        private String show_price;
        private String goods_image;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public int getIs_promote() {
            return is_promote;
        }

        public void setIs_promote(int is_promote) {
            this.is_promote = is_promote;
        }

        public int getPromote_start_date() {
            return promote_start_date;
        }

        public void setPromote_start_date(int promote_start_date) {
            this.promote_start_date = promote_start_date;
        }

        public int getPromote_end_date() {
            return promote_end_date;
        }

        public void setPromote_end_date(int promote_end_date) {
            this.promote_end_date = promote_end_date;
        }

        public int getGoods_number() {
            return goods_number;
        }

        public void setGoods_number(int goods_number) {
            this.goods_number = goods_number;
        }

        public String getGoods_thumb() {
            return goods_thumb;
        }

        public void setGoods_thumb(String goods_thumb) {
            this.goods_thumb = goods_thumb;
        }

        public int getSales_count() {
            return sales_count;
        }

        public void setSales_count(int sales_count) {
            this.sales_count = sales_count;
        }

        public String getShow_price() {
            return show_price;
        }

        public void setShow_price(String show_price) {
            this.show_price = show_price;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }
    }
}
