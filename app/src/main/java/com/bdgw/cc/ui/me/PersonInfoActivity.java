package com.bdgw.cc.ui.me;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;
import com.socks.library.KLog;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.mvvm.base.BaseActivity;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ToastUtils;
import me.goldze.common.widget.MultipleItemView;
import me.goldze.common.widget.dialog.MaterialDialogUtils;

/**
 * 个人信息
 */

@Route(path = ARouterConfig.Me.PERSONINFOACTIVITY)
public class PersonInfoActivity extends BaseActivity implements MaterialDialog.InputCallback, MaterialDialog.ListCallbackSingleChoice,
        MaterialDialog.SingleButtonCallback {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;


    @BindView(R.id.miv_1)
    MultipleItemView miv1;
    @BindView(R.id.miv_2)
    MultipleItemView miv2;
    @BindView(R.id.miv_3)
    MultipleItemView miv3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_info;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showSuccess();

        ivBack.setVisibility(View.VISIBLE);
        rlTitleBar.setVisibility(View.VISIBLE);
        tvTitle.setText("设置");
    }

    @OnClick({R.id.iv_back, R.id.miv_1, R.id.miv_2, R.id.miv_3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.miv_1:
                if (null == inputDialog) {
                    inputDialog = MaterialDialogUtils.showInputDialog(this, "修改昵称", "", this);
                }
                inputDialog.show();
                break;
            case R.id.miv_2:
                if (null == listDialog) {
                    content = Arrays.asList("男", "女", "保密");
                }
                listDialog = MaterialDialogUtils.showSingleListDialog(this, "性别",
                        content, this);
                listDialog.show();
                break;
            case R.id.miv_3:
                if (null == customDialog) {
                    View v = LayoutInflater.from(this).inflate(R.layout.dialog_modify_password, null);
                    customDialog = MaterialDialogUtils.showCustomDialog(this, "修改密码", v, this);

                    edOldPassword = v.findViewById(R.id.ed_old_password);
                    edNewPassword = v.findViewById(R.id.ed_new_password);
                    edNewRePassword = v.findViewById(R.id.ed_new__repassword);
                }
                customDialog.show();
                break;
        }
    }

    private List<String> content;
    private MaterialDialog.Builder inputDialog, listDialog, customDialog;
    private MaterialEditText edOldPassword, edNewPassword, edNewRePassword;
//    private String oldPassword, newPassword, newRePassword;

    @Override
    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
        miv1.setRightText(input.toString());
    }

    @Override
    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
        miv2.setRightText(text.toString());
        return true;
    }

    @Override
    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        KLog.i(which);
        switch (which) {
            case POSITIVE:
                String oldPassword = edOldPassword.getText().toString().trim();
                String newPassword = edNewPassword.getText().toString().trim();
                String newRePassword = edNewRePassword.getText().toString().trim();
                if (TextUtils.isEmpty(oldPassword)) {
                    ToastUtils.showLong("请输入老密码");
                    return;
                } else if (TextUtils.isEmpty(newPassword)) {
                    ToastUtils.showLong("请输入新密码");
                    return;
                } else if (TextUtils.isEmpty(newRePassword)) {
                    ToastUtils.showLong("请输入确认新密码");
                    return;
                } else if (oldPassword.equals(newPassword)) {
                    ToastUtils.showLong("新密码不能和老密码相同");
                    return;
                } else if (!newRePassword.equals(newPassword)) {
                    ToastUtils.showLong("新密码和确认密码不相同");
                    return;
                } else {
                    edOldPassword.setText("");
                    edNewPassword.setText("");
                    edNewRePassword.setText("");
                    dialog.dismiss();
                }
                break;
        }


    }
}