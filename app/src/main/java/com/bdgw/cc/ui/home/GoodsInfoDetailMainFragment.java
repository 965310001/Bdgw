package com.bdgw.cc.ui.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bdgw.cc.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.mvvm.base.BaseFragment;

/**
 * 商品详情 -图文详情 主Fragment
 * 顶部为商品详情 商品规格 2个Tba 点击可切换下方Fragment的父 Fragment
 */
public class GoodsInfoDetailMainFragment extends BaseFragment {

    @BindView(R.id.tv_goods_detail)
    TextView tvGoodsDetail;
    @BindView(R.id.tv_goods_config)
    TextView tvGoodsConfig;
    @BindView(R.id.v_tab_cursor)
    View vTabCursor;
    @BindView(R.id.fl_goods_content)
    FrameLayout flContent;

    private int nowIndex;
    private float fromX;
    private List<TextView> tabTextList;
    private GoodsInfoWebFragment goodsDetailWebFragment;
    private GoodsConfigFragment goodsConfigFragment;
    private Fragment currentFragment;//当前显示的Fragment
    private FragmentManager fragmentManager;

    public GoodsInfoDetailMainFragment() {
    }

    public static GoodsInfoDetailMainFragment newInstance() {
        return new GoodsInfoDetailMainFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_goods_info_detail_main;
    }

    @Override
    protected int getContentResId() {
        return R.id.content_layout;
    }

    @Override
    public void initView(Bundle state) {
        showSuccess();
        tabTextList = new ArrayList<>();
        tabTextList.add(tvGoodsDetail);
        tabTextList.add(tvGoodsConfig);
        setData();
    }

    /**
     * 商品信息Fragment页获取完数据执行
     */
    public void setData() {
        currentFragment = goodsDetailWebFragment = new GoodsInfoWebFragment();
        goodsConfigFragment = new GoodsConfigFragment();
        fragmentManager = getChildFragmentManager();
        //默认显示商品详情tab
        fragmentManager.beginTransaction().replace(R.id.fl_goods_content, currentFragment).commitAllowingStateLoss();
    }

    @OnClick({R.id.tv_goods_detail, R.id.tv_goods_config})
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_goods_detail) {//商品详情tab
            switchFragment(currentFragment, goodsDetailWebFragment);
            nowIndex = 0;
            currentFragment = goodsDetailWebFragment;
            scrollCursor();
        } else if (i == R.id.tv_goods_config) {//规格参数tab
            switchFragment(currentFragment, goodsConfigFragment);
            nowIndex = 1;
            currentFragment = goodsConfigFragment;
            scrollCursor();
        }
    }

    /**
     * 滑动游标
     */
    private void scrollCursor() {
        TranslateAnimation anim = new TranslateAnimation(fromX, nowIndex * vTabCursor.getWidth(), 0, 0);
        anim.setFillAfter(true);//设置动画结束时停在动画结束的位置
        anim.setDuration(50);
        //保存动画结束时游标的位置,作为下次滑动的起点
        fromX = nowIndex * vTabCursor.getWidth();
        vTabCursor.startAnimation(anim);

        //设置Tab切换颜色
        for (int i = 0; i < tabTextList.size(); i++) {
            tabTextList.get(i).setTextColor(i == nowIndex ? getResources().getColor(R.color.red) : getResources().getColor(R.color.black));
        }
    }

    /**
     * 切换Fragment
     */
    private void switchFragment(Fragment fromFragment, Fragment toFragment) {
        if (currentFragment != toFragment) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (!toFragment.isAdded()) {    // 先判断是否被add过
                fragmentTransaction.hide(fromFragment).add(R.id.fl_goods_content, toFragment).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到activity中
            } else {
                fragmentTransaction.hide(fromFragment).show(toFragment).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
        }
    }


}
