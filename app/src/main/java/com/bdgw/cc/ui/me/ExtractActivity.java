package com.bdgw.cc.ui.me;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;
import com.socks.library.KLog;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.mvvm.base.test.BaseActivity;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ToastUtils;
import me.goldze.common.widget.dialog.MaterialDialogUtils;

/**
 * 提现
 */
@Route(path = ARouterConfig.Me.EXTRACTACTIVITY)
public class ExtractActivity extends BaseActivity {

    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.ed_draw_price)
    EditText edDrawPrice;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_number)
    EditText edNumber;
    @BindView(R.id.ed_des)
    EditText edDes;
    @BindView(R.id.tv_draw_fun_status)
    TextView tvDrawFunStatus;

    String drawPrice, name, number, des;
    MaterialDialog.Builder singleListDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_extract;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        loadManager.showSuccess();

        tvTotalMoney.setText("10000000000000000000000");
    }

    @OnClick({R.id.tv_draw_fun_status, R.id.tv_sub})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_draw_fun_status:
                /*支付方式*/
                singleListDialog = MaterialDialogUtils.showSingleListDialog(this, "请选择提现方式",
                        Arrays.asList("支付宝", "银行卡"), new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                tvDrawFunStatus.setText(text);
                                return false;
                            }
                        }
                );
                singleListDialog.show();
                break;
            case R.id.tv_sub:/*提交*/
                drawPrice = edDrawPrice.getText().toString().trim();
                name = edName.getText().toString().trim();
                number = edNumber.getText().toString().trim();
                des = edDes.getText().toString().trim();

                if (TextUtils.isEmpty(drawPrice)) {
                    ToastUtils.showLong("请输入你要提现多少");
                    return;
                } else if (Double.parseDouble(drawPrice) < 1.00f) {
                    ToastUtils.showLong("请输入你要提现必须大于1");
                    return;
                } else if (TextUtils.isEmpty(name)) {
                    ToastUtils.showLong("请输入你的姓名");
                    return;
                } else if (TextUtils.isEmpty(number)) {
                    ToastUtils.showLong("请输入你的账号");
                    return;
                }
                KLog.i(drawPrice + " " + name + " " + number + " " + des);
                finish();
                break;
        }
    }

}