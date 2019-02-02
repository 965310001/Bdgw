package com.bdgw.cc.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bdgw.cc.R;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.mvvm.base.BaseActivity;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ProgressFragment;
import me.goldze.common.utils.RegexUtils;
import me.goldze.common.utils.ToastUtils;

/**
 * 忘记密码
 */
@Route(path = ARouterConfig.home.RETRIEVEACTIVITY)
public class RetrieveActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.tv_countdown)
    TextView tvCountdown;
    @BindView(R.id.ed_change_phone)
    MaterialEditText edChangePhone;
    @BindView(R.id.ed_change_password)
    MaterialEditText edChangePassword;
    @BindView(R.id.ed_change_code)
    MaterialEditText edChangeCode;

    private ProgressFragment progressFragment;
    private String phone, password, code;
    private CountDownTimer countDownTimer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_retrieve;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        loadManager.showSuccess();
        ARouter.getInstance().inject(this);

        progressFragment = new ProgressFragment();

        ivBack.setVisibility(View.VISIBLE);
        rlTitleBar.setVisibility(View.VISIBLE);
        tvTitle.setText("密码找回");
    }

    @OnClick({R.id.iv_back, R.id.tv_countdown, R.id.tv_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_register:
                if (checkIsNull()) {
                    if (TextUtils.isEmpty(phone)) {
                        edChangePhone.setError("请输入手机号");
                    }
                    if (TextUtils.isEmpty(code)) {
                        edChangeCode.setError("请输入验证码");
                    }
                    if (TextUtils.isEmpty(password)) {
                        edChangePassword.setError("请输入密码");
                    }
                } else if (!RegexUtils.isMobileExact(phone)) {
                    ToastUtils.showLong("请输入正确的手机号");
                } else if (code.length() < 6 || code.length() > 6) {
                    edChangeCode.setError("请输入6位验证码");
                } else if (password.length() < 6 || password.length() > 15) {
                    ToastUtils.showLong("密码必须在6-15位");
                } else {
                    if (!progressFragment.isVisible()) {
                        progressFragment.show(getSupportFragmentManager(), ARouterConfig.home.RETRIEVEACTIVITY);
                    }
                    change();
                }
                break;
            case R.id.tv_countdown:
                phone = edChangePhone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    edChangePhone.setError("请输入手机号");
                } else {
                    getSureCode();
                }
                break;
        }
    }


    // TODO: 2019/2/1 修改密码
    private void change() {
        progressFragment.dismiss();
        ToastUtils.showLong("正在修改");
        finish();
    }


    private boolean checkIsNull() {
        phone = edChangePhone.getText().toString().trim();
        password = edChangePassword.getText().toString().trim();
        code = edChangeCode.getText().toString().trim();
        return TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) | TextUtils.isEmpty(code);
    }

    /*发送验证码*/
    private void getSureCode() {
        if (null == countDownTimer) {
            countDownTimer = new CountDownTimer(60000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    String value = String.valueOf((int) (millisUntilFinished / 1000));
                    tvCountdown.setText(value);
                    tvCountdown.setEnabled(false);
                }

                @Override
                public void onFinish() {
                    tvCountdown.setText("获取验证码");
                    tvCountdown.setEnabled(true);
                }
            };
        }
        countDownTimer.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (progressFragment.isVisible()) {
            progressFragment.dismiss();
            progressFragment = null;
        }
        if (null != countDownTimer) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }


}
