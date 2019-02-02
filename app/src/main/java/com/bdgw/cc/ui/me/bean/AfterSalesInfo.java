package com.bdgw.cc.ui.me.bean;

import me.goldze.common.base.bean.BaseBean;

/**
 * @author GuoFeng
 * @date : 2019/2/1 17:19
 * @description: 售后
 */
public class AfterSalesInfo extends BaseBean {


    /**
     * number : 1000000001776202
     * des : 订单 1000000001776202金河田 智能PLUS3000 额定300W台式机电源 工包 数量：1，单价：84.20元；
     * reason : 申请时间：2019/01/12 14:39提前换货:风扇不转，无没使用，无没开机，更换电源后就正常
     * typeservice : 请先发回确认 待买家寄回
     * status : 处理中
     */

    private String number;
    private String des;
    private String reason;
    private String typeservice;
    private String status;
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTypeservice() {
        return typeservice;
    }

    public void setTypeservice(String typeservice) {
        this.typeservice = typeservice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
