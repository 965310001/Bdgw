package com.bdgw.cc.http;


import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author GuoFeng
 * @date : 2019/1/19 15:27
 * @description:
 */
public class Result {

    /**
     * data : {"chapterTops":[],"collectIds":[],"email":"","icon":"","id":16386,"password":"","token":"","type":0,"username":"戒酒的李白s"}
     */

    @SerializedName("data")
    private DataBean dataX;

    public DataBean getDataX() {
        return dataX;
    }

    public void setDataX(DataBean dataX) {
        this.dataX = dataX;
    }

    public static class DataBean {
        /**
         * chapterTops : []
         * collectIds : []
         * email :
         * icon :
         * id : 16386
         * password :
         * token :
         * type : 0
         * username : 戒酒的李白s
         */

        private String email;
        private String icon;
        private int id;
        private String password;
        private String token;
        private int type;
        private String username;
        private List<?> chapterTops;
        private List<?> collectIds;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<?> getChapterTops() {
            return chapterTops;
        }

        public void setChapterTops(List<?> chapterTops) {
            this.chapterTops = chapterTops;
        }

        public List<?> getCollectIds() {
            return collectIds;
        }

        public void setCollectIds(List<?> collectIds) {
            this.collectIds = collectIds;
        }
    }
}
