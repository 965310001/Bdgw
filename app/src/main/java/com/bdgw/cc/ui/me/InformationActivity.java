package com.bdgw.cc.ui.me;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bdgw.cc.R;

import me.goldze.common.base.mvvm.base.test.BaseActivity;
import me.goldze.common.constants.ARouterConfig;

/**
 * 扁担资讯
 */
@Route(path = ARouterConfig.Me.INFORMATIONACTIVITY)
public class InformationActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_information;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        loadManager.showSuccess();
        setTitle("扁担资讯");
    }


}
