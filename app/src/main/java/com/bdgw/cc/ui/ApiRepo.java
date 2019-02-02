package com.bdgw.cc.ui;

import com.bdgw.cc.http.ApiService;
import com.bdgw.cc.http.RetrofitClient;
import com.bdgw.cc.ui.classify.bean.ClassificationInfo;
import com.bdgw.cc.ui.home.bean.SearchInfo;
import com.bdgw.cc.ui.me.bean.AddressInfo;

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
    public static Flowable<BaseResponse<UserInfo>> login(String phone, String password) {
        return apiService.login(phone, password).compose(RxSchedulers.<BaseResponse<UserInfo>>io_main());
    }

    /*注册*/
    public static Flowable<BaseResponse<UserInfo>> register(String phone, String password, String repassword) {
        return apiService.register(phone, password, repassword).compose(RxSchedulers.<BaseResponse<UserInfo>>io_main());
    }


    /*搜索体系*/
    public static Flowable<HotKeyInfo> getHotKeys() {
        return apiService.getHotkeyData().compose(RxSchedulers.<HotKeyInfo>io_main());
    }

    /*搜索list*/
    public static Flowable<SearchInfo> getSearchListData(String k) {
        return apiService.getSearchListData(k).compose(RxSchedulers.<SearchInfo>io_main());
    }

    /*-------------------------------------------------------------------分类---------------------------------------------------*/
    /*左边listview*/
    public static Flowable<ClassificationInfo> getTreeData() {
        return apiService.getTreeData().compose(RxSchedulers.<ClassificationInfo>io_main());
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


}
