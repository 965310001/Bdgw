package com.bdgw.cc.ui.shopping;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.Constants;
import com.bdgw.cc.ui.shopping.bean.GoodsInfo;
import com.bdgw.cc.ui.shopping.bean.VendorInfo;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.event.LiveBus;
import me.goldze.common.base.mvvm.base.BaseFragment;
import me.goldze.common.base.mvvm.stateview.ErrorState;
import me.goldze.common.utils.ToastUtils;

/**
 * 购物车
 */
//@Route(path = ARouterConfig.SHOPPINGFRAGMENT)
public class ShoppingFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.btn_buy)
    TextView btnBuy;
    @BindView(R.id.iv_check_all)
    ImageView ivCheckAll;

    private List<VendorInfo> mData = new ArrayList<>();
    private ShoppingCartAdapter adapter;

    private boolean isEdit;//是否属于编辑状态

    public static ShoppingFragment newInstance() {
        return new ShoppingFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_shopping;
    }

    @Override
    protected int getContentResId() {
        return R.id.content_layout;
    }

    @Override
    public void initView(Bundle state) {
        loadManager.showSuccess();

        rlTitleBar.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.VISIBLE);
        tvRight.setVisibility(View.VISIBLE);

        tvTitle.setText("购物车");
        tvRight.setText("编辑");

        adapter = new ShoppingCartAdapter(getContext(), mData);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);

        dataObserver();

        cartData();
    }

    private void dataObserver() {
        LiveBus.getDefault().subscribe(Constants.Shopping.EVENT_SHOPPING_CART_CHANGED,
                Constants.Shopping.EVENT_SHOPPING_CART_CHANGED, String.class)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        displayResult();
                        if (s.equals(Constants.Shopping.EVENT_SHOPPING_CART_CHANGED)) {
                            //购物车有变化

                            ivCheckAll.setSelected(ShoppingCartUtils.isAllVendorChecked(mData));
//                            ivCheckAll.setSelected(ShoppingCartUtils.isAllVendorChecked(ShoppingCartUtils.getLocalData()));
                            adapter.notifyDataSetChanged();

                        } else if (s.equals(Constants.Shopping.EVENT_SHOPPING_CART_REFRESH)) {
                            //重新获取购物车数据
                            cartData();
                        }
                    }
                });
    }

    @OnClick({R.id.iv_check_all, R.id.tv_right, R.id.btn_buy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_check_all:
                boolean selected = view.isSelected();
                view.setSelected(!selected);
                ShoppingCartUtils.checkAll(mData, !selected);
                adapter.notifyDataSetChanged();
                displayResult();
                break;
            case R.id.tv_right:
                onRightChange();

                break;
            case R.id.btn_buy:
                List<GoodsInfo> checkedGoods = ShoppingCartUtils.getAllCheckedGoods(mData);
                if (checkedGoods.size() > 0) {
                    if (isEdit) {
                        //删除
                        ShoppingCartUtils.delete(checkedGoods);
                    } else {
                        ToastUtils.showLong("去结算");
                    }
                }
                break;
        }
    }

    /**
     * 获取数据库
     */
    private void cartData() {
        List<VendorInfo> data = ShoppingCartUtils.getLocalData();
        if (data.size() > 0) {
            mData.clear();
            mData.addAll(data);
            adapter.notifyDataSetChanged();
            displayResult();
        }
        setCartNumber();
    }


    /**
     * 设置购物车数量
     */
    private void setCartNumber() {
        int count = ShoppingCartUtils.getCartCount();
        if (count < 1) {
            tvRight.setVisibility(View.GONE);
            loadManager.showStateView(ErrorState.class);
//            tvCount.setVisibility(View.GONE);
        } else {
            loadManager.showSuccess();
            tvRight.setVisibility(View.VISIBLE);
//            tvCount.setVisibility(View.VISIBLE);
//            tvCount.setText(String.valueOf(count));
        }
        KLog.i("购物车数量" + count);
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        // TODO: 2019/1/25 去购买商品
    }

    private void displayResult() {
        btnBuy.setSelected(ShoppingCartUtils.isCheckedLeastOne(mData));
        KLog.i(" displayResult" + String.valueOf(ShoppingCartUtils.getCartCountPrice(mData)));
        tvTotal.setText(String.format("%s%s", getString(R.string.market_total),
                String.valueOf(ShoppingCartUtils.getCartCountPrice(mData))));
    }

    private void onRightChange() {
        isEdit = !isEdit;
        tvTotal.setVisibility(isEdit ? View.GONE : View.VISIBLE);
        tvRight.setText(isEdit ? "完成" : "编辑");
        btnBuy.setText(isEdit ? "删除" : "下一步");
        btnBuy.setBackgroundResource(isEdit ? R.drawable.market_shopping_cart_next_btn_red : R.drawable.market_shopping_cart_next_btn);
        adapter.setEdit(isEdit);
    }
}