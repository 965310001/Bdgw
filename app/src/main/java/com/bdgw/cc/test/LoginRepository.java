package com.bdgw.cc.test;

import com.bdgw.cc.http.ApiService;
import com.bdgw.cc.http.RetrofitClient;

import me.goldze.common.base.mvvm.base.BaseRepository;

/**
 * @author GuoFeng
 * @date : 2019/1/19 16:33
 * @description:
 */
public class LoginRepository extends BaseRepository {

    protected ApiService apiService = RetrofitClient.getInstance().getApiService();

    public void login(String phone, String password) {
/*
        addDisposable(Flowable.concat(apiService.login(phone, password),
                apiService.login(phone, password))
                .compose(RxSchedulers.<Result>io_main())
                .subscribeWith(new RxSubscriber<Result>() {
                    @Override
                    public void onSuccess(Result result) {
                        KLog.i(result.getDataX().getUsername());
                        sendData("EVENT_KEY_WORK_MORE", result);
                    }

                    @Override
                    public void onFailure(String msg) {
                        KLog.i(msg);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);

                    }
                })
        );
*/
    }


}


