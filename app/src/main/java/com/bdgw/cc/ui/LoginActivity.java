package com.bdgw.cc.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;
import com.bdgw.cc.test.LoginViewModel;
import com.socks.library.KLog;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.mvvm.AbsLifecycleActivity;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.http.rx.RxSubscriber;
import me.goldze.common.utils.ActivityToActivity;
import me.goldze.common.utils.ProgressFragment;
import me.goldze.common.utils.SharePreferenceUtil;
import me.goldze.common.utils.ToastUtils;
import me.goldze.xui.button.TextChangeUtils;

/**
 * 登录
 */
@Route(path = ARouterConfig.LOGINACTIVITY)
public class LoginActivity extends AbsLifecycleActivity<LoginViewModel> {

    @BindView(R.id.ed_phone)
    MaterialEditText etPhone;
    @BindView(R.id.ed_password)
    MaterialEditText etPassword;
    @BindView(R.id.btn_login)
    TextView btnLogin;

    private String phone, password;

    ProgressFragment progressFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        showSuccess();

        progressFragment = new ProgressFragment();

        TextChangeUtils.observer(btnLogin, etPhone, etPassword);
        etPhone.setText("139280079711");
        etPassword.setText("123456");
    }

    @OnClick({R.id.tv_to_register, R.id.btn_login, R.id.tv_retrievet})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_to_register:
                ActivityToActivity.toActivity(ARouterConfig.REGISTERACTIVITY);
                /*finish();*/
                break;
            case R.id.tv_retrievet://忘记密码
                ActivityToActivity.toActivity(ARouterConfig.home.RETRIEVEACTIVITY);
                break;

            case R.id.btn_login:
                if (checkIsNull()) {
                   /* if (TextUtils.isEmpty(phone)) {
                        etPhone.setError("请输入手机号");
                    }*/
                    if (TextUtils.isEmpty(password)) {
                        etPassword.setError("请输入密码");
                    }
                }
               /* else if (!RegexUtils.isMobileExact(phone)) {
                    ToastUtils.showLong("请输入正确的手机号");
                }*/
                else if (password.length() < 6 || password.length() > 15) {
                    ToastUtils.showLong("密码必须在6-15位");
                } else {
                    if (!progressFragment.isVisible()) {
                        progressFragment.show(getSupportFragmentManager(), ARouterConfig.LOGINACTIVITY);
                    }
                    login(phone, password);
                }
                break;
        }
    }

    private void login(final String phone, String password) {
        ApiRepo.login(phone, password).subscribeWith(new RxSubscriber<UserInfo>() {

            @Override
            public void onSuccess(UserInfo response) {
                if (progressFragment.getDialog().isShowing()) {
                    progressFragment.dismiss();
                }
                if (!response.isSuccess()) {
                    ToastUtils.showLong(response.getErrorMsg());
                } else {
                    SharePreferenceUtil.saveUser(response);
                    ActivityToActivity.toActivity(ARouterConfig.MAINACTIVITY);
                    finish();
                }
            }

            @Override
            public void onFailure(String msg) {
                KLog.i(msg);
                ToastUtils.showLong(msg);
                if (progressFragment.getDialog().isShowing()) {
                    progressFragment.dismiss();
                }
            }

            @Override
            public void onError(Throwable t) {
                KLog.i(t.getMessage());
                ToastUtils.showLong("请稍后再试");
                if (progressFragment.getDialog().isShowing()) {
                    progressFragment.dismiss();
                }
            }
        });
    }

    private boolean checkIsNull() {
        phone = etPhone.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        return TextUtils.isEmpty(phone) || TextUtils.isEmpty(password);
    }

}