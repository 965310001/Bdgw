package com.bdgw.cc.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;
import com.socks.library.KLog;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.bean.BaseResponse;
import me.goldze.common.base.mvvm.base.BaseActivity;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.http.rx.RxSubscriber;
import me.goldze.common.utils.ActivityToActivity;
import me.goldze.common.utils.ProgressFragment;
import me.goldze.common.utils.RegexUtils;
import me.goldze.common.utils.ToastUtils;
import me.goldze.xui.button.TextChangeUtils;


/**
 * 注册
 */
@Route(path = ARouterConfig.REGISTERACTIVITY)
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;

    @BindView(R.id.ed_register_name)
    MaterialEditText edPhone;
    @BindView(R.id.ed_register_password)
    MaterialEditText edPassword;
    @BindView(R.id.ed_register_repassword)
    MaterialEditText edRepassword;

    @BindView(R.id.btn_register)
    TextView btnRegister;

    String phone, password, repassword;

    ProgressFragment progressFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        loadManager.showSuccess();
        progressFragment = new ProgressFragment();

        ivBack.setVisibility(View.VISIBLE);
        rlTitleBar.setVisibility(View.VISIBLE);
        tvTitle.setText("注册");

        TextChangeUtils.observer(btnRegister, edPhone, edPassword, edRepassword);
    }

    @OnClick({R.id.iv_back, R.id.btn_register, R.id.tv_to_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_register:
                if (checkIsNull()) {
                    if (TextUtils.isEmpty(phone)) {
                        edPhone.setError("请输入手机号");
                    }
                    if (TextUtils.isEmpty(password)) {
                        edPassword.setError("请输入密码");
                    }
                    if (TextUtils.isEmpty(repassword)) {
                        edRepassword.setError("请输入密码");
                    }
                } else if (!RegexUtils.isMobileExact(phone)) {
                    ToastUtils.showLong("请输入正确的手机号");
                } else if (password.length() < 6 || password.length() > 15) {
                    ToastUtils.showLong("密码必须在6-15位");
                } else if (!password.equals(repassword)) {
                    ToastUtils.showLong("密码不一样");
                } else {
                    if (!progressFragment.isVisible()) {
                        progressFragment.show(getSupportFragmentManager(), ARouterConfig.REGISTERACTIVITY);
                    }
                    register(phone, password, repassword);
                }
                break;
            case R.id.tv_to_login:
                ActivityToActivity.toActivity(ARouterConfig.LOGINACTIVITY);
                finish();
                break;
        }
    }

    private void register(final String phone, String password, String repassword) {
        ApiRepo.register(phone, password, repassword).subscribeWith(new RxSubscriber<BaseResponse<UserInfo>>() {

            @Override
            public void onSuccess(BaseResponse<UserInfo> response) {
                if (progressFragment.getDialog().isShowing()) {
                    progressFragment.dismiss();
                }

                KLog.i(response.getErrorMsg() + response.getErrorCode());

                if (!response.isSuccess()) {
                    ToastUtils.showLong(response.getErrorMsg());
                } else {
                    ActivityToActivity.toActivity(ARouterConfig.LOGINACTIVITY);
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
        phone = edPhone.getText().toString().trim();
        password = edPassword.getText().toString().trim();
        repassword = edRepassword.getText().toString().trim();

        return TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(repassword);
    }
}
