package com.bdgw.cc.ui.home.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import me.goldze.common.base.bean.BaseBean;

/**
 * @author GuoFeng
 * @date : 2019/1/20 14:26
 * @description: 轮播图
 */
public class Banner extends BaseBean {

    /**
     * data : [{"desc":"一起来做个App吧","id":10,"imagePath":"http://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png","isVisible":1,"order":3,"title":"一起来做个App吧","type":0,"url":"http://www.wanandroid.com/blog/show/2"},{"desc":"","id":4,"imagePath":"http://www.wanandroid.com/blogimgs/ab17e8f9-6b79-450b-8079-0f2287eb6f0f.png","isVisible":1,"order":0,"title":"看看别人的面经，搞定面试~","type":1,"url":"http://www.wanandroid.com/article/list/0?cid=73"},{"desc":"","id":3,"imagePath":"http://www.wanandroid.com/blogimgs/fb0ea461-e00a-482b-814f-4faca5761427.png","isVisible":1,"order":1,"title":"兄弟，要不要挑个项目学习下?","type":1,"url":"http://www.wanandroid.com/project"},{"desc":"","id":6,"imagePath":"http://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png","isVisible":1,"order":1,"title":"我们新增了一个常用导航Tab~","type":1,"url":"http://www.wanandroid.com/navi"},{"desc":"","id":18,"imagePath":"http://www.wanandroid.com/blogimgs/00f83f1d-3c50-439f-b705-54a49fc3d90d.jpg","isVisible":1,"order":1,"title":"公众号文章列表强势上线","type":1,"url":"http://www.wanandroid.com/wxarticle/list/408/1"},{"desc":"","id":2,"imagePath":"http://www.wanandroid.com/blogimgs/90cf8c40-9489-4f9d-8936-02c9ebae31f0.png","isVisible":1,"order":2,"title":"JSON工具","type":1,"url":"http://www.wanandroid.com/tools/bejson"},{"desc":"","id":5,"imagePath":"http://www.wanandroid.com/blogimgs/acc23063-1884-4925-bdf8-0b0364a7243e.png","isVisible":1,"order":3,"title":"微信文章合集","type":1,"url":"http://www.wanandroid.com/blog/show/6"}]
     * errorCode : 0
     * errorMsg :
     */

    private int errorCode;
    private String errorMsg;
    @SerializedName("banners")
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

    public List<String> getImags() {
        List<String> imags = new ArrayList<>();
        for (DataBean bean : data) {
            imags.add(bean.getPhoto().getThumb());
        }
        return imags;
    }

//    public String getTitle(int postion) {
//        return data.get(postion).getTitle();
//    }

    public String getUrl(int postion) {
        return data.get(postion).getLink();
    }

    private static class DataBean {
        /**
         * desc : 一起来做个App吧
         * id : 10
         * imagePath : http://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png
         * isVisible : 1
         * order : 3
         * title : 一起来做个App吧
         * type : 0
         * url : http://www.wanandroid.com/blog/show/2
         */

//        private String desc;
//        private int id;
//        private String imagePath;
//        private int isVisible;
//        private int order;
//        private String title;
//        private int type;
//        private String url;
//
//        public String getDesc() {
//            return desc;
//        }
//
//        public void setDesc(String desc) {
//            this.desc = desc;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getImagePath() {
//            return imagePath;
//        }
//
//        public void setImagePath(String imagePath) {
//            this.imagePath = imagePath;
//        }
//
//        public int getIsVisible() {
//            return isVisible;
//        }
//
//        public void setIsVisible(int isVisible) {
//            this.isVisible = isVisible;
//        }
//
//        public int getOrder() {
//            return order;
//        }
//
//        public void setOrder(int order) {
//            this.order = order;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public int getType() {
//            return type;
//        }
//
//        public void setType(int type) {
//            this.type = type;
//        }
//
//        private String getUrl() {
//            return url;
//        }
//
//        public void setUrl(String url) {
//            this.url = url;
//        }

        /**
         * id : 0
         * photo : {"width":null,"height":null,"thumb":"http://www.biandanwang.cc/data/afficheimg/20161114nraqzu.jpg","large":"http://www.biandanwang.cc/data/afficheimg/20161114nraqzu.jpg"}
         * link : http://yunqi.shopex.cn
         * title :
         * sort : 0
         */

//        private int id;
        private PhotoBean photo;
        private String link;
//        private String title;
//        private String sort;

//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }

        public PhotoBean getPhoto() {
            return photo;
        }

        public void setPhoto(PhotoBean photo) {
            this.photo = photo;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }

//        public String getSort() {
//            return sort;
//        }
//
//        public void setSort(String sort) {
//            this.sort = sort;
//        }

        public static class PhotoBean {
            /**
             * width : null
             * height : null
             * thumb : http://www.biandanwang.cc/data/afficheimg/20161114nraqzu.jpg
             * large : http://www.biandanwang.cc/data/afficheimg/20161114nraqzu.jpg
             */

//            private Object width;
//            private Object height;
            private String thumb;
//            private String large;

//            public Object getWidth() {
//                return width;
//            }
//
//            public void setWidth(Object width) {
//                this.width = width;
//            }
//
//            public Object getHeight() {
//                return height;
//            }
//
//            public void setHeight(Object height) {
//                this.height = height;
//            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

          /*  public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }*/
        }
    }
}