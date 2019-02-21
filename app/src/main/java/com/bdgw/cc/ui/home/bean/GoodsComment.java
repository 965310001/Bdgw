package com.bdgw.cc.ui.home.bean;

import com.google.gson.annotations.SerializedName;
import com.socks.library.KLog;

import me.goldze.common.base.bean.BaseBean;
import me.goldze.common.utils.DateUtils;

/**
 * @author GuoFeng
 * @date : 2019/1/28 16:27
 * @description: 商品评论
 */
public class GoodsComment extends BaseBean {

    private String userHead;
    @SerializedName("user_name")
    private String userName;
    @SerializedName("content")
    private String comment;//评论内容
    public float star;//星级
    /*0全部,1差评,2中评,3好评	*/
    private int grade;

    @SerializedName("created_at")
    private long time;

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserHead() {
        this.userHead="https://fakeimg.pl/350x200/dedede/?text=".concat(this.userName.substring(0,1));
        KLog.i(userHead);
        return userHead;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getComment() {
        if (comment.isEmpty()) {
            comment = "无评价信息";
        }
        return comment;
    }

    public String getTime() {
        return DateUtils.getFormatDate(time, "yyyy-MM-dd HH:mm:ss");
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setStar(float star) {
        this.star = star;
    }
}
