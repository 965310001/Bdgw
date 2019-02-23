package com.bdgw.cc.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;
import com.socks.library.KLog;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.edittext.materialedittext.validation.RegexpValidator;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
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
    @BindView(R.id.tv_countdown)
    TextView tvCountdown;
    @BindView(R.id.ed_register_name)
    MaterialEditText edPhone;
    @BindView(R.id.ed_register_code)
    MaterialEditText edCode;
    @BindView(R.id.ed_register_password)
    MaterialEditText edPassword;
    @BindView(R.id.ed_register_repassword)
    MaterialEditText edInviteCode;

    @BindView(R.id.btn_register)
    TextView btnRegister;

    String phone, code, password, inviteCode;

    private ProgressFragment progressFragment;

    private CountDownTimer countDownTimer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showSuccess();
        progressFragment = new ProgressFragment();

        ivBack.setVisibility(View.VISIBLE);
        rlTitleBar.setVisibility(View.VISIBLE);
        tvTitle.setText("注册");

        TextChangeUtils.observer(tvCountdown, edPhone);
        TextChangeUtils.observer(btnRegister, edPhone, edCode, edPassword);


        edPhone.addValidator(new RegexpValidator("请输入正确的手机号码", RegexUtils.RegexConstants.REGEX_MOBILE_EXACT));
//        edPhone.validate();  判断是否正确
    }

    @OnClick({R.id.iv_back, R.id.btn_register, R.id.tv_to_login, R.id.tv_countdown})
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
                    if (TextUtils.isEmpty(code)) {
                        edInviteCode.setError("请输入验证码");
                    }
                    if (TextUtils.isEmpty(password)) {
                        edPassword.setError("请输入密码");
                    }
                } else if (!RegexUtils.isMobileExact(phone)) {
                    ToastUtils.showLong("请输入正确的手机号");
                }
               /* else if (!password.equals(code)) {
                    ToastUtils.showLong("验证码长度必须大于4位");
                }*/
//                else if (!RegexUtils.isPassword(password)) {
//                    ToastUtils.showLong("至少包含一个大写字母或小字母 不能有空格");
//                }
                else if (password.length() < 6 || password.length() > 15) {
                    ToastUtils.showLong("密码必须在6-15位");
                } else {
                    if (!progressFragment.isVisible()) {
                        progressFragment.show(getSupportFragmentManager(), ARouterConfig.REGISTERACTIVITY);
                    }

                    /*register(phone, code, password, inviteCode);*/

                    Map<String, String> map = new HashMap<>();
                    map.put("phone", phone);
                    map.put("code", code);
                    map.put("password", password);
                    map.put("invitecode", inviteCode);
                    ActivityToActivity.toActivity(ARouterConfig.REGISTERACTIVITY2, map);
                }
                break;
            case R.id.tv_to_login:
                ActivityToActivity.toActivity(ARouterConfig.LOGINACTIVITY);
                finish();
                break;
            case R.id.tv_countdown:
                phone = edPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    edPhone.setError("请输入手机号");
                } else {
                  /*  if (edPhone.validate()) {
                        KLog.i("输入正确");
                    }*/
                    if (!RegexUtils.isMobileExact(phone)) {
                        ToastUtils.showLong("请输入正确的手机号");
                    } else {
                        getSureCode();
                    }
                }
                break;
        }
    }

    /*发送验证码*/
    private void getSureCode() {
        /*code 表示区号*/
        if (null == countDownTimer) {
            countDownTimer = new CountDownTimer(60000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    String value = String.valueOf((int) (millisUntilFinished / 1000));
                    tvCountdown.setText(value);
                    tvCountdown.setEnabled(false);
                    edPhone.setEnabled(false);
                }

                @Override
                public void onFinish() {
                    tvCountdown.setText("获取验证码");
                    tvCountdown.setEnabled(true);
                    edPhone.setEnabled(true);
                }
            };
        }

        ApiRepo.senCode(phone, code).subscribeWith(new RxSubscriber<UserInfo>() {
            @Override
            public void onSuccess(UserInfo response) {
              /*  if (progressFragment.getDialog().isShowing()) {
                    progressFragment.dismiss();
                }*/
                KLog.i(response.getErrorMsg() + response.getError_desc());
                if (!response.isSuccess()) {
                    ToastUtils.showLong(response.getErrorMsg());
                } else {
                    countDownTimer.start();
                }
            }

            @Override
            public void onFailure(String msg) {
                KLog.i(msg);
                ToastUtils.showLong(msg);
               /* if (progressFragment.getDialog().isShowing()) {
                    progressFragment.dismiss();
                }*/
            }

            @Override
            public void onError(Throwable t) {
                KLog.i(t.getMessage());
                ToastUtils.showLong("请稍后再试");
              /*  if (progressFragment.getDialog().isShowing()) {
                    progressFragment.dismiss();
                }*/
            }
        });
    }

    private void register(final String phone, final String code, final String password, final String inviteCode) {
        ApiRepo.register(phone, code, password, inviteCode).subscribeWith(new RxSubscriber<UserInfo>() {

            @Override
            public void onSuccess(UserInfo response) {
                if (progressFragment.getDialog().isShowing()) {
                    progressFragment.dismiss();
                }

                KLog.i(response.getErrorMsg() + response.getError_desc());

                if (!response.isSuccess()) {
                    ToastUtils.showLong(response.getErrorMsg());
                } else {
//                    ActivityToActivity.toActivity(ARouterConfig.LOGINACTIVITY);
//                    finish();

                    /*第二部*/
                    Map<String, String> map = new HashMap<>();
                    map.put("phone", phone);
                    map.put("code", code);
                    map.put("password", password);
                    map.put("invitecode", inviteCode);
                    ActivityToActivity.toActivity(ARouterConfig.REGISTERACTIVITY2, map);
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
        code = edCode.getText().toString().trim();
        password = edPassword.getText().toString().trim();
        inviteCode = edInviteCode.getText().toString().trim();

        return TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(code);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != progressFragment && progressFragment.isVisible()) {
            progressFragment.dismiss();
            progressFragment = null;
        }
        if (null != countDownTimer) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }
}
