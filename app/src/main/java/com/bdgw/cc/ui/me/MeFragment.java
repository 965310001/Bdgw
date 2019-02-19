package com.bdgw.cc.ui.me;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.http.Result;
import com.bdgw.cc.test.LoginViewModel;
import com.bdgw.cc.ui.UserInfo;
import com.leon.lib.settingview.LSettingItem;
import com.socks.library.KLog;
import com.xuexiang.xui.widget.textview.badge.BadgeView;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.event.LiveBus;
import me.goldze.common.base.mvvm.AbsLifecycleFragment;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ActivityToActivity;
import me.goldze.common.utils.ImageUtils;
import me.goldze.common.utils.SharePreferenceUtil;

/**
 * 我的
 */
public class MeFragment extends AbsLifecycleFragment<LoginViewModel> {

    @BindView(R.id.iv_user_head)
    ImageView ivUserHead;

    @BindView(R.id.tv_user_integral)
    TextView tvUserIntegral;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    @BindView(R.id.tabs)
    TabLayout tabs;

    @BindView(R.id.menu_tv1)
    TextView menuTv;

    public MeFragment() {
    }

    public static MeFragment newInstance() {
        return new MeFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_me;
    }

    @Override
    protected int getContentResId() {
        return R.id.content_layout;
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        showSuccess();

        tvUserIntegral.setText(String.format(getString(R.string.user_integral), 200));
//        ImageUtils.loadImageCircle(ivUserHead,
//                "https://cdn2.jianshu.io/assets/default_avatar/5-33d2da32c552b8be9a0548c7a4576607.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/96/h/96");

        new BadgeView(getContext()).bindTarget(menuTv)
                .setBadgeText("1")
                .setBadgeTextColor(getResources().getColor(R.color.white))
                .setBadgeGravity(Gravity.TOP | Gravity.END)
                .setBadgeBackgroundColor(getResources().getColor(R.color.orange));

        ((LSettingItem) getViewById(R.id.me_item_info_order)).setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                ActivityToActivity.toActivity(ARouterConfig.home.ORDERACTIVITY, "position", 0);
            }
        });
        ((LSettingItem) getViewById(R.id.me_item_info_collection)).setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                ActivityToActivity.toActivity(ARouterConfig.Me.COLLECTIONACTIVITY);
            }
        });
        ((LSettingItem) getViewById(R.id.me_item_info_recommendation)).setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                ActivityToActivity.toActivity(ARouterConfig.Me.RECOMMENDATIONACTIVITY);
            }
        });
        ((LSettingItem) getViewById(R.id.me_item_info_money)).setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                ActivityToActivity.toActivity(ARouterConfig.Me.FUNDSACTIVITY);
            }
        });
        ((LSettingItem) getViewById(R.id.me_item_info_help)).setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                ActivityToActivity.toActivity(ARouterConfig.Me.HELPACTIVITY);
            }
        });
        ((LSettingItem) getViewById(R.id.me_item_info_popularize)).setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                ActivityToActivity.toActivity(ARouterConfig.Me.DIMENSIONALCODESACTIVITY);
            }
        });
        ((LSettingItem) getViewById(R.id.me_item_info_address)).setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                ActivityToActivity.toActivity(ARouterConfig.Me.ADDRESSLISTACTIVITY);
            }
        });
        ((LSettingItem) getViewById(R.id.me_item_info_redenvelope)).setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                ActivityToActivity.toActivity(ARouterConfig.home.REDACTIVITY);
            }
        });


        /*可售后商品*/
        ((LSettingItem) getViewById(R.id.me_item_info_aftersales)).setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                ActivityToActivity.toActivity(ARouterConfig.Me.AFTERSALESACTIVITY);
            }
        });
        /*售后商品list*/
        ((LSettingItem) getViewById(R.id.me_item_info_aftersales_list)).setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                ActivityToActivity.toActivity(ARouterConfig.Me.AFTERSALESLISTACTIVITY);
            }
        });

        setUser();
    }

    @Override
    public void onResume() {
        super.onResume();
        setUser();
    }

    boolean isLogin;

    private void setUser() {
        UserInfo data = (UserInfo) SharePreferenceUtil.getUser(UserInfo.class);
        if (null != data) {
            KLog.i("设置用户信息");
            UserInfo.UserBean avatar = data.getUser();
            ImageUtils.loadImageCircle(ivUserHead, avatar.getAvatar().getThumb());
            isLogin = true;
            tvLogin.setText(avatar.getAlias());
        } else {
            isLogin = false;
            tvLogin.setText("登录/注册");
            ImageUtils.loadImageCircle(ivUserHead,
                    "https://cdn2.jianshu.io/assets/default_avatar/5-33d2da32c552b8be9a0548c7a4576607.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/96/h/96");
        }
    }

    @Override
    protected void dataObserver() {
        super.dataObserver();
        LiveBus.getDefault()
                .subscribe("EVENT_KEY_WORK_MORE", Result.class)
                .observeForever(new Observer<Result>() {
                    @Override
                    public void onChanged(@Nullable Result response) {
                        KLog.i(response.getDataX().getUsername());
                    }
                });
    }

    @Override
    protected Object getStateEventKey() {
        return "EVENT_KEY_WORK_MORE";
    }

    @OnClick({R.id.iv_setting, R.id.iv_message, R.id.iv_user_head, R.id.tv_login, R.id.tv_user_integral, R.id.tv_integral_record,
            R.id.me_item_info_order, R.id.menu_tv1, R.id.menu_iv2, R.id.menu_iv3, R.id.menu_iv4,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                ActivityToActivity.toActivity(ARouterConfig.Me.SETTINGACTIVITY);
                break;
            case R.id.iv_message:
                ActivityToActivity.toActivity(ARouterConfig.Me.MESSAGEACTIVITY);
                break;
            case R.id.iv_user_head:
                ActivityToActivity.toActivity(ARouterConfig.Me.PERSONINFOACTIVITY);
                break;
            case R.id.tv_login:
                ActivityToActivity.toActivity(!isLogin ? ARouterConfig.LOGINACTIVITY : ARouterConfig.Me.PERSONINFOACTIVITY);
                break;
            case R.id.tv_user_integral:
                ActivityToActivity.toActivity(ARouterConfig.Me.INTEGRALRECORDACTIVITY, "postion", 0);
                break;
            case R.id.tv_integral_record:
                ActivityToActivity.toActivity(ARouterConfig.Me.INTEGRALRECORDACTIVITY, "postion", 1);
                break;
            case R.id.menu_tv1:
                ActivityToActivity.toActivity(ARouterConfig.home.ORDERACTIVITY, "postion", 1);
                break;
            case R.id.menu_iv2:
                ActivityToActivity.toActivity(ARouterConfig.home.ORDERACTIVITY, "position", 2);
                break;
            case R.id.menu_iv3:
                ActivityToActivity.toActivity(ARouterConfig.home.ORDERACTIVITY, "position", 3);
                break;
            case R.id.menu_iv4:
                ActivityToActivity.toActivity(ARouterConfig.home.ORDERACTIVITY, "position", 4);
                break;
        }
    }

}