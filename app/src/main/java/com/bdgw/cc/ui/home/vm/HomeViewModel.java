//package com.bdgw.cc.ui.home.vm;
//
//import android.app.Application;
//import android.support.annotation.NonNull;
//
//import com.bdgw.cc.ui.home.vm.source.HomeRepository;
//
//import me.goldze.common.base.mvvm.AbsViewModel;
//
///**
// * @author GuoFeng
// * @date : 2019/1/20 14:33
// * @description:
// */
//public class HomeViewModel extends AbsViewModel<HomeRepository> {
//
//    public HomeViewModel(@NonNull Application application) {
//        super(application);
//    }
//
//    public void getBannerData() {
//        mRepository.loadBannerData();
//    }
//
//    public void getHomeData(String id) {
//        mRepository.loadHomeData(id);
//    }
//
//    public void getHomeData() {
//        getBannerData();
////        getHomeData();
//
//        mRepository.loadHomeData();
//    }
//}
