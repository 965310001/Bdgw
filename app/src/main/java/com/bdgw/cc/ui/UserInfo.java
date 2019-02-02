package com.bdgw.cc.ui;

import java.util.List;

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

    private String username;
    private String password;
    private String icon;
    private int id;
    private int type;
    private List<Integer> collectIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Integer> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<Integer> collectIds) {
        this.collectIds = collectIds;
    }

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

    public static class UserBean {
        /**
         * user_id : 2167
         * email : 229108560@QQ.COM
         * user_name : 13928007971
         * password : e10adc3949ba59abbe56e057f20f883e
         * question :
         * answer :
         * sex : 0
         * birthday : 0000-00-00
         * user_money : 1008.44
         * interest : 8.606
         * frozen_money : 0.00
         * pay_points : 88283
         * rank_points : 0
         * address_id : 6553
         * reg_time : 1476111719
         * last_login : 1548037646
         * last_time : 0000-00-00 00:00:00
         * last_ip : 113.74.42.12
         * visit_count : 903
         * order_count : 34
         * or_num : 1
         * or_num_send : 0
         * or_num_send_owe : 0
         * or_num_receive : 0
         * or_state : 1
         * bonus_count : 0
         * user_rank : 0
         * is_special : 0
         * ec_salt : null
         * salt : 0
         * parent_id : 0
         * flag : 0
         * alias : 王高健(A)
         * msn :
         * qq :
         * office_phone :
         * home_phone : 13928007971
         * mobile_phone : 13928007971
         * is_validated : 1
         * credit_line : 0.00
         * passwd_question : null
         * passwd_answer : null
         * avatar_img_big : null
         * avatar_img_middle : null
         * avatar_img_small : null
         * pay_pwd : 7e4fd78e74826e4b2d3c
         * real_name : 王高健(A)
         * is_buy : 1
         * provinceid : 44
         * cityid : 4404
         * areaid : 440402
         * areainfo : 广东省 珠海市 香洲区
         * company : 爱嘉电脑
         * company_id : null
         * department : null
         * department_contact : null
         * type : 1
         * btype : 1
         * etype : 1
         * quma : 14404020181
         * state : 1
         * ywy_id : 100008
         * address : 广东省	珠海市	香洲区
         */

        private int user_id;
        private String email;
        private String user_name;
        private String password;
        private String question;
        private String answer;
        private int sex;
        private String birthday;
        private String user_money;
        private String interest;
        private String frozen_money;
        private int pay_points;
        private int rank_points;
        private int address_id;
        private int reg_time;
        private int last_login;
        private String last_time;
        private String last_ip;
        private int visit_count;
        private int order_count;
        private int or_num;
        private int or_num_send;
        private int or_num_send_owe;
        private int or_num_receive;
        private int or_state;
        private int bonus_count;
        private int user_rank;
        private int is_special;
        private Object ec_salt;
        private String salt;
        private int parent_id;
        private int flag;
        private String alias;
        private String msn;
        private String qq;
        private String office_phone;
        private String home_phone;
        private String mobile_phone;
        private int is_validated;
        private String credit_line;
        private Object passwd_question;
        private Object passwd_answer;
        private Object avatar_img_big;
        private Object avatar_img_middle;
        private Object avatar_img_small;
        private String pay_pwd;
        private String real_name;
        private int is_buy;
        private String provinceid;
        private String cityid;
        private String areaid;
        private String areainfo;
        private String company;
        private Object company_id;
        private Object department;
        private Object department_contact;
        private int type;
        private int btype;
        private int etype;
        private String quma;
        private int state;
        private String ywy_id;
        private String address;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getInterest() {
            return interest;
        }

        public void setInterest(String interest) {
            this.interest = interest;
        }

        public String getFrozen_money() {
            return frozen_money;
        }

        public void setFrozen_money(String frozen_money) {
            this.frozen_money = frozen_money;
        }

        public int getPay_points() {
            return pay_points;
        }

        public void setPay_points(int pay_points) {
            this.pay_points = pay_points;
        }

        public int getRank_points() {
            return rank_points;
        }

        public void setRank_points(int rank_points) {
            this.rank_points = rank_points;
        }

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public int getReg_time() {
            return reg_time;
        }

        public void setReg_time(int reg_time) {
            this.reg_time = reg_time;
        }

        public int getLast_login() {
            return last_login;
        }

        public void setLast_login(int last_login) {
            this.last_login = last_login;
        }

        public String getLast_time() {
            return last_time;
        }

        public void setLast_time(String last_time) {
            this.last_time = last_time;
        }

        public String getLast_ip() {
            return last_ip;
        }

        public void setLast_ip(String last_ip) {
            this.last_ip = last_ip;
        }

        public int getVisit_count() {
            return visit_count;
        }

        public void setVisit_count(int visit_count) {
            this.visit_count = visit_count;
        }

        public int getOrder_count() {
            return order_count;
        }

        public void setOrder_count(int order_count) {
            this.order_count = order_count;
        }

        public int getOr_num() {
            return or_num;
        }

        public void setOr_num(int or_num) {
            this.or_num = or_num;
        }

        public int getOr_num_send() {
            return or_num_send;
        }

        public void setOr_num_send(int or_num_send) {
            this.or_num_send = or_num_send;
        }

        public int getOr_num_send_owe() {
            return or_num_send_owe;
        }

        public void setOr_num_send_owe(int or_num_send_owe) {
            this.or_num_send_owe = or_num_send_owe;
        }

        public int getOr_num_receive() {
            return or_num_receive;
        }

        public void setOr_num_receive(int or_num_receive) {
            this.or_num_receive = or_num_receive;
        }

        public int getOr_state() {
            return or_state;
        }

        public void setOr_state(int or_state) {
            this.or_state = or_state;
        }

        public int getBonus_count() {
            return bonus_count;
        }

        public void setBonus_count(int bonus_count) {
            this.bonus_count = bonus_count;
        }

        public int getUser_rank() {
            return user_rank;
        }

        public void setUser_rank(int user_rank) {
            this.user_rank = user_rank;
        }

        public int getIs_special() {
            return is_special;
        }

        public void setIs_special(int is_special) {
            this.is_special = is_special;
        }

        public Object getEc_salt() {
            return ec_salt;
        }

        public void setEc_salt(Object ec_salt) {
            this.ec_salt = ec_salt;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getMsn() {
            return msn;
        }

        public void setMsn(String msn) {
            this.msn = msn;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getOffice_phone() {
            return office_phone;
        }

        public void setOffice_phone(String office_phone) {
            this.office_phone = office_phone;
        }

        public String getHome_phone() {
            return home_phone;
        }

        public void setHome_phone(String home_phone) {
            this.home_phone = home_phone;
        }

        public String getMobile_phone() {
            return mobile_phone;
        }

        public void setMobile_phone(String mobile_phone) {
            this.mobile_phone = mobile_phone;
        }

        public int getIs_validated() {
            return is_validated;
        }

        public void setIs_validated(int is_validated) {
            this.is_validated = is_validated;
        }

        public String getCredit_line() {
            return credit_line;
        }

        public void setCredit_line(String credit_line) {
            this.credit_line = credit_line;
        }

        public Object getPasswd_question() {
            return passwd_question;
        }

        public void setPasswd_question(Object passwd_question) {
            this.passwd_question = passwd_question;
        }

        public Object getPasswd_answer() {
            return passwd_answer;
        }

        public void setPasswd_answer(Object passwd_answer) {
            this.passwd_answer = passwd_answer;
        }

        public Object getAvatar_img_big() {
            return avatar_img_big;
        }

        public void setAvatar_img_big(Object avatar_img_big) {
            this.avatar_img_big = avatar_img_big;
        }

        public Object getAvatar_img_middle() {
            return avatar_img_middle;
        }

        public void setAvatar_img_middle(Object avatar_img_middle) {
            this.avatar_img_middle = avatar_img_middle;
        }

        public Object getAvatar_img_small() {
            return avatar_img_small;
        }

        public void setAvatar_img_small(Object avatar_img_small) {
            this.avatar_img_small = avatar_img_small;
        }

        public String getPay_pwd() {
            return pay_pwd;
        }

        public void setPay_pwd(String pay_pwd) {
            this.pay_pwd = pay_pwd;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public int getIs_buy() {
            return is_buy;
        }

        public void setIs_buy(int is_buy) {
            this.is_buy = is_buy;
        }

        public String getProvinceid() {
            return provinceid;
        }

        public void setProvinceid(String provinceid) {
            this.provinceid = provinceid;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getAreaid() {
            return areaid;
        }

        public void setAreaid(String areaid) {
            this.areaid = areaid;
        }

        public String getAreainfo() {
            return areainfo;
        }

        public void setAreainfo(String areainfo) {
            this.areainfo = areainfo;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public Object getCompany_id() {
            return company_id;
        }

        public void setCompany_id(Object company_id) {
            this.company_id = company_id;
        }

        public Object getDepartment() {
            return department;
        }

        public void setDepartment(Object department) {
            this.department = department;
        }

        public Object getDepartment_contact() {
            return department_contact;
        }

        public void setDepartment_contact(Object department_contact) {
            this.department_contact = department_contact;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getBtype() {
            return btype;
        }

        public void setBtype(int btype) {
            this.btype = btype;
        }

        public int getEtype() {
            return etype;
        }

        public void setEtype(int etype) {
            this.etype = etype;
        }

        public String getQuma() {
            return quma;
        }

        public void setQuma(String quma) {
            this.quma = quma;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getYwy_id() {
            return ywy_id;
        }

        public void setYwy_id(String ywy_id) {
            this.ywy_id = ywy_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
