package com.bdgw.cc.ui.me.bean;

import java.util.List;

/**
 * @author GuoFeng
 * @date : 2019/1/28 14:30
 * @description:
 */
public class ExtractInfo {

    /**
     * data : [{"status":"提现","money":"41","time":"2015-01-10"},{"status":"提现","money":"41","time":"2015-01-10"},{"status":"提现","money":"41","time":"2015-01-10"}]
     * error_code : 0
     */

    private int error_code;
    private List<DataBean> data;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * status : 提现
         * money : 41
         * time : 2015-01-10
         */

        private String status;
        private String money;
        private String time;
        private String surplusmoney;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getSurplusmoney() {
            return surplusmoney;
        }

        public void setSurplusmoney(String surplusmoney) {
            this.surplusmoney = surplusmoney;
        }
    }
}
