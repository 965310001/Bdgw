package com.bdgw.cc.ui;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import me.goldze.common.base.bean.BaseBean;

/**
 * @author GuoFeng
 * @date : 2019/1/23 11:17
 * @description: 搜索
 */
public class HotKeyInfo extends BaseBean {

    /**
     * data : [{"id":6,"link":"","name":"面试","order":1,"visible":1},{"id":9,"link":"","name":"Studio3","order":1,"visible":1},{"id":5,"link":"","name":"动画","order":2,"visible":1},{"id":1,"link":"","name":"自定义View","order":3,"visible":1},{"id":2,"link":"","name":"性能优化 速度","order":4,"visible":1},{"id":3,"link":"","name":"gradle","order":5,"visible":1},{"id":4,"link":"","name":"Camera 相机","order":6,"visible":1},{"id":7,"link":"","name":"代码混淆 安全","order":7,"visible":1},{"id":8,"link":"","name":"逆向 加固","order":8,"visible":1}]
     * errorCode : 0
     * errorMsg :
     */

    private int errorCode;
    private String errorMsg;
    @SerializedName("keywords")
    private List<DataBean> data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 6
         * link :
         * name : 面试
         * order : 1
         * visible : 1
         */

        @SerializedName("type")
        private int id;
        //        private String link;
        @SerializedName("content")
        private String name;
//        private int order;
//        private int visible;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

//        public String getLink() {
//            return link;
//        }
//
//        public void setLink(String link) {
//            this.link = link;
//        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

//        public int getOrder() {
//            return order;
//        }
//
//        public void setOrder(int order) {
//            this.order = order;
//        }
//
//        public int getVisible() {
//            return visible;
//        }
//
//        public void setVisible(int visible) {
//            this.visible = visible;
//        }
    }
}
