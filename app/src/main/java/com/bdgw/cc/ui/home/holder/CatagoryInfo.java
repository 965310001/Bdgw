package com.bdgw.cc.ui.home.holder;

import com.google.gson.annotations.SerializedName;
import com.socks.library.KLog;

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
        /*private String link;*/
        private String id;
        private String icon;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

//        public String getLink() {
//            return link;
//        }
//
//        public void setLink(String link) {
//            this.link = link;
//        }


        public String getIcon() {
            // TODO: 2019/2/22 删除
            if (this.icon.isEmpty()) {
                this.icon = "https://img14.360buyimg.com/focus/s140x140_jfs/t27136/183/1628977274/31007/a6f7ed55/5be6ebd8Nb07ef492.png";
                KLog.i(this.icon);
            }
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

}
