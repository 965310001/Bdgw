package com.bdgw.cc.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.bean.BannerItemView;
import com.bdgw.cc.ui.classify.bean.ClassificationInfo;
import com.bdgw.cc.ui.classify.bean.DistributionInfo;
import com.bdgw.cc.ui.classify.holder.ClassificationItemHolder;
import com.bdgw.cc.ui.classify.holder.ClassificationRightAdapter;
import com.bdgw.cc.ui.classify.holder.ClassificationRightItemHolder;
import com.bdgw.cc.ui.classify.holder.DistributionInfoItemHolder;
import com.bdgw.cc.ui.classify.holder.ProductsItemHolder;
import com.bdgw.cc.ui.home.TypeItemView;
import com.bdgw.cc.ui.home.bean.Banner;
import com.bdgw.cc.ui.home.bean.OrderInfo;
import com.bdgw.cc.ui.home.bean.RedItemInfo;
import com.bdgw.cc.ui.home.bean.SearchInfo;
import com.bdgw.cc.ui.home.bean.TypeInfo;
import com.bdgw.cc.ui.home.holder.CatagoryInfo;
import com.bdgw.cc.ui.home.holder.CategoryItemView;
import com.bdgw.cc.ui.home.holder.OrderItemView;
import com.bdgw.cc.ui.home.holder.RedItemView;
import com.bdgw.cc.ui.home.holder.SearchItemView;
import com.bdgw.cc.ui.home.holder.VendorItemView;
import com.bdgw.cc.ui.me.bean.AddressInfo;
import com.bdgw.cc.ui.me.bean.AfterSalesInfo;
import com.bdgw.cc.ui.me.bean.DefiniteInfo;
import com.bdgw.cc.ui.me.bean.ExtractInfo;
import com.bdgw.cc.ui.me.vm.AddressInfoItemHolder;
import com.bdgw.cc.ui.me.vm.AfterSalesInfoItemHolder;
import com.bdgw.cc.ui.me.vm.DefiniteInfoItemHolder;
import com.bdgw.cc.ui.me.vm.ExtractInfoInfoItemHolder;
import com.bdgw.cc.ui.shopping.bean.DeliverInfo;
import com.bdgw.cc.ui.shopping.bean.GoodsInfo;
import com.bdgw.cc.ui.shopping.bean.OrderDetailsInfo;
import com.bdgw.cc.ui.shopping.vm.DeliverInfoItemHolder;
import com.bdgw.cc.ui.shopping.vm.OrderAddressItemHolder;
import com.bdgw.cc.ui.shopping.vm.OrderDetailsInfoFootItemHolder;
import com.bdgw.cc.ui.shopping.vm.OrderDetailsInfoItemHolder;
import com.trecyclerview.adapter.DelegateAdapter;
import com.trecyclerview.footview.FootViewHolder;
import com.trecyclerview.headview.HeaderViewHolder;
import com.trecyclerview.holder.AbsHolder;
import com.trecyclerview.holder.AbsItemHolder;
import com.trecyclerview.pojo.FootVo;
import com.trecyclerview.pojo.HeaderVo;
import com.trecyclerview.progressindicator.ProgressStyle;

import me.goldze.common.base.event.LiveBus;

/**
 * @author GuoFeng
 * @date : 2019/1/20 15:06
 * @description: Adapter适配器
 */
public class AdapterPool {
    private static AdapterPool adapterPool;

    public static AdapterPool newInstance() {
        if (adapterPool == null) {
            synchronized (AdapterPool.class) {
                if (adapterPool == null) {
                    adapterPool = new AdapterPool();
                }
            }
        }
        return adapterPool;
    }

    /*-------------------------------------------------------------------首页---------------------------------------------------*/
    public DelegateAdapter.Builder getHomeAdapter(Context context) {
        return getNoFootAdapter(new DelegateAdapter.Builder<>()
                        .bind(Banner.class, new BannerItemView(context))
                        .bind(CatagoryInfo.class, new CategoryItemView(context))
                        .bind(TypeInfo.class, new TypeItemView(context))
                        .bind(GoodsInfo.class, new VendorItemView(context)),
                context, ProgressStyle.Pacman);
//                .bind(TypeVo.class, new TypeItemView(context))
//                .bind(BookList.class, new BookItemHolder(context))
//                .bind(CourseInfoVo.class, new CourseItemHolder(context))
//                .bind(LiveRecommendVo.class, new HomeLiveItemView(context))
//                .bind(MatreialSubjectVo.class, new HomeMaterialItemView(context)),
    }

    /*订单*/
    public DelegateAdapter.Builder getOrderAdapter(Context context) {
        return getNoFootAdapter(new DelegateAdapter.Builder().bind(OrderInfo.OrdersBean.class,
                new OrderItemView(context)),
                context, ProgressStyle.BallPulse);
    }

    /*红包*/
    public DelegateAdapter.Builder getRedAdapter(Context context) {
        return getAdapter(new DelegateAdapter.Builder().bind(RedItemInfo.CashgiftsBean.class,
                new RedItemView(context)),
                context, ProgressStyle.Pacman);
    }

    /*搜索list*/
    public DelegateAdapter.Builder getSearchListAdapter(Context context) {
        return getNoFootAdapter(new DelegateAdapter.Builder().bind(SearchInfo.DataBean.DatasBean.class,
                new SearchItemView(context)),
                context, ProgressStyle.Pacman);
    }

    /*public DelegateAdapter.Builder getAdapter(Context context, Class clazz,
                                              AbsItemHolder absItemHolder, int style) {
        return getNoFootAdapter(new DelegateAdapter.Builder().bind(clazz, absItemHolder), context, style);
    }*/

    /*------------------------------------------------------------------分类---------------------------------------------------*/
    public DelegateAdapter.Builder getLeftAdapter(Context context) {
        return getAdapter(new DelegateAdapter.Builder<>()
                        .bind(ClassificationInfo.DataBean.class,
                                new ClassificationItemHolder(context)),
                context, ProgressStyle.BallRotate);
    }

    /*二级listview*/
//    public DelegateAdapter.Builder getRightAdapter(Context context) {
//        return getAdapter(new DelegateAdapter.Builder<>()
//                        .bind(ClassificationInfo.DataBean.ChildrenBean.class,
//                                new ClassificationRightItemHolder(context)),
//                context, ProgressStyle.BallRotate);
//    }

    /*三级listview*/
    public DelegateAdapter.Builder getRightAdapter(Context context) {
        return getAdapter(new DelegateAdapter.Builder<>()
                        .bind(ClassificationInfo.DataBean.ChildrenBeanX.class, new ClassificationRightItemHolder(context)),
                context, ProgressStyle.BallRotate);
    }

    public DelegateAdapter.Builder getRightAdapter1(Context context) {
        return getAdapter(new DelegateAdapter.Builder<>()
                        .bind(ClassificationInfo.DataBean.ChildrenBeanX.ChildrenBean.class,
                                new ClassificationRightAdapter(context)),
                context, ProgressStyle.BallRotate);
    }

    /*配送方式*/
    public DelegateAdapter.Builder getDistributionAdapter(Context context) {
        return getNoFootAdapter(new DelegateAdapter.Builder<>()
                        .bind(DistributionInfo.VendorsBean.class,
                                new DistributionInfoItemHolder(context)),
                context, ProgressStyle.Pacman);
    }

    /*分类列表里面的list*/
    public DelegateAdapter.Builder getProductsAdapter(Context context) {
        return getAdapter(new DelegateAdapter.Builder<>()
                        .bind(GoodsInfo.class, new ProductsItemHolder(context)),
                context, ProgressStyle.Pacman);
    }

    /*-------------------------------------------------------------------购物车-------------------------------------------------*/

    /*物流*/
    public DelegateAdapter.Builder getDeliverInfoAdapter(Context context) {
        return getNoFootAdapter(new DelegateAdapter.Builder<>()
                        .bind(DeliverInfo.TracesBean.class,
                                new DeliverInfoItemHolder(context)),
                context, ProgressStyle.Pacman);
    }

    /*订单详情*/
    public DelegateAdapter.Builder getOrderDetailsAdapter(Context context) {
        class ViewHolder extends AbsHolder {
            TextView tvName;
            TextView tvNum;
            TextView tvPrice;
            TextView tvGetAll;

            private ViewHolder(View view) {
                super(view);
                tvName = getViewById(R.id.tv_name);
                tvNum = getViewById(R.id.tv_num);
                tvPrice = getViewById(R.id.tv_price);
                tvGetAll = getViewById(R.id.tv_get_all);
            }
        }
        return new DelegateAdapter.Builder<>()
                .bind(AddressInfo.class, new OrderAddressItemHolder(context))
                .bind(GoodsInfo.class, new OrderDetailsInfoItemHolder(context))
                .bind(Boolean.class, new AbsItemHolder<Boolean, ViewHolder>(context) {
                    @Override
                    protected void onBindViewHolder(@NonNull final ViewHolder holder, @NonNull final Boolean b) {
                        holder.tvName.setVisibility(View.GONE);
                        holder.tvPrice.setVisibility(View.GONE);
                        holder.tvNum.setVisibility(View.GONE);
                        holder.tvGetAll.setVisibility(View.VISIBLE);
                        holder.tvGetAll.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                holder.tvGetAll.setText(b ? "收收" : "加载全部");
                                LiveBus.getDefault().postEvent("OrderDetailsActivity", "OrderDetailsActivity", !b);
                            }
                        });
                    }

                    @Override
                    public int getLayoutResId() {
                        return R.layout.item_order_details;
                    }

                    @Override
                    public ViewHolder createViewHolder(View view) {
                        return new ViewHolder(view);
                    }

                })
                .bind(OrderDetailsInfo.OrderInfoBean.class, new OrderDetailsInfoFootItemHolder(context));
    }

    /*-------------------------------------------------------------------我的---------------------------------------------------*/
    /*地址列表*/
    public DelegateAdapter.Builder getAddressAdapter(Context context) {
        return getNoFootAdapter(new DelegateAdapter.Builder<>()
                        .bind(AddressInfo.class,
                                new AddressInfoItemHolder(context)),
                context, ProgressStyle.Pacman);
    }

    /*提现记录*/
    public DelegateAdapter.Builder getExtractListAdapter(Context context) {
        return getNoFootAdapter(new DelegateAdapter.Builder<>()
                        .bind(ExtractInfo.DataBean.class, new ExtractInfoInfoItemHolder(context)),
                context, ProgressStyle.Pacman);
    }

    /*资金明细*/
    public DelegateAdapter.Builder getDefiniteAdapter(Context context) {
        return getAdapter(new DelegateAdapter.Builder<>()
                        .bind(DefiniteInfo.BalancesBean.class,
                                new DefiniteInfoItemHolder(context)),
                context, ProgressStyle.Pacman);
    }

    /*售后列表*/
    public DelegateAdapter.Builder getAfterSalesListAdapter(Context context) {
        return getAdapter(new DelegateAdapter.Builder<>()
                        .bind(AfterSalesInfo.class, new AfterSalesInfoItemHolder(context)),
                context, ProgressStyle.Pacman);
    }

    /*可售后商品*/
    public DelegateAdapter.Builder getAfterSalesAdapter(Context context, boolean is) {
        return getAdapter(new DelegateAdapter.Builder<>()
                        .bind(AfterSalesInfo.class, new AfterSalesInfoItemHolder(context, is)),
                context, ProgressStyle.Pacman);
    }
    /*-------------------------------------------------------------------end---------------------------------------------------*/

    private DelegateAdapter.Builder getAdapter(DelegateAdapter.Builder builder,
                                               Context context,
                                               int mProgressStyle) {
        return builder.bind(HeaderVo.class, new HeaderViewHolder(context, mProgressStyle)).
                bind(FootVo.class, new FootViewHolder(context, mProgressStyle));
    }

    private DelegateAdapter.Builder getNoFootAdapter(DelegateAdapter.Builder builder,
                                                     Context context,
                                                     int mProgressStyle) {
        return builder.bind(HeaderVo.class,
                new HeaderViewHolder(context, mProgressStyle));
    }


}
