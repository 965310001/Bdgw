package com.bdgw.cc.ui.me;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;
import com.socks.library.KLog;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.mvvm.base.BaseActivity;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ActivityToActivity;
import me.goldze.common.utils.SharePreferenceUtil;
import me.goldze.common.utils.ToastUtils;
import me.goldze.common.widget.dialog.MaterialDialogUtils;

/**
 * 设置
 */
@Route(path = ARouterConfig.Me.SETTINGACTIVITY)
public class SettingActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showSuccess();

        ivBack.setVisibility(View.VISIBLE);
        rlTitleBar.setVisibility(View.VISIBLE);
        tvTitle.setText("设置");
    }

    @OnClick({R.id.iv_back, R.id.miv_1, R.id.tv_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.miv_1:
                KLog.i("关于我们");
                ActivityToActivity.toActivity(ARouterConfig.Me.ABOUTUSACTIVITY);
                break;
            case R.id.tv_exit:
                KLog.i("退出");
                MaterialDialogUtils.showBasicDialog(this, "确认退出")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                if (SharePreferenceUtil.isClear()) {
                                    ToastUtils.showLong("退出成功！");
                                    finish();
                                }
                            }
                        }).show();
                break;
        }
    }
}