package com.bdgw.cc.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bdgw.cc.R;
import com.bdgw.cc.ui.ApiData;
import com.bdgw.cc.ui.shopping.ShoppingCartUtils;
import com.bdgw.cc.ui.shopping.bean.GoodsInfo;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.bean.HorizontalTabTitle;
import me.goldze.common.base.mvvm.base.BaseActivity;
import me.goldze.common.base.mvvm.base.BaseFragment;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ActivityToActivity;
import me.goldze.common.utils.ToastUtils;
import me.goldze.common.widget.NoScrollViewPager;
import me.goldze.common.widget.PagerSlidingTabStrip;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * 商品详情
 */
@Route(path = ARouterConfig.home.SHOPPINGDETAILSACTIVITY)
public class ShoppingDetailsActivity extends BaseActivity {

    @Autowired
    String id;

    @BindView(R.id.psts_tabs)
    public PagerSlidingTabStrip pstsTabs;

    @BindView(R.id.tv_title)
    public TextView tvTitle;

    @BindView(R.id.vp_content)
    public NoScrollViewPager vpContent;

    @BindView(R.id.tv_count)
    TextView tvCount;//购物车数量

    //TODO 测试使用列表造的伪数据表示不同商品做加入购物车操作
    private GoodsInfo goodsInfo;

    private GoodsInfoMainFragment goodsInfoMainFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopping_details;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);

        loadManager.showSuccess();
        KLog.i("商品" + id);

        getGoodsInfo();

        List<HorizontalTabTitle> title = new ArrayList<>();
        title.add(new HorizontalTabTitle("商品"));
        title.add(new HorizontalTabTitle("详情"));
        title.add(new HorizontalTabTitle("评价"));

        List<BaseFragment> fragmentList = new ArrayList<>();
        fragmentList.add(goodsInfoMainFragment = GoodsInfoMainFragment.newInstance(id, goodsInfo));
        fragmentList.add(new GoodsInfoDetailMainFragment());
        fragmentList.add(new GoodsCommentFragment());
        vpContent.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), title, fragmentList));
        vpContent.setOffscreenPageLimit(3);
        pstsTabs.setViewPager(vpContent);

//        setCartNumber();
    }

    private void getGoodsInfo() {
        List<GoodsInfo> arrayList = ApiData.getGoodsInfos();
        for (GoodsInfo info : arrayList) {
            if (Long.parseLong(info.getGoodsId()) == Long.parseLong(id)) {
                this.goodsInfo = info;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCartNumber();
    }

    /**
     * 设置内容
     */
    public void setViewContent(boolean scrollToBottom) {
        // true:图文详情  false:商品详情
        vpContent.setNoScroll(scrollToBottom);
        tvTitle.setVisibility(scrollToBottom ? VISIBLE : GONE);
        pstsTabs.setVisibility(scrollToBottom ? GONE : VISIBLE);
    }

    @OnClick({R.id.ll_back, R.id.rl_cart, R.id.tv_add_cart, R.id.tv_buy_now})
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.ll_back) {
            finish();
        } else if (i == R.id.rl_cart) {
            //去购物车
            ActivityToActivity.toActivity(ARouterConfig.Shopping.SHOPPINGACTIVITY);
        } else if (i == R.id.tv_add_cart) {
            //加入购物车
            if (goodsInfo != null) {
                goodsInfo.setNum(goodsInfoMainFragment.getGoodsCount());
                ShoppingCartUtils.addCartGoods(goodsInfo);
                setCartNumber();
//                EventBusUtils.sendEvent(new Event(EventAction.EVENT_SHOPPING_CART_REFRESH));
//                LiveBus.getDefault().postEvent(Constants.Shopping.EVENT_SHOPPING_CART_REFRESH,
//                        Constants.Shopping.EVENT_SHOPPING_CART_REFRESH, Constants.Shopping.EVENT_SHOPPING_CART_REFRESH);
            } else {
                ToastUtils.showLong("没有正经的商品信息~");
            }
        } else if (i == R.id.tv_buy_now) {
            //立即购买
            Map<String, GoodsInfo> map = new HashMap<>();
            map.put("goodsInfo", goodsInfo);
            ActivityToActivity.toActivity(ARouterConfig.classify.BUYACTIVITY, map);
        }
    }

    /**
     * 设置购物车数量
     */
    private void setCartNumber() {
        int count = ShoppingCartUtils.getCartCount();
        if (count < 1) {
            tvCount.setVisibility(View.GONE);
        } else {
            tvCount.setVisibility(View.VISIBLE);
            tvCount.setText(String.valueOf(count));
        }
    }

    /**
     * 切换Fragment
     *
     * @param position position
     */
    public void setCurrentFragment(int position) {
        vpContent.setCurrentItem(position);
    }

}