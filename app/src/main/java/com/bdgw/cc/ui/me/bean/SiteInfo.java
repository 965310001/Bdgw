package com.bdgw.cc.ui.me.bean;

import com.google.gson.annotations.SerializedName;

import me.goldze.common.base.bean.BaseBean;

/**
 * @author GuoFeng
 * @date : 2019/2/21 11:54
 * @description: 站点信息
 */
public class SiteInfo extends BaseBean {
    /**
     * site_info : {"name":"测试网站","desc":"测试网站","logo":null,"opening":true,"telephone":"0756-8977001","terms_url":"http://www.biandanwang.cc/article.php?cat_id=-1","about_url":"http://www.biandanwang.cc/article.php?cat_id=-2"}
     */

    @SerializedName("site_info")
    private SiteInfoBean data;

    public SiteInfoBean getData() {
        return data;
    }

    public void setData(SiteInfoBean data) {
        this.data = data;
    }

    public static class SiteInfoBean {
        /**
         * name : 测试网站
         * desc : 测试网站
         * logo : null
         * opening : true
         * telephone : 0756-8977001
         * terms_url : http://www.biandanwang.cc/article.php?cat_id=-1
         * about_url : http://www.biandanwang.cc/article.php?cat_id=-2
         */

        private String name;
        private String desc;
        private String logo;
        private boolean opening;
        private String telephone;
        private String terms_url;
        private String about_url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public boolean isOpening() {
            return opening;
        }

        public void setOpening(boolean opening) {
            this.opening = opening;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getTerms_url() {
            return terms_url;
        }

        public void setTerms_url(String terms_url) {
            this.terms_url = terms_url;
        }

        public String getAbout_url() {
            return about_url;
        }

        public void setAbout_url(String about_url) {
            this.about_url = about_url;
        }
    }
}
