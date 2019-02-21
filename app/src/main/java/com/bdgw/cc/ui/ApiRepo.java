package com.bdgw.cc.ui;

import com.bdgw.cc.http.ApiService;
import com.bdgw.cc.http.RetrofitClient;
import com.bdgw.cc.ui.classify.bean.ClassificationInfo;
import com.bdgw.cc.ui.classify.bean.ReviewListInfo;
import com.bdgw.cc.ui.home.bean.RedItemInfo;
import com.bdgw.cc.ui.home.bean.SearchInfo;
import com.bdgw.cc.ui.home.holder.CatagoryInfo;
import com.bdgw.cc.ui.me.bean.AddressInfo;
import com.bdgw.cc.ui.me.bean.SiteInfo;
import com.bdgw.cc.ui.shopping.bean.GoodsListInfo;
import com.bdgw.cc.ui.shopping.bean.VendorInfo;

import io.reactivex.Flowable;
import me.goldze.common.base.bean.BaseResponse;
import me.goldze.common.http.rx.RxSchedulers;

/**
 * @author GuoFeng
 * @date : 2019/1/23 11:12
 * @description:
 */
public final class ApiRepo {

    private final static ApiService apiService = RetrofitClient.getInstance().getApiService();
    /*-------------------------------------------------------------------首页----------------------------------------------------------------*/

    /*登录*/
    public static Flowable<UserInfo> login(String phone, String password) {
        return apiService.login(phone, password).compose(RxSchedulers.<UserInfo>io_main());
    }

    /*注册*/
    public static Flowable<UserInfo> register(String mobile, String code, String password, String inviteCode) {
        return apiService.register(mobile, code, password, inviteCode)
                .compose(RxSchedulers.<UserInfo>io_main());
    }

    /*获取验证码*/
    public static Flowable<UserInfo> senCode(String mobile, String code) {
        return apiService.senCode(mobile, code)
                .compose(RxSchedulers.<UserInfo>io_main());
    }


    /*重置密码*/
    public static Flowable<UserInfo> reset(String mobile, String code, String password) {
        return apiService.reset(mobile, code, password)
                .compose(RxSchedulers.<UserInfo>io_main());
    }

    /*菜单*/
    public static Flowable<CatagoryInfo> getMenu() {
        return apiService.getMenu().compose(RxSchedulers.<CatagoryInfo>io_main());
    }

    /*热搜*/
    public static Flowable<HotKeyInfo> getHotKeys() {
        return apiService.getHotkeyData().compose(RxSchedulers.<HotKeyInfo>io_main());
    }

    /*搜索list*/
    public static Flowable<SearchInfo> getSearchListData(String k) {
        return apiService.getSearchListData(k).compose(RxSchedulers.<SearchInfo>io_main());
    }

    /*-------------------------------------------------------------------分类---------------------------------------------------*/
    /*商品评价*/
    public static Flowable<ReviewListInfo> getReviewList(String id, int page) {
        return apiService.getReviewList(Long.valueOf(id), page, 20)
                .compose(RxSchedulers.<ReviewListInfo>io_main());
    }

    /*-------------------------------------------------------------------购物车---------------------------------------------------*/
    /*左边listview*/
    public static Flowable<ClassificationInfo> getTreeData() {
        return apiService.getTreeData(1, 10, 1).compose(RxSchedulers.<ClassificationInfo>io_main());
    }

//    public static Flowable<ClassificationInfo> getTreeRightData(int category) {
//        return apiService.getTreeRightData(category).compose(RxSchedulers.<ClassificationInfo>io_main());
//    }

    /*商品详情*/
    public static Flowable<GoodsListInfo> getProduct(long product) {
        return apiService.getProduct(product).compose(RxSchedulers.<GoodsListInfo>io_main());
    }

    /*商品列表*/
    public static Flowable<GoodsListInfo> getProductList(long product, long page, int sortKey) {
        return apiService.getProductList(product, page, 20, 0, sortKey, 2).compose(RxSchedulers.<GoodsListInfo>io_main());
    }

    /*购物车商品数量*/
    public static Flowable<UserInfo> getCartNum() {
        return apiService.getCartNum().compose(RxSchedulers.<UserInfo>io_main());
    }

    /*购物车商品列表*/
    public static Flowable<VendorInfo> getCartList() {
        return apiService.getCartList().compose(RxSchedulers.<VendorInfo>io_main());
    }

    /*删除购物车商品*/
    public static Flowable<UserInfo> deleteCart(String ids) {
        return apiService.deleteCart(ids).compose(RxSchedulers.<UserInfo>io_main());
    }

    /*修改购物车商品数量*/
    public static Flowable<UserInfo> updateCart(String id, int amount) {
        return apiService.updateCart(id, amount).compose(RxSchedulers.<UserInfo>io_main());
    }

    /*添加商品到购物车*/
//    public static Flowable<GoodsListInfo> addCart(String id, int amount, int property) {
//        return apiService.addCart(id, amount, property).compose(RxSchedulers.<GoodsListInfo>io_main());
//    }
    /*添加商品到购物车*/
    public static Flowable<GoodsListInfo> addCart(String id, int amount) {
        return apiService.addCart(id, amount).compose(RxSchedulers.<GoodsListInfo>io_main());
    }

    /*购物车--结算*/
    public static Flowable<UserInfo> checkoutCart() {
        return apiService.checkoutCart().compose(RxSchedulers.<UserInfo>io_main());
    }

    /*-------------------------------------------------------------------我的---------------------------------------------------*/
    /*地址list*/
    public static Flowable<BaseResponse<AddressInfo>> getAddressList(String id) {
        return apiService.getAddressList(id)
                .compose(RxSchedulers.<BaseResponse<AddressInfo>>io_main());
    }

    /*增加、修改地址*/
    public static Flowable<BaseResponse<String>> updateAddress(String id, String status, String name, String phone, String address, String detailed) {
        return apiService.updateAddress(id, status, name, phone, address, detailed)
                .compose(RxSchedulers.<BaseResponse<String>>io_main());
    }

    /*站点信息*/
    public static Flowable<SiteInfo> getSite() {
        return apiService.getSite().compose(RxSchedulers.<SiteInfo>io_main());
    }

    /*获取红包列表*/
    public static Flowable<RedItemInfo> getRedList(String status, long page) {
        return apiService.getRedList(status, page, "20").compose(RxSchedulers.<RedItemInfo>io_main());
    }

    /*我的收藏*/
    public static Flowable<GoodsListInfo> getLikedList(long page) {
        return apiService.getLikedList(page, "20").compose(RxSchedulers.<GoodsListInfo>io_main());
    }

    /*取消收藏*/
    public static Flowable<UserInfo> getUnlike(long product) {
        return apiService.getUnlike(product).compose(RxSchedulers.<UserInfo>io_main());
    }
}