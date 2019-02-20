package com.bdgw.cc.ui.bean;

import me.goldze.common.base.bean.BaseBean;

/**
 * @author GuoFeng
 * @date : 2019/2/20 15:57
 * @description:
 */
public class Paged extends BaseBean {

    /**
     * total : 69
     * page : 6
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
