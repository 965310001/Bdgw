package com.bdgw.cc.ui.classify.bean;

import com.bdgw.cc.ui.home.bean.GoodsComment;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import me.goldze.common.base.bean.BaseBean;

/**
 * @author GuoFeng
 * @date : 2019/2/21 12:10
 * @description:
 */
public class ReviewListInfo extends BaseBean {

    /**
     * reviews : [{"user_name":"陈**","content":"商品质量好","id":22,"grade":3,"is_anonymous":0,"created_at":1550537238,"updated_at":1550537238},{"user_name":"陈**","content":"性价比非常高","id":18,"grade":3,"is_anonymous":1,"created_at":1549119774,"updated_at":1549119774}]
     * paged : {"total":2,"page":"1","size":"10","more":0}
     * error_code : 0
     */

    private PagedBean paged;
    private int error_code;
    @SerializedName("reviews")
    private List<GoodsComment> data;

    public PagedBean getPaged() {
        return paged;
    }

    public void setPaged(PagedBean paged) {
        this.paged = paged;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<GoodsComment> getData() {
        return data;
    }

    public void setData(List<GoodsComment> data) {
        this.data = data;
    }

    public static class PagedBean {
        /**
         * total : 2
         * page : 1
         * size : 10
         * more : 0
         */

        private int total;
        private String page;
        private String size;
        private int more;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public int getMore() {
            return more;
        }

        public void setMore(int more) {
            this.more = more;
        }
    }

//    public static class ReviewsBean {
//        /**
//         * user_name : 陈**
//         * content : 商品质量好
//         * id : 22
//         * grade : 3
//         * is_anonymous : 0
//         * created_at : 1550537238
//         * updated_at : 1550537238
//         */
//
//        private String user_name;
//        private String content;
//        private int id;
//        private int grade;
//        private int is_anonymous;
//        private int created_at;
//        private int updated_at;
//
//        public String getUser_name() {
//            return user_name;
//        }
//
//        public void setUser_name(String user_name) {
//            this.user_name = user_name;
//        }
//
//        public String getContent() {
//            return content;
//        }
//
//        public void setContent(String content) {
//            this.content = content;
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
//        public int getGrade() {
//            return grade;
//        }
//
//        public void setGrade(int grade) {
//            this.grade = grade;
//        }
//
//        public int getIs_anonymous() {
//            return is_anonymous;
//        }
//
//        public void setIs_anonymous(int is_anonymous) {
//            this.is_anonymous = is_anonymous;
//        }
//
//        public int getCreated_at() {
//            return created_at;
//        }
//
//        public void setCreated_at(int created_at) {
//            this.created_at = created_at;
//        }
//
//        public int getUpdated_at() {
//            return updated_at;
//        }
//
//        public void setUpdated_at(int updated_at) {
//            this.updated_at = updated_at;
//        }
//    }
}
