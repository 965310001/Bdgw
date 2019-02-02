package com.bdgw.cc.ui.home.bean;

import me.goldze.common.base.bean.BaseBean;

/**
 * @author GuoFeng
 * @date : 2019/1/28 16:27
 * @description: 商品评论
 */
public class GoodsComment extends BaseBean {

    public String userHead;
    public String userName;
    public String comment;//评论内容
    public float star;//星级

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setStar(float star) {
        this.star = star;
    }
}
