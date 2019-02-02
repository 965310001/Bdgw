package com.bdgw.cc.ui.me.bean;

import me.goldze.common.base.bean.BaseBean;

/**
 * @author GuoFeng
 * @date : 2019/1/25 21:04
 * @description:
 */
public class AddressInfo extends BaseBean {

    public String name;
    public String phone;
    public String address;
    public String details;
    public boolean isDefault;

    public String status = "";

    @Override
    public String toString() {
        return "AddressInfo{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", details='" + details + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }
}
