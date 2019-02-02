package com.bdgw.cc.ui.home.vm;

import android.app.Application;
import android.support.annotation.NonNull;

import com.bdgw.cc.ui.home.vm.source.OrderItemRepository;

import me.goldze.common.base.mvvm.AbsViewModel;

/**
 * @author GuoFeng
 * @date : 2019/1/22 14:59
 * @description:
 */
public class OrderItemViewModel extends AbsViewModel<OrderItemRepository> {

    public OrderItemViewModel(@NonNull Application application) {
        super(application);
    }

    public void getOrderData(int page, String status) {
        mRepository.getOrderData(page, "10", status);
    }
}