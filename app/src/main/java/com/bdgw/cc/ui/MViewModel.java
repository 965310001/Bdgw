package com.bdgw.cc.ui;

import android.app.Application;
import android.support.annotation.NonNull;

import com.bdgw.cc.http.ApiService;
import com.bdgw.cc.http.RetrofitClient;
import com.bdgw.cc.ui.home.bean.Banner;
import com.bdgw.cc.ui.home.bean.HomeList;
import com.bdgw.cc.ui.home.bean.HomeMerge;

import me.goldze.common.base.mvvm.AbsViewModel;
import me.goldze.common.base.mvvm.base.BaseRepository;
import me.goldze.common.base.mvvm.stateview.StateConstants;
import me.goldze.common.http.rx.RxSchedulers;
import me.goldze.common.http.rx.RxSubscriber;

/**
 * @author GuoFeng
 * @date : 2019/1/22 16:24
 * @description: 主ViewModel
 */
public class MViewModel extends AbsViewModel<MViewModel.MRepository> {

    public MViewModel(@NonNull Application application) {
        super(application);
        mRepository = new MRepository();
    }

    public void getBannerData() {
        mRepository.getBannerData();
    }

    public void getHomeData() {
        getBannerData();
    }

    /*-------------------------------------------------------------------首页---------------------------------------------------*/
    /*-------------------------------------------------------------------分类---------------------------------------------------*/
    /*-------------------------------------------------------------------购物车-------------------------------------------------*/
    /*-------------------------------------------------------------------我的---------------------------------------------------*/

    class MRepository extends BaseRepository {

        ApiService apiService = RetrofitClient.getInstance().getApiService();
        private HomeMerge homeMerge;

        public void getBannerData() {
            homeMerge = new HomeMerge();
            addDisposable(apiService.getBannerData().compose(RxSchedulers.io_main())
                    .subscribeWith(new MRxSubscriber(Constants.EVENT_KEY_HOME, Constants.EVENT_KEY_WORK_LIST_STATE)));
        }

        class MRxSubscriber extends RxSubscriber<Object> {

            @Override
            public void onSuccess(Object object) {
                if (object instanceof Banner) {
                    homeMerge.banner = (Banner) object;
                    sendData(state, homeMerge);
                    showPageState(listState, StateConstants.SUCCESS_STATE);
                } else if (object instanceof HomeList) {
                    homeMerge.homeList = (HomeList) object;
                    sendData(state, homeMerge);
                    showPageState(listState, StateConstants.SUCCESS_STATE);
                }
            }

            String listState, state;

            MRxSubscriber(String state, String listState) {
                this.state = state;
                this.listState = listState;
            }

            @Override
            protected void onNoNetWork() {
                showPageState(listState, StateConstants.NET_WORK_STATE);
            }

            @Override
            public void onFailure(String msg) {
                showPageState(listState, StateConstants.ERROR_STATE);
            }
        }
        /*-------------------------------------------------------------------首页---------------------------------------------------*/
        /*-------------------------------------------------------------------分类---------------------------------------------------*/
        /*-------------------------------------------------------------------购物车-------------------------------------------------*/
        /*-------------------------------------------------------------------我的---------------------------------------------------*/
    }


}
