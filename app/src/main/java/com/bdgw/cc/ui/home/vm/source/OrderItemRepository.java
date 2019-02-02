package com.bdgw.cc.ui.home.vm.source;

import com.bdgw.cc.http.ApiService;
import com.bdgw.cc.http.RetrofitClient;
import com.bdgw.cc.ui.Constants;
import com.bdgw.cc.ui.home.bean.OrderInfo;
import com.google.gson.Gson;

import me.goldze.common.base.mvvm.base.BaseRepository;
import me.goldze.common.base.mvvm.stateview.StateConstants;
import me.goldze.common.utils.AssetsUtils;
import me.goldze.common.utils.Utils;

/**
 * @author GuoFeng
 * @date : 2019/1/22 14:59
 * @description:
 */
public class OrderItemRepository extends BaseRepository {

    ApiService apiService = RetrofitClient.getInstance().getApiService();

    public void getOrderData(int page, String per_page, String status) {
        OrderInfo orderInfo = new Gson().fromJson(AssetsUtils.getStringFromAssert(Utils.getApplication(),
                "OrderList.json"), OrderInfo.class);

        sendData(Constants.Order.EVENT_KEY_ORDER_LIST[0], status, orderInfo);
        showPageState(Constants.Order.EVENT_KEY_ORDER_LIST[1],
                StateConstants.SUCCESS_STATE);

// TODO: 2019/1/25 需要取消
//        addDisposable(apiService.getOrderData(page, per_page, status)
//                .compose(RxSchedulers.<OrderInfo>io_main())
//                .subscribeWith(new RxSubscriber<OrderInfo>() {
//                    @Override
//                    protected void onNoNetWork() {
//                        showPageState(Constants.Order.EVENT_KEY_ORDER_LIST[1], StateConstants.NET_WORK_STATE);
//                    }
//
//                    @Override
//                    public void onSuccess(OrderInfo orderInfo) {
//                        sendData(Constants.Red.EVENT_KEY_RED_LIST[0], orderInfo);
//                        showPageState(Constants.Order.EVENT_KEY_ORDER_LIST[1],
//                                StateConstants.SUCCESS_STATE);
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//                        KLog.i(msg);
//                        showPageState(Constants.Order.EVENT_KEY_ORDER_LIST[1], StateConstants.ERROR_STATE);
//                    }
//                }));
    }
}