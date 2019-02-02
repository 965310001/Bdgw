package com.bdgw.cc.ui.me.bean;

import java.util.List;

import me.goldze.common.base.bean.BaseBean;

/**
 * @author GuoFeng
 * @date : 2019/1/28 20:57
 * @description:
 */
public class DefiniteInfo extends BaseBean {


    /**
     * paged : {"total":39,"page":"1","size":"10","more":1}
     * balances : [{"price":"0.35","action":"增加","memo":"余额收益","create_at":1547193901,"status":1},{"price":"0.35","action":"增加","memo":"余额收益","create_at":1547107502,"status":1},{"price":"0.35","action":"增加","memo":"余额收益","create_at":1547021101,"status":1},{"price":"0.35","action":"增加","memo":"余额收益","create_at":1546934701,"status":1},{"price":"0.35","action":"增加","memo":"余额收益","create_at":1546848301,"status":1},{"price":"0.35","action":"增加","memo":"余额收益","create_at":1546761901,"status":1},{"price":"0.35","action":"增加","memo":"余额收益","create_at":1546675501,"status":1},{"price":"0.35","action":"增加","memo":"余额收益","create_at":1546589102,"status":1},{"price":"0.35","action":"增加","memo":"余额收益","create_at":1546502701,"status":1},{"price":"0.35","action":"增加","memo":"余额收益","create_at":1546416301,"status":1}]
     * error_code : 0
     */

    private PagedBean paged;
    private int error_code;
    private List<BalancesBean> balances;

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

    public List<BalancesBean> getBalances() {
        return balances;
    }

    public void setBalances(List<BalancesBean> balances) {
        this.balances = balances;
    }

    public static class PagedBean {
        /**
         * total : 39
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

    public static class BalancesBean {
        /**
         * price : 0.35
         * action : 增加
         * memo : 余额收益
         * create_at : 1547193901
         * status : 1
         */

        private String price;
        private String action;
        private String memo;
        private int create_at;
        private int status;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public int getCreate_at() {
            return create_at;
        }

        public void setCreate_at(int create_at) {
            this.create_at = create_at;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
