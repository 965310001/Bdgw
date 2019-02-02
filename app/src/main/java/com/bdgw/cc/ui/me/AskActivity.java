package com.bdgw.cc.ui.me;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bdgw.cc.R;
import com.bdgw.cc.ui.me.bean.AfterSalesInfo;
import com.socks.library.KLog;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.mvvm.base.BaseActivity;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ImageUtils;
import me.goldze.common.utils.ToastUtils;
import me.goldze.common.widget.CountClickView;
import me.goldze.common.widget.dialog.MaterialDialogUtils;

/**
 * 提交售后
 */
@Route(path = ARouterConfig.Me.ASKACTIVITY)
public class AskActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;

    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.tv_reason)
    TextView tvReason;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_sub_state)
    TextView tvSubState;

    @Autowired
    AfterSalesInfo data;
    @BindView(R.id.ccv_click)
    CountClickView ccvClick;
    @BindView(R.id.ed_des)
    MaterialEditText edDes;


    MaterialDialog.Builder singleListDialog;
    String des;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ask;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        KLog.i(data);

        loadManager.showSuccess();

        ivBack.setVisibility(View.VISIBLE);
        rlTitleBar.setVisibility(View.VISIBLE);
        tvTitle.setText("提交售后");
    }

    @Override
    protected void initData() {
        super.initData();

        ccvClick.setMinCount(1);

        tvDes.setText(data.getDes());
        tvReason.setText(data.getReason());
        tvState.setText(data.getStatus());
        ImageUtils.loadImage(ivIcon, data.getImg());
    }

    @OnClick({R.id.iv_back, R.id.tv_sub_state, R.id.tv_sub})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_sub_state:
                /*售后类型*/
                singleListDialog = MaterialDialogUtils.showSingleListDialog(this, "请选择售后类型",
                        Arrays.asList("提前换货", "保修期保修", "包换期换货"
                                , "退货退款", "其他"), new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                tvSubState.setText(text);
                                return false;
                            }
                        }
                );
                singleListDialog.show();
                break;
            case R.id.tv_sub:
                // TODO: 2019/2/2 提交售后
                des = edDes.getText().toString().trim();
                if (TextUtils.isEmpty(des)) {
                    ToastUtils.showLong("请输入内容");
                    return;
                }
                if (ccvClick.getCount() < 1) {
                    ToastUtils.showLong("请选择提交的数量");
                    return;
                }
                ToastUtils.showLong("描述" + des + " " + "产品数量：" + ccvClick.getCount());

                break;
        }
    }

}