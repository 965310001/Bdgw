package com.bdgw.cc.ui.home.vm;

import android.app.Application;
import android.support.annotation.NonNull;

import com.bdgw.cc.ui.home.vm.source.RedItemRepository;

import me.goldze.common.base.mvvm.AbsViewModel;

/**
 * @author GuoFeng
 * @date : 2019/1/21 18:00
 * @description: 红包ViewModel
 */
public class RedItemViewModel extends AbsViewModel<RedItemRepository> {

    public RedItemViewModel(@NonNull Application application) {
        super(application);
    }

    public void getRedData(int page, String status) {
        mRepository.getRedData(page, "10", status);
    }

}
