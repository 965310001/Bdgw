package me.goldze.common.base.mvvm.base.test;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.socks.library.KLog;
import com.tqzhang.stateview.core.LoadManager;
import com.tqzhang.stateview.stateview.BaseStateControl;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.goldze.common.R;

/**
 * @author GuoFeng
 * @date :2019/1/16 14:40
 * @description: 基类Activity
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    protected LoadManager loadManager;

    ImageView ivBack;
    MaterialEditText edSearch;
    TextView tvTitle;
    ImageView ivSearch;
    TextView tvRight;
    RelativeLayout rlTitleBar;

    private Unbinder unBinder;
    private ImmersionBar mImmersionBar;
    private ViewGroup contentLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*竖屏*/
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        /*android软键盘挡住输入框问题*/
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        setContentView(R.layout.activity_base);

        contentLayout = findViewById(R.id.fl_content);
        contentLayout.addView(getLayoutInflater().inflate(getLayoutId(), null));

        loadManager = new LoadManager.Builder().setViewParams(contentLayout)
                .setListener(new BaseStateControl.OnRefreshListener() {
                    @Override
                    public void onRefresh(View v) {
                        onStateRefresh();
                    }
                }).build();

        /*初始化ButterKnife*/
        unBinder = ButterKnife.bind(this);

        if (isActionBar()) {
            ivBack = findViewById(R.id.iv_back);
            tvTitle = findViewById(R.id.tv_title);
            rlTitleBar = findViewById(R.id.rl_title_bar);
            rlTitleBar.setVisibility(View.VISIBLE);

            tvTitle.setText(getTitle());

            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            ivBack.setVisibility(isBack() ? View.VISIBLE : View.GONE);
        }

        /*封装*/
        try {
            ActivityInfo info = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            boolean isBack = Boolean.getBoolean(info.metaData.getString("back"));
            boolean isActionBar = Boolean.getBoolean(info.metaData.getString("isActionBar"));
            KLog.i(isBack + " " + isActionBar);

            if (isActionBar) {
                ivBack = findViewById(R.id.iv_back);
                tvTitle = findViewById(R.id.tv_title);
                rlTitleBar = findViewById(R.id.rl_title_bar);
                rlTitleBar.setVisibility(View.VISIBLE);

                tvTitle.setText(getTitle());

                ivBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

                if (!isBack) {
                    ivBack.setVisibility(isBack ? View.VISIBLE : View.GONE);
                }
            }


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        /*沉浸式状态栏*/
        initImmersionBar(android.R.color.holo_blue_light);

        initViews(savedInstanceState);
        initData();
    }

    /*默认是显示的*/
    protected boolean isBack() {
        return true;
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
        tvTitle.setText(titleId);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        tvTitle.setText(title);
    }

    /*沉浸栏颜色*/
    protected void initImmersionBar(@ColorRes int color) {
        mImmersionBar = ImmersionBar.with(this);
        if (color != 0) {
            mImmersionBar.statusBarColor(color);
        }
        mImmersionBar.init();
    }

    /*是否需要ActionBar*/
    protected boolean isActionBar() {
        return true;
    }

    /*初始化数据*/
    protected void initData() {
    }

    /*设置布局layout*/
    protected abstract @LayoutRes
    int getLayoutId();

    protected void onStateRefresh() {
    }

    /*初始化views*/
    protected abstract void initViews(Bundle savedInstanceState);

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unBinder != null) {
            unBinder.unbind();
        }
        //必须调用该方法，防止内存泄漏
        if (null != mImmersionBar) {
            mImmersionBar.destroy();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            finish();
        }
    }
}