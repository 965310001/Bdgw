package com.bdgw.cc.ui.classify.bean;

import java.util.List;

import me.goldze.common.base.bean.BaseBean;

/**
 * @author GuoFeng
 * @date : 2019/1/27 18:33
 * @description:
 */
public class DistributionInfo extends BaseBean {


    /**
     * vendors : [{"id":5,"code":"sto_express","name":"申通快递","desc":"江、浙、沪地区首重为15元/KG，其他地区18元/KG， 续重均为5-6元/KG， 云南地区为8元","price":{"first":15,"step":5},"fee":15,"is_additional":true},{"id":6,"code":"post_mail","name":"邮局平邮","desc":"邮局平邮的描述内容。","price":{"first":3.5,"step":2.5},"fee":3.5,"is_additional":true},{"id":9,"code":"zto","name":"中通速递","desc":"说明：保价费按照申报价值的2％交纳，保价费:100<=10000元，保价金额超过10000元的部分无效","price":{"first":10,"step":5},"fee":10,"is_additional":true}]
     * error_code : 0
     */

    private int error_code;
    private List<VendorsBean> vendors;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<VendorsBean> getVendors() {
        return vendors;
    }

    public void setVendors(List<VendorsBean> vendors) {
        this.vendors = vendors;
    }

    public static class VendorsBean extends BaseBean {
        /**
         * id : 5
         * code : sto_express
         * name : 申通快递
         * desc : 江、浙、沪地区首重为15元/KG，其他地区18元/KG， 续重均为5-6元/KG， 云南地区为8元
         * price : {"first":15,"step":5}
         * fee : 15
         * is_additional : true
         */

        private int id;
        private String code;
        private String name;
        private String desc;
        private PriceBean price;
        private Object fee;
        private boolean is_additional;
        private boolean isSelect;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public PriceBean getPrice() {
            return price;
        }

        public void setPrice(PriceBean price) {
            this.price = price;
        }

        public Object getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public boolean isIs_additional() {
            return is_additional;
        }

        public void setIs_additional(boolean is_additional) {
            this.is_additional = is_additional;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public static class PriceBean extends BaseBean {
            /**
             * first : 15
             * step : 5
             */

            private Object first;
            private Object step;

            public Object getFirst() {
                return first;
            }

            public void setFirst(Object first) {
                this.first = first;
            }

            public Object getStep() {
                return step;
            }

            public void setStep(Object step) {
                this.step = step;
            }
        }
    }
}
