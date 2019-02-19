package com.bdgw.cc.ui;

import com.google.gson.annotations.SerializedName;

import me.goldze.common.base.bean.BaseBean;

/**
 * @author GuoFeng
 * @date : 2019/1/21 18:29
 * @description: 用户信息
 */
public class UserInfo extends BaseBean {

    /**
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjIxNjcsImV4cCI6MTU1MDY1ODQ0NiwicGxhdGZvcm0iOiJtb3ppbGxhIn0.aP1d7r-KkGiLPRHuoE1R8IrZ8cP5YDa3thSnhtO-Ico
     * user : {"user_id":2167,"email":"229108560@QQ.COM","user_name":"13928007971","password":"e10adc3949ba59abbe56e057f20f883e","question":"","answer":"","sex":0,"birthday":"0000-00-00","user_money":"1008.44","interest":"8.606","frozen_money":"0.00","pay_points":88283,"rank_points":0,"address_id":6553,"reg_time":1476111719,"last_login":1548037646,"last_time":"0000-00-00 00:00:00","last_ip":"113.74.42.12","visit_count":903,"order_count":34,"or_num":1,"or_num_send":0,"or_num_send_owe":0,"or_num_receive":0,"or_state":1,"bonus_count":0,"user_rank":0,"is_special":0,"ec_salt":null,"salt":"0","parent_id":0,"flag":0,"alias":"王高健(A)","msn":"","qq":"","office_phone":"","home_phone":"13928007971","mobile_phone":"13928007971","is_validated":1,"credit_line":"0.00","passwd_question":null,"passwd_answer":null,"avatar_img_big":null,"avatar_img_middle":null,"avatar_img_small":null,"pay_pwd":"7e4fd78e74826e4b2d3c","real_name":"王高健(A)","is_buy":1,"provinceid":"44","cityid":"4404","areaid":"440402","areainfo":"广东省 珠海市 香洲区 ","company":"爱嘉电脑","company_id":null,"department":null,"department_contact":null,"type":1,"btype":1,"etype":1,"quma":"14404020181","state":1,"ywy_id":"100008","address":"广东省\t珠海市\t香洲区"}
     * error_code : 0
     */

    private String token;
    private UserBean user;
    private int error_code;
    private String error_desc;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_desc() {
        return error_desc;
    }

    public void setError_desc(String error_desc) {
        this.error_desc = error_desc;
    }

    public boolean isSuccess() {
        return error_code == 0;
    }

    public String getErrorMsg() {
        return error_desc;
    }

    public static class UserBean extends BaseBean{
        /**
         * user_id : 2167
         * user_name : 13928007971
         * sex : 0
         * or_state : 1
         * alias : 王高健(A)
         * is_buy : 1
         * type : 1
         * state : 1
         * mobile : 13928007971
         * avatar : {"width":null,"height":null,"thumb":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLu3MgZBAyyiavU0YXGWKEnLRjlg2gHIu87Mgc7hp62E8gLUqOWE2Qr0to0nXagzyEnv4TqcibylOdw/132","large":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLu3MgZBAyyiavU0YXGWKEnLRjlg2gHIu87Mgc7hp62E8gLUqOWE2Qr0to0nXagzyEnv4TqcibylOdw/132"}
         * is_completed : true
         */

        private int user_id;
        private String user_name;
        private int sex;
        private int or_state;
        private String alias;
        private int is_buy;
        @SerializedName("type")
        private int typeX;
        private int state;
        private String mobile;
        private AvatarBean avatar;
        private boolean is_completed;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getOr_state() {
            return or_state;
        }

        public void setOr_state(int or_state) {
            this.or_state = or_state;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public int getIs_buy() {
            return is_buy;
        }

        public void setIs_buy(int is_buy) {
            this.is_buy = is_buy;
        }

        public int getTypeX() {
            return typeX;
        }

        public void setTypeX(int typeX) {
            this.typeX = typeX;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public AvatarBean getAvatar() {
            return avatar;
        }

        public void setAvatar(AvatarBean avatar) {
            this.avatar = avatar;
        }

        public boolean isIs_completed() {
            return is_completed;
        }

        public void setIs_completed(boolean is_completed) {
            this.is_completed = is_completed;
        }

        public static class AvatarBean {
            /**
             * width : null
             * height : null
             * thumb : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLu3MgZBAyyiavU0YXGWKEnLRjlg2gHIu87Mgc7hp62E8gLUqOWE2Qr0to0nXagzyEnv4TqcibylOdw/132
             * large : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLu3MgZBAyyiavU0YXGWKEnLRjlg2gHIu87Mgc7hp62E8gLUqOWE2Qr0to0nXagzyEnv4TqcibylOdw/132
             */

            private Object width;
            private Object height;
            private String thumb;
            private String large;

            public Object getWidth() {
                return width;
            }

            public void setWidth(Object width) {
                this.width = width;
            }

            public Object getHeight() {
                return height;
            }

            public void setHeight(Object height) {
                this.height = height;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }
        }
    }



}
