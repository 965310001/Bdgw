package me.goldze.common.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import me.goldze.common.R;
import me.goldze.common.R2;
import me.goldze.common.base.mvvm.base.BaseActivity;

public abstract class ActionBarActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.iv_search)
    ImageView ivSearch;
    @BindView(R2.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R2.id.content_layout)
    FrameLayout contentLayout;
    @BindView(R2.id.iv_back)
    ImageView ivBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_action_bar;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        contentLayout.removeAllViews();
        contentLayout.addView(LayoutInflater.from(this).inflate(getContentLayoutId(), null));
        if (isActionBar()) {
            rlTitleBar.setVisibility(View.VISIBLE);
            ivBack.setVisibility(View.VISIBLE);

            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            if (ivSearch.getVisibility() == View.VISIBLE) {
                ivSearch.setOnClickListener(this);
            }
        }
    }

    protected abstract int getContentLayoutId();

    protected void setTitleText(String title) {
        if (isActionBar()) {
            tvTitle.setText(title);
        } else {
            new Exception("没有bar");
        }
    }

    protected void setTitleText(int title) {
        if (isActionBar()) {
            tvTitle.setText(title);
        } else {
            new Exception("没有bar");
        }
    }

    protected boolean isActionBar() {
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R2.id.iv_back:
                finish();
                break;
            case R2.id.iv_search:
                break;
        }
    }
}
