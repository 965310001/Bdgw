package com.bdgw.cc.ui.home.bean;

import java.util.List;

import me.goldze.common.base.bean.BaseBean;

/**
 * @author GuoFeng
 * @date : 2019/1/21 18:09
 * @description: 红包Bean
 */
public class RedItemInfo extends BaseBean {


    /**
     * cashgifts : [{"status":0,"id":31,"name":"注册有礼，首单可用","value":9,"effective":1548007547,"expires":1550684760,"condition":"0.00"},{"status":0,"id":32,"name":"注册有礼，首单可用","value":9,"effective":1548007547,"expires":1550684760,"condition":"0.00"},{"status":0,"id":33,"name":"7 - 选购商品，送你大红包","value":50,"effective":1548016761,"expires":1550686020,"condition":"999.00"},{"status":0,"id":34,"name":"7 - 选购商品，送你大红包","value":50,"effective":1548016761,"expires":1550686020,"condition":"999.00"},{"status":0,"id":35,"name":"7 - 选购商品，送你大红包","value":50,"effective":1548016761,"expires":1550686020,"condition":"999.00"},{"status":0,"id":36,"name":"8 - 添加购物车，好礼送不停","value":50,"effective":1548016755,"expires":1550686200,"condition":"999.00"},{"status":0,"id":37,"name":"8 - 添加购物车，好礼送不停","value":50,"effective":1548016755,"expires":1550686200,"condition":"999.00"},{"status":0,"id":38,"name":"8 - 添加购物车，好礼送不停","value":50,"effective":1548016755,"expires":1550686200,"condition":"999.00"},{"status":0,"id":39,"name":"8 - 添加购物车，好礼送不停","value":50,"effective":1548016755,"expires":1550686200,"condition":"999.00"},{"status":0,"id":40,"name":"8 - 添加购物车，好礼送不停","value":50,"effective":1548016755,"expires":1550686200,"condition":"999.00"}]
     * paged : {"total":0,"page":"1","size":"10","more":0}
     * error_code : 0
     */

    private PagedBean paged;
    private int error_code;
    private List<CashgiftsBean> cashgifts;

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

    public List<CashgiftsBean> getCashgifts() {
        return cashgifts;
    }

    public void setCashgifts(List<CashgiftsBean> cashgifts) {
        this.cashgifts = cashgifts;
    }

    public static class PagedBean {
        /**
         * total : 0
         * page : 1
         * size : 10
         * more : 0
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

    public static class CashgiftsBean {
        /**
         * status : 0
         * id : 31
         * name : 注册有礼，首单可用
         * value : 9
         * effective : 1548007547
         * expires : 1550684760
         * condition : 0.00
         */

        private int status;
        private int id;
        private String name;
        private int value;
        private int effective;
        private int expires;
        private String condition;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getEffective() {
            return effective;
        }

        public void setEffective(int effective) {
            this.effective = effective;
        }

        public int getExpires() {
            return expires;
        }

        public void setExpires(int expires) {
            this.expires = expires;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }
    }
}
