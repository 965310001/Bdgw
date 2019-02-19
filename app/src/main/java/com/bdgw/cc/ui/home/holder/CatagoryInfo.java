package com.bdgw.cc.ui.home.holder;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import me.goldze.common.base.bean.BaseBean;

/**
 * @author GuoFeng
 * @date : 2019/1/20 15:58
 * @description: 首页 四个状态栏
 */
public class CatagoryInfo extends BaseBean {

    @SerializedName("menus")
    private List<Catagory> data;

    public List<Catagory> getData() {
        return data;
    }

    public void setData(List<Catagory> data) {
        this.data = data;
    }

    class Catagory extends BaseBean {
        private String title;
        private String link;
        private int id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

}
