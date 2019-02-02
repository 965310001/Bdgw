package com.bdgw.cc.ui.home.vm.source;

import com.bdgw.cc.http.ApiService;
import com.bdgw.cc.http.RetrofitClient;
import com.bdgw.cc.ui.Constants;
import com.bdgw.cc.ui.home.bean.RedItemInfo;
import com.socks.library.KLog;

import me.goldze.common.base.mvvm.base.BaseRepository;
import me.goldze.common.base.mvvm.stateview.StateConstants;
import me.goldze.common.http.rx.RxSchedulers;
import me.goldze.common.http.rx.RxSubscriber;

/**
 * @author GuoFeng
 * @date : 2019/1/21 18:00
 * @description:
 */
public class RedItemRepository extends BaseRepository {

    ApiService apiService = RetrofitClient.getInstance().getApiService();

    public void getRedData(int page, String per_page, String status) {
        KLog.i(this.getClass().getName() + status);
        addDisposable(apiService.getRedData(page, per_page, status)
                .compose(RxSchedulers.<RedItemInfo>io_main())
                .subscribeWith(new RxSubscriber<RedItemInfo>() {
                    @Override
                    protected void onNoNetWork() {
                        showPageState(Constants.Red.EVENT_KEY_RED_LIST[1], StateConstants.NET_WORK_STATE);
                    }

                    @Override
                    public void onSuccess(RedItemInfo redItemInfo) {
                        sendData(Constants.Red.EVENT_KEY_RED_LIST[0], redItemInfo);
                        showPageState(Constants.Red.EVENT_KEY_RED_LIST[1],
                                StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg) {
                        showPageState(Constants.Red.EVENT_KEY_RED_LIST[1], StateConstants.ERROR_STATE);
                    }
                }));
    }
}
