package com.bdgw.cc.ui.home.bean;

import com.bdgw.cc.ui.shopping.bean.GoodsInfo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import me.goldze.common.base.bean.BaseBean;

/**
 * @author GuoFeng
 * @date : 2019/1/22 14:57
 * @description: 订单类
 */
public class OrderInfo extends BaseBean {

    /**
     * orders : [{"id":"110711","total":12124,"thumb":"http://www.biandanwang.cc/2018/03/29/1_05756383727350027_60.jpg","shippingprice":"144.00","status":0},{"id":"110711","total":12124,"thumb":"http://www.biandanwang.cc/2018/03/29/1_05756383727350027_60.jpg","shippingprice":"144.00","status":0},{"id":"110711","total":12124,"thumb":"http://www.biandanwang.cc/2018/03/29/1_05756383727350027_60.jpg","shippingprice":"144.00","status":0},{"id":"110711","total":12124,"thumb":"http://www.biandanwang.cc/2018/03/29/1_05756383727350027_60.jpg","shippingprice":"144.00","status":0},{"id":"110711","total":12124,"thumb":"http://www.biandanwang.cc/2018/03/29/1_05756383727350027_60.jpg","shippingprice":"144.00","status":0},{"id":"110711","total":12124,"thumb":"http://www.biandanwang.cc/2018/03/29/1_05756383727350027_60.jpg","shippingprice":"144.00","status":0},{"id":"110711","total":12124,"thumb":"http://www.biandanwang.cc/2018/03/29/1_05756383727350027_60.jpg","shippingprice":"144.00","status":0}]
     * paged : {"total":34,"page":"1","size":"10","more":1}
     * error_code : 0
     */

    private PagedBean paged;
    private int error_code;
    private List<OrdersBean> orders;

    public PagedBean getPaged() {
        return paged;
    }

    public void setPaged(PagedBean paged) {
        this.paged = paged;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<OrdersBean> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersBean> orders) {
        this.orders = orders;
    }

    public static class PagedBean {
        /**
         * total : 34
         * page : 1
         * size : 10
         * more : 1
         */

        private int total;
        private String page;
        private String size;
        private int more;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public int getMore() {
            return more;
        }

        public void setMore(int more) {
            this.more = more;
        }
    }

    public static class OrdersBean extends BaseBean {
        /**
         * id : 110711
         * total : 12124
         * thumb : http://www.biandanwang.cc/2018/03/29/1_05756383727350027_60.jpg
         * shippingprice : 144.00
         * status : 0
         */

        private String id;
        private int total;
        private String thumb;
        @SerializedName("shipping_fee")
        private String shippingprice;
        private String goodsName;
        private int status;
        private int goodsid;
        /*订单编号*/
        private long sn;

        @SerializedName("order_goods")
        private List<GoodsInfo> data;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getShippingprice() {
            return shippingprice;
        }

        public void setShippingprice(String shippingprice) {
            this.shippingprice = shippingprice;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(int goodsid) {
            this.goodsid = goodsid;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public List<GoodsInfo> getData() {
            return data;
        }

        public void setData(List<GoodsInfo> data) {
            this.data = data;
        }

        public long getSn() {
            return sn;
        }

        public void setSn(long sn) {
            this.sn = sn;
        }
    }
}
