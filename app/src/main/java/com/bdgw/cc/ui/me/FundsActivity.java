package com.bdgw.cc.ui.me;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;
import com.socks.library.KLog;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.mvvm.base.test.BaseActivity;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ActivityToActivity;

@Route(path = ARouterConfig.Me.FUNDSACTIVITY)
public class FundsActivity extends BaseActivity {

    @BindView(R.id.tv_money)
    TextView tvMoney;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_funds;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showSuccess();
        setTitle("可用资金");
    }

    @Override
    protected void initData() {
        super.initData();

        tvMoney.setText("925.91");
    }

    @OnClick({R.id.tv_extract, R.id.tv_extract_list, R.id.tv_definite})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_extract:
                KLog.i("json");
                ActivityToActivity.toActivity(ARouterConfig.Me.EXTRACTACTIVITY);
                break;
            case R.id.tv_extract_list:
                /*提取记录*/
                ActivityToActivity.toActivity(ARouterConfig.Me.EXTRACTLISTACTIVITY);
                break;
            case R.id.tv_definite:
                KLog.i("明细");
                ActivityToActivity.toActivity(ARouterConfig.Me.DEFINITEACTIVITY);
                break;
        }
    }
}

//public class FundsActivity extends ActionBarActivity {
//
//    private TextView tvMoney;// tvExtract, tvExtractList, tvDefinite;
//
//    @Override
//    protected int getContentLayoutId() {
//        return R.layout.activity_funds;
//    }
//
//    @Override
//    protected void initViews(Bundle savedInstanceState) {
//        super.initViews(savedInstanceState);
//
//        tvMoney = findViewById(R.id.tv_money);
//        TextView tvExtractList = findViewById(R.id.tv_extract_list);
//        TextView tvExtract = findViewById(R.id.tv_extract);
//        TextView tvDefinite = findViewById(R.id.tv_definite);
//
//        Listener listener = new Listener();
//        tvExtract.setOnClickListener(listener);
//        tvExtractList.setOnClickListener(listener);
//        tvDefinite.setOnClickListener(listener);
//
//        showSuccess();
//
//        setTitleText("可用资金");
//
//    }
//
//    @Override
//    protected void initData() {
//        super.initData();
//
//        tvMoney.setText("925.91");
//    }
//
//    class Listener implements View.OnClickListener {
//        @Override
//        public void onClick(View view) {
//            switch (view.getId()) {
//                case R.id.tv_extract:
//                    KLog.i("json");
//                    ActivityToActivity.toActivity(ARouterConfig.Me.EXTRACTACTIVITY);
//                    break;
//                case R.id.tv_extract_list:
//                    /*提取记录*/
//                    ActivityToActivity.toActivity(ARouterConfig.Me.EXTRACTLISTACTIVITY);
//                    break;
//                case R.id.tv_definite:
//                    KLog.i("明细");
//                    ActivityToActivity.toActivity(ARouterConfig.Me.DEFINITEACTIVITY);
//                    break;
//            }
//        }
//    }
//
//}