package com.bdgw.cc.ui.classify.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import me.goldze.common.base.bean.BaseBean;

/**
 * @author GuoFeng
 * @date : 2019/1/20 18:08
 * @description: 三级分类
 */
public class ClassificationInfo extends BaseBean {

    /**
     * data : [{"children":[{"children":[{"children":[],"courseId":13,"id":26,"name":"基础UI控件","order":15000,"parentChapterId":25,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":27,"name":"ListView&GridView","order":15001,"parentChapterId":25,"userControlSetTop":false,"visible":1}],"courseId":13,"id":60,"name":"Android Studio相关","order":1000,"parentChapterId":150,"userControlSetTop":false,"visible":1},{"children":[{"children":[],"courseId":13,"id":26,"name":"基础UI控件","order":15000,"parentChapterId":25,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":26,"name":"基础UI控件","order":15000,"parentChapterId":25,"userControlSetTop":false,"visible":1}],"courseId":13,"id":169,"name":"gradle","order":1001,"parentChapterId":150,"userControlSetTop":false,"visible":1}],"courseId":13,"id":150,"name":"开发环境","order":1,"parentChapterId":0,"userControlSetTop":false,"visible":1}]
     * errorCode : 0
     * errorMsg :
     */

    private int errorCode;
    private String errorMsg;
    @SerializedName("categories")
    private List<DataBean> data;//左边

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

        private int id;
        private String name;
        @SerializedName("categories")
        private List<ChildrenBeanX> children;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ChildrenBeanX> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBeanX> children) {
            this.children = children;
        }

        public static class ChildrenBeanX {//右边最上面

            private String name;

            @SerializedName("categories")
            private List<ChildrenBean> children;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ChildrenBean> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBean> children) {
                this.children = children;
            }

            public static class ChildrenBean {//右边最下面

                private int id;
                private String icon;
                private String name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }
            }
        }
    }
}

/**
 * @author GuoFeng
 * @date : 2019/1/20 18:08
 * @description: 二级分类
 */

//public class ClassificationInfo extends BaseBean {
//
//    private List<DataBean> data;
//
//    public List<DataBean> getData() {
//        return data;
//    }
//
//    public void setData(List<DataBean> data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        /**
//         * children : [{"children":[],"courseId":13,"id":60,"name":"Android Studio相关","order":1000,"parentChapterId":150,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":169,"name":"gradle","order":1001,"parentChapterId":150,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":269,"name":"官方发布","order":1002,"parentChapterId":150,"userControlSetTop":false,"visible":1}]
//         * courseId : 13
//         * id : 150
//         * name : 开发环境
//         * order : 1
//         * parentChapterId : 0
//         * userControlSetTop : false
//         * visible : 1
//         */
//        private int courseId;
//        private int id;
//        private String name;
//        private int order;
//        private int parentChapterId;
//        private boolean userControlSetTop;
//        private int visible;
//        private List<ChildrenBean> children;
//
//        public int getCourseId() {
//            return courseId;
//        }
//
//        public void setCourseId(int courseId) {
//            this.courseId = courseId;
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
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
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
//        public int getParentChapterId() {
//            return parentChapterId;
//        }
//
//        public void setParentChapterId(int parentChapterId) {
//            this.parentChapterId = parentChapterId;
//        }
//
//        public boolean isUserControlSetTop() {
//            return userControlSetTop;
//        }
//
//        public void setUserControlSetTop(boolean userControlSetTop) {
//            this.userControlSetTop = userControlSetTop;
//        }
//
//        public int getVisible() {
//            return visible;
//        }
//
//        public void setVisible(int visible) {
//            this.visible = visible;
//        }
//
//        public List<ChildrenBean> getChildren() {
//            return children;
//        }
//
//        public void setChildren(List<ChildrenBean> children) {
//            this.children = children;
//        }
//
//        public static class ChildrenBean {
//            /**
//             * children : []
//             * courseId : 13
//             * id : 60
//             * name : Android Studio相关
//             * order : 1000
//             * parentChapterId : 150
//             * userControlSetTop : false
//             * visible : 1
//             */
//
//            private int courseId;
//            private int id;
//            private String name;
//            private int order;
//            private int parentChapterId;
//            private boolean userControlSetTop;
//            private int visible;
//            private List<?> children;
//
//            public int getCourseId() {
//                return courseId;
//            }
//
//            public void setCourseId(int courseId) {
//                this.courseId = courseId;
//            }
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public int getOrder() {
//                return order;
//            }
//
//            public void setOrder(int order) {
//                this.order = order;
//            }
//
//            public int getParentChapterId() {
//                return parentChapterId;
//            }
//
//            public void setParentChapterId(int parentChapterId) {
//                this.parentChapterId = parentChapterId;
//            }
//
//            public boolean isUserControlSetTop() {
//                return userControlSetTop;
//            }
//
//            public void setUserControlSetTop(boolean userControlSetTop) {
//                this.userControlSetTop = userControlSetTop;
//            }
//
//            public int getVisible() {
//                return visible;
//            }
//
//            public void setVisible(int visible) {
//                this.visible = visible;
//            }
//
//            public List<?> getChildren() {
//                return children;
//            }
//
//            public void setChildren(List<?> children) {
//                this.children = children;
//            }
//        }
//    }
//}
