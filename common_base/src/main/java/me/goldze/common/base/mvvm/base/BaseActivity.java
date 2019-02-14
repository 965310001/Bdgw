package me.goldze.common.base.mvvm.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;

import com.tqzhang.stateview.core.LoadManager;
import com.tqzhang.stateview.stateview.BaseStateControl;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author GuoFeng
 * @date :2019/1/16 14:40
 * @description: 基类Activity
 */
public abstract class BaseActivity extends FragmentActivity {

    protected LoadManager loadManager;
    private Unbinder unBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        initStatusBar();
        //设置布局内容
        setContentView(getLayoutId());

        unBinder = ButterKnife.bind(this);

        loadManager = new LoadManager.Builder()
                .setViewParams(this)
                .setListener(new BaseStateControl.OnRefreshListener() {
                    @Override
                    public void onRefresh(View v) {
                        onStateRefresh();
                    }
                })
                .build();
        initViews(savedInstanceState);

        initToolBar();

        initData();
    }

    /*初始化数据*/
    protected void initData() {

    }

    /*状态栏*/
    protected void initStatusBar() {

    }

    /*初始化toolbar*/
    protected void initToolBar() {

    }

    /*设置布局layout*/
    protected abstract @LayoutRes
    int getLayoutId();

    protected void onStateRefresh() {
    }

    /*初始化views*/
    protected abstract void initViews(Bundle savedInstanceState);


    /*显示进度条*/
    public void showProgressBar() {
    }

    /*隐藏进度条*/
    public void hideProgressBar() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unBinder != null) {
            unBinder.unbind();
        }
    }
}