package com.bdgw.cc.ui.me;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;
import com.bdgw.cc.ui.ApiRepo;
import com.bdgw.cc.ui.me.bean.SiteInfo;
import com.socks.library.KLog;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.mvvm.base.BaseActivity;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.http.rx.RxSubscriber;
import me.goldze.common.utils.ActivityToActivity;
import me.goldze.common.utils.ToastUtils;

@Route(path = ARouterConfig.Me.HELPACTIVITY)
public class HelpActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;

    private SiteInfo data;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_help;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showSuccess();

        ivBack.setVisibility(View.VISIBLE);
        rlTitleBar.setVisibility(View.VISIBLE);
        tvTitle.setText("使用帮助");
        getSite();
    }

    @OnClick({R.id.iv_back, R.id.miv_1, R.id.miv_2, R.id.miv_3, R.id.miv_4, R.id.miv_5})
    public void onClick(View view) {
        if (null == data) {
            getSite();
        }
        // TODO: 2019/2/21 修改 
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.miv_1:
                ActivityToActivity.toActivity(ARouterConfig.Me.ABOUTUSACTIVITY);
                break;
            case R.id.miv_2:
                ActivityToActivity.toActivity(ARouterConfig.Me.CUSTOMERSERVICEACTIVITY);
                break;
            case R.id.miv_3:
                ActivityToActivity.toActivity(ARouterConfig.Me.STATIONACTIVITY);
                break;
            case R.id.miv_4:
                ActivityToActivity.toActivity(ARouterConfig.Me.INFORMATIONACTIVITY);
                break;
            case R.id.miv_5:
                ActivityToActivity.toActivity(ARouterConfig.Me.PHYCLASSACTIVITY);
                break;
        }
    }

    private void getSite() {
        ApiRepo.getSite().subscribeWith(new RxSubscriber<SiteInfo>() {

            @Override
            public void onSuccess(SiteInfo response) {
                data = response;
            }

            @Override
            public void onFailure(String msg) {
                KLog.i(msg);
                ToastUtils.showLong(msg);
            }

            @Override
            public void onError(Throwable t) {
                KLog.i(t.getMessage());
                ToastUtils.showLong("请稍后再试");
            }
        });
    }
}
