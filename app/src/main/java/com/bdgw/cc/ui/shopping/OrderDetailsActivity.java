package com.bdgw.cc.ui.shopping;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bdgw.cc.ui.ApiData;
import com.bdgw.cc.ui.ApiRepo;
import com.bdgw.cc.ui.adapter.AdapterPool;
import com.bdgw.cc.ui.shopping.bean.GoodsInfo;
import com.bdgw.cc.ui.shopping.bean.OrderDetailsInfo;
import com.socks.library.KLog;
import com.trecyclerview.adapter.DelegateAdapter;
import com.trecyclerview.listener.OnItemClickListener;

import java.util.List;

import me.goldze.common.base.event.LiveBus;
import me.goldze.common.base.mvvm.base.BaseListActivity;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.http.rx.RxSubscriber;

/**
 * 订单详情
 */
@Route(path = ARouterConfig.Shopping.ORDERDETAILSACTIVITY)
public class OrderDetailsActivity extends BaseListActivity implements OnItemClickListener {

    @Autowired
    int id;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        ARouter.getInstance().inject(this);
        showSuccess();

        setTitle("订单详情");
        showBack();

        KLog.i("商品详情" + id);

        LiveBus.getDefault()
                .subscribe("OrderDetailsActivity", "OrderDetailsActivity").observeForever(new Observer<Object>() {
            @Override
            public void onChanged(@Nullable Object object) {
                isAll = Boolean.parseBoolean(object.toString());
                initData();//false //收收

                KLog.i(isAll + " ");
            }
        });
    }

    private boolean isAll;

    @Override
    protected void initData() {
        super.initData();

        List<OrderDetailsInfo> datas = ApiData.getOrderDetailsInfos();
        newItems.add(datas.get(0).getAddressInfo());
        List<GoodsInfo> goodsInfos = datas.get(0).getGoodsInfos();
//        if (goodsInfos.size() > 3) {
//            newItems.addAll(goodsInfos.subList(0, 3));
////            newItems.add(true);
//        } else {
////
//        }
        newItems.addAll(goodsInfos);
        newItems.add(datas.get(0).getOrderInfo());
        oldItems.clear();
        oldItems.addAll(newItems);
        mRecyclerView.refreshComplete(oldItems, true);
        newItems.clear();
    }

    private void getData() {
        showLoadingState();
        ApiRepo.getOrderData(id).subscribeWith(new RxSubscriber<OrderDetailsInfo>() {
            @Override
            public void onSuccess(OrderDetailsInfo data) {
                showSuccess();
               /* if (null != data.getOrders() && data.getOrders().size() > 0) {
                    setData(data.getOrders());
                }*/
                KLog.i("订单详情");
            }

            @Override
            public void onFailure(String msg) {
                KLog.i(msg);
                showErrorState();
            }
        });

    }

    @Override
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getOrderDetailsAdapter(this)
                .setOnItemClickListener(this)
                .build();
    }

    @Override
    public void onItemClick(View view, int i, Object object) {

    }
}
