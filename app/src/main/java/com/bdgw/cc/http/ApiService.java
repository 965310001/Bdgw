package com.bdgw.cc.http;


import com.bdgw.cc.BuildConfig;
import com.bdgw.cc.ui.HotKeyInfo;
import com.bdgw.cc.ui.UserInfo;
import com.bdgw.cc.ui.bean.AppInfo;
import com.bdgw.cc.ui.classify.bean.ClassificationInfo;
import com.bdgw.cc.ui.home.bean.Banner;
import com.bdgw.cc.ui.home.bean.HomeList;
import com.bdgw.cc.ui.home.bean.OrderInfo;
import com.bdgw.cc.ui.home.bean.RedItemInfo;
import com.bdgw.cc.ui.home.bean.SearchInfo;
import com.bdgw.cc.ui.home.holder.CatagoryInfo;
import com.bdgw.cc.ui.me.bean.AddressInfo;
import com.bdgw.cc.ui.shopping.bean.GoodsInfo;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import me.goldze.common.base.bean.BaseResponse;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author GuoFeng
 * @date : 2019/1/19 14:55
 * @description: api
 */
public interface ApiService {

    interface Api {

        /*http://api.biandanwang.cc/v2/*/
        /*http://wanandroid.com/*/
        String Base_URL = BuildConfig.APP_URL;

        /*-------------------------------------------------------------------首页---------------------------------------------------*/
        /*登录*/
        String LOGIN = "/v2/ecapi.auth.signin";
        /*注册*/
        String REGISTER = "/user/register";
        /*检查更新*/
        String CHECKUPDATE = "";

        interface home {
//            String BANNER = "/banner/json";
//            String HOME_LIST = "/banner/json";

            /*轮播图*/
            String BANNER = "/v2/ecapi.banner.list";
            /*首页数据*/
            String HOME_LIST = "/v2/ecapi.home.product.list";
            /*菜单*/
            String HOME_MENU = "/v2/ecapi.site.menu";

            /*订单*/
            String ORDER_LIST = "/v2/ecapi.order.list";
            /*红包*/
            String RED_LIST = "/v2/ecapi.cashgift.list";

            /*热搜*/
            String HOTKEY_LIST = "/v2/ecapi.search.keyword.list";

            /*搜索list*/
            String SEARCH_LIST = "/article/query/0/json";
        }

        /*-------------------------------------------------------------------分类---------------------------------------------------*/
        interface classify {
            //双listview
            String TREE = "/v2/ecapi.category.list";
        }

        /*-------------------------------------------------------------------购物车-------------------------------------------------*/
        interface shopping {

            String PRODUCT="/v2/ecapi.product.get";
        }
        /*-------------------------------------------------------------------我的---------------------------------------------------*/

        interface me {

            /*地址列表*/
            String ADDRESSLIST = "//";
            /*地址的增加，修改*/
            String UPDATEADDRESS = "//";
        }
    }


    /*-------------------------------------------------------------------首页---------------------------------------------------*/
    /*登录*/
    //Observable Flowable
    @FormUrlEncoded
    @POST(Api.LOGIN)
    Flowable<UserInfo> login(@Field("username") String userName,
                             @Field("password") String password);


    /*注册*/
    @FormUrlEncoded
    @POST(Api.REGISTER)
    Flowable<BaseResponse<UserInfo>> register(@Field("username") String userName,
                                              @Field("password") String password,
                                              @Field("repassword") String repassword);

    /*菜单*/
    @POST(Api.home.HOME_MENU)
    Flowable<CatagoryInfo> getMenu();


    /*检查更新*/
    @POST(Api.CHECKUPDATE)
    Observable<AppInfo> checkUpdate();

    /*轮播图*/
    @POST(Api.home.BANNER)
    @FormUrlEncoded
    Flowable<Banner> getBannerData(@Field("page") int page,
                                   @Field("per_page") int per_page);

    /*首页数据*/
    @POST(Api.home.HOME_LIST)
    Flowable<HomeList> getHomeData();

    /*订单*/
    @FormUrlEncoded
    @POST(Api.home.ORDER_LIST)
    Flowable<OrderInfo> getOrderData(@Field("page") int page,
                                     @Field("per_page") String per_page,
                                     @Field("status") String status);

    /*红包*/
    @FormUrlEncoded
    @POST(Api.home.RED_LIST)
    Flowable<RedItemInfo> getRedData(@Field("page") int page,
                                     @Field("per_page") String per_page,
                                     @Field("status") String status);

    /*热搜*/
    @POST(Api.home.HOTKEY_LIST)
    Flowable<HotKeyInfo> getHotkeyData();

    /*搜索list*/
    @FormUrlEncoded
    @POST(Api.home.SEARCH_LIST)
    Flowable<SearchInfo> getSearchListData(@Field("k") String k);

    /*双listview*/
    @FormUrlEncoded
    @POST(Api.classify.TREE)
    Flowable<ClassificationInfo> getTreeData(@Field("page") int page,
                                             @Field("per_page") int per_page,
                                             @Field("top_level") int top_level);
    /*-------------------------------------------------------------------分类---------------------------------------------------*/

    /*双listview右边*/
//    @POST(Api.classify.TREE)
//    Flowable<ClassificationInfo> getTreeRightData(@Field("category") int category);

    /*-------------------------------------------------------------------购物车-------------------------------------------------*/
    /*商品详情*/
    @FormUrlEncoded
    @POST(Api.shopping.PRODUCT)
    Flowable<GoodsInfo> getProduct(@Field("product") long id);


    /*-------------------------------------------------------------------我的---------------------------------------------------*/

    @FormUrlEncoded
    @POST(Api.me.ADDRESSLIST)
    Flowable<BaseResponse<AddressInfo>> getAddressList(@Field("id") String id);

    @FormUrlEncoded
    @POST(Api.me.UPDATEADDRESS)
    Flowable<BaseResponse<String>> updateAddress(@Field("id") String id,
                                                 @Field("status") String status,
                                                 @Field("name") String name,
                                                 @Field("phone") String phone,
                                                 @Field("address") String address,
                                                 @Field("detailed") String detailed);


}
