package com.bdgw.cc.ui.me;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.socks.library.KLog;

import me.goldze.common.constants.ARouterConfig;

@Route(path = ARouterConfig.Me.INTEGRALRECORDACTIVITY)
public class IntegralRecordActivity extends DefiniteActivity {

    @Autowired
    int postion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        ARouter.getInstance().inject(this);

//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                DisplayUtil.px2dp(this,300));
//
//        View view = LayoutInflater.from(this).inflate(R.layout.activity_test, null);
//        view.setLayoutParams(layoutParams);
//        LinearLayout linearLayout = findViewById(R.id.ll_content);
//        linearLayout.addView(view, 1);

        KLog.i("我的积分");

        setText("我的积分");

        onPageSelected(postion);

    }

}
