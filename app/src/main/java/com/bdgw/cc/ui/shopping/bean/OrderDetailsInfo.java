package com.bdgw.cc.ui.shopping.bean;

import com.bdgw.cc.ui.me.bean.AddressInfo;

import java.util.List;

import me.goldze.common.base.bean.BaseBean;

/**
 * @author GuoFeng
 * @date : 2019/1/30 14:52
 * @description: 订单详情
 */
public class OrderDetailsInfo extends BaseBean {

    private AddressInfo addressInfo;//地址

    private List<GoodsInfo> goodsInfos; //商品

    public AddressInfo getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
    }

    public List<GoodsInfo> getGoodsInfos() {
        return goodsInfos;
    }

    public void setGoodsInfos(List<GoodsInfo> goodsInfos) {
        this.goodsInfos = goodsInfos;
    }

    /**
     * orderInfo : {"orderId":"CS170926155954NO7","baseOrderId":null,"beId":"12","customerId":"b0b2ef71776d465e83e189ca6f50b018","sourceType":1,"status":5,"orderType":1,"currency":10,"currencyAfterDiscount":10,"measureId":1,"measureName":null,"createTime":"2017-09-26T07:59:10Z","paymentTime":"2017-09-26T07:59:22Z","lastUpdateTime":"2017-09-26T08:01:57Z","needAudit":false,"contractId":null}
     * error_code : CBC.0000
     * error_msg : Success
     */

    private OrderInfoBean orderInfo;
    private String error_code;
    private String error_msg;

    public OrderInfoBean getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoBean orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public static class OrderInfoBean {
        /**
         * orderId : CS170926155954NO7
         * baseOrderId : null
         * beId : 12
         * customerId : b0b2ef71776d465e83e189ca6f50b018
         * sourceType : 1
         * status : 5
         * orderType : 1
         * currency : 10
         * currencyAfterDiscount : 10
         * measureId : 1
         * measureName : null
         * createTime : 2017-09-26T07:59:10Z
         * paymentTime : 2017-09-26T07:59:22Z
         * lastUpdateTime : 2017-09-26T08:01:57Z
         * needAudit : false
         * contractId : null
         */

        private String orderId;
        private Object baseOrderId;
        private String beId;
        private String customerId;
        private int sourceType;
        private int status;
        private int orderType;
        private int currency;
        private int currencyAfterDiscount;
        private int measureId;
        private Object measureName;
        private String createTime;
        private String paymentTime;
        private String lastUpdateTime;
        private boolean needAudit;
        private Object contractId;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public Object getBaseOrderId() {
            return baseOrderId;
        }

        public void setBaseOrderId(Object baseOrderId) {
            this.baseOrderId = baseOrderId;
        }

        public String getBeId() {
            return beId;
        }

        public void setBeId(String beId) {
            this.beId = beId;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public int getSourceType() {
            return sourceType;
        }

        public void setSourceType(int sourceType) {
            this.sourceType = sourceType;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }

        public int getCurrency() {
            return currency;
        }

        public void setCurrency(int currency) {
            this.currency = currency;
        }

        public int getCurrencyAfterDiscount() {
            return currencyAfterDiscount;
        }

        public void setCurrencyAfterDiscount(int currencyAfterDiscount) {
            this.currencyAfterDiscount = currencyAfterDiscount;
        }

        public int getMeasureId() {
            return measureId;
        }

        public void setMeasureId(int measureId) {
            this.measureId = measureId;
        }

        public Object getMeasureName() {
            return measureName;
        }

        public void setMeasureName(Object measureName) {
            this.measureName = measureName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getPaymentTime() {
            return paymentTime;
        }

        public void setPaymentTime(String paymentTime) {
            this.paymentTime = paymentTime;
        }

        public String getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(String lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }

        public boolean isNeedAudit() {
            return needAudit;
        }

        public void setNeedAudit(boolean needAudit) {
            this.needAudit = needAudit;
        }

        public Object getContractId() {
            return contractId;
        }

        public void setContractId(Object contractId) {
            this.contractId = contractId;
        }
    }
}
