package com.bdgw.cc.ui.classify;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bdgw.cc.R;
import com.bdgw.cc.ui.Constants;

import java.util.ArrayList;
import java.util.List;

import me.goldze.common.activity.HorizontalTabActivity;
import me.goldze.common.base.mvvm.base.BaseFragment;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ActivityToActivity;
import me.goldze.common.utils.ImageUtils;

/**
 * 商品分类list
 */
@Route(path = ARouterConfig.classify.PRODUCTSACTIVITY)
public class ProductsActivity extends HorizontalTabActivity {

    @Autowired
    String id;

    @Override
    protected String[] getTabTitles() {
        return Constants.Listview.PRODUCTS_TITLE;
    }

    @Override
    protected List<BaseFragment> getTabFragments() {
        List<BaseFragment> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(ProductsItemFragment.newInstance(id));
        }
        return list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        ARouter.getInstance().inject(this);

        findViewById(R.id.tv_title).setVisibility(View.GONE);
        findViewById(R.id.ed_search).setVisibility(View.VISIBLE);
        ImageView ivAddCart = findViewById(R.id.iv_search);
        ivAddCart.setVisibility(View.VISIBLE);

//        ivAddCart.setMinimumHeight(PxUtils.px2dp(Utils.getApplication(), getResources().getDimension(R.dimen.image_height_small)));
//        ivAddCart.setMinimumHeight(PxUtils.px2dp(Utils.getApplication(), getResources().getDimension(R.dimen.image_height_small)));
//        ivAddCart.setMaxWidth(PxUtils.px2dp(Utils.getApplication(), getResources().getDimension(R.dimen.image_height_small)));
//        ivAddCart.setMaxWidth(PxUtils.px2dp(Utils.getApplication(), getResources().getDimension(R.dimen.image_height_small)));

        ImageUtils.loadImage(ivAddCart, R.drawable.market_icon_shopping_cart);

//        ivAddCart.setImageResource(R.drawable.market_icon_shopping_cart);
//        ivAddCart.setBackground(R.drawable.market_icon_shopping_cart);

        ivAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityToActivity.toActivity(ARouterConfig.Shopping.SHOPPINGACTIVITY);
            }
        });
    }
}
