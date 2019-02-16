package com.bdgw.cc.ui.me;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bdgw.cc.R;
import com.bdgw.cc.ui.me.bean.AfterSalesInfo;
import com.socks.library.KLog;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.mvvm.base.BaseActivity;
import me.goldze.common.constants.ARouterConfig;

/**
 * 售后详情
 */
@Route(path = ARouterConfig.Me.AFTERSALESDETAILSDCTIVITY)
public class AfterSalesDetailsDctivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;

    @Autowired
    AfterSalesInfo data;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_after_sales_details_dctivity;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showSuccess();
        ARouter.getInstance().inject(this);
        ivBack.setVisibility(View.VISIBLE);
        rlTitleBar.setVisibility(View.VISIBLE);
        tvTitle.setText("售后服务详情");
        KLog.i(data);
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
