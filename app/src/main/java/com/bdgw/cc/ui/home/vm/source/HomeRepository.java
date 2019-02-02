package com.bdgw.cc.ui.home.vm.source;

import com.bdgw.cc.http.ApiService;
import com.bdgw.cc.http.RetrofitClient;
import com.bdgw.cc.ui.Constants;
import com.bdgw.cc.ui.home.bean.Banner;
import com.bdgw.cc.ui.home.bean.HomeList;
import com.bdgw.cc.ui.home.bean.HomeMerge;

import io.reactivex.Flowable;
import me.goldze.common.base.mvvm.base.BaseRepository;
import me.goldze.common.base.mvvm.stateview.StateConstants;
import me.goldze.common.http.rx.RxSchedulers;
import me.goldze.common.http.rx.RxSubscriber;

/**
 * @author GuoFeng
 * @date : 2019/1/20 14:34
 * @description:
 */
public class HomeRepository extends BaseRepository {

    private Flowable<Banner> mBannerObservable;
//    private Flowable<HomeList> mHomeListObservable;

    private HomeMerge homeMerge = new HomeMerge();

    ApiService apiService = RetrofitClient.getInstance().getApiService();

    public void loadBannerData() {
        mBannerObservable = apiService.getBannerData();
    }

    public void loadHomeData(String id) {
//        Flowable<HomeList> mHomeListObservable = apiService.getHomeData(id);
    }

    public void loadHomeData() {
        addDisposable(mBannerObservable
//                addDisposable(Flowable.concat(mHomeListObservable, mBannerObservable)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<Object>() {

                    @Override
                    protected void onNoNetWork() {
                        showPageState(Constants.EVENT_KEY_WORK_LIST_STATE, StateConstants.NET_WORK_STATE);
                    }

                    @Override
                    public void onSuccess(Object object) {
                        if (object instanceof Banner) {
                            homeMerge.banner = (Banner) object;

                            // TODO: 2019/1/20 测试
                            sendData(Constants.EVENT_KEY_HOME, homeMerge);
                            showPageState(Constants.EVENT_KEY_WORK_LIST_STATE, StateConstants.SUCCESS_STATE);

                        } else if (object instanceof HomeList) {
                            homeMerge.homeList = (HomeList) object;
                            sendData(Constants.EVENT_KEY_HOME, homeMerge);
                            showPageState(Constants.EVENT_KEY_WORK_LIST_STATE, StateConstants.SUCCESS_STATE);
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        showPageState(Constants.EVENT_KEY_WORK_LIST_STATE, StateConstants.ERROR_STATE);
                    }
                }));
    }

}
