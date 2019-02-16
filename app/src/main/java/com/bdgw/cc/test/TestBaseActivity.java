package com.bdgw.cc.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.socks.library.KLog;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.mvvm.base.test.BaseActivity;

public class TestBaseActivity extends BaseActivity {

    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button)
    Button button;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_base;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        showSuccess();

        textView2.setText("textView2");
        button2.setText("button2");
        button.setText("button");
    }

    @OnClick({R.id.textView2, R.id.button2, R.id.button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textView2:
                KLog.i("textView2");
                break;
            case R.id.button2:
                KLog.i("button2");
                break;
            case R.id.button:
                KLog.i("button");
                break;
        }
    }
}
