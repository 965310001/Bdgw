package com.bdgw.cc.test;

import android.app.Application;
import android.support.annotation.NonNull;

import me.goldze.common.base.mvvm.AbsViewModel;

public class LoginViewModel extends AbsViewModel<LoginRepository> {

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void login(String phone, String password) {
        mRepository.login(phone, password);
    }
}