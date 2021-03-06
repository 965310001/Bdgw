package com.bdgw.cc.ui.home;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.ApiRepo;
import com.bdgw.cc.ui.UserInfo;
import com.bdgw.cc.ui.classify.bean.ReviewListInfo;
import com.bdgw.cc.ui.home.bean.GoodsComment;
import com.bdgw.cc.ui.shopping.bean.GoodsInfo;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.socks.library.KLog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.adapter.BannerImgAdapter;
import me.goldze.common.base.mvvm.base.BaseFragment;
import me.goldze.common.http.rx.RxSubscriber;
import me.goldze.common.utils.ToastUtils;
import me.goldze.common.widget.CountClickView;
import me.goldze.common.widget.SlideLayout;

/**
 * 商品详情 主Fragment 滑动可查看图片详情 规格等
 */
public class GoodsInfoMainFragment extends BaseFragment implements SlideLayout.OnSlideDetailsListener {

    String id;

    @BindView(R.id.vp_item_goods_img)
    ConvenientBanner vpItemGoodsImg;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_goods_price)
    TextView tvGoodsPrice;
    @BindView(R.id.tv_old_price)
    TextView tvOldPrice;
    @BindView(R.id.ccv_click)
    CountClickView ccvClick;
    @BindView(R.id.tv_comment_count)
    TextView tvCommentCount;
    @BindView(R.id.tv_praise_rate)
    TextView tvPraiseRate;
    @BindView(R.id.tv_empty_comment)
    TextView tvEmptyComment;
    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;//评论
    @BindView(R.id.sv_switch)
    SlideLayout svSwitch;

    /**
     * 当前商品详情数据页的索引分别是图文详情、规格参数
     */
    GoodsInfo goodsInfo;
    private ShoppingDetailsActivity shoppingDetailsActivity;
    private GoodsCommentAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        shoppingDetailsActivity = (ShoppingDetailsActivity) context;
    }

    public GoodsInfoMainFragment() {
    }

    public static GoodsInfoMainFragment newInstance(String id, GoodsInfo goodsInfo) {
        GoodsInfoMainFragment fragment = new GoodsInfoMainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putSerializable("goodsInfo", goodsInfo);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_goods_info_main;
    }

    @Override
    protected int getContentResId() {
        return R.id.content_layout;
    }

    @Override
    public void initView(Bundle state) {
        showSuccess();

        ccvClick.setCurrCount(1);
        ccvClick.setMinCount(1);
        svSwitch.setOnSlideDetailsListener(this);

        id = getArguments().getString("id");
        goodsInfo = (GoodsInfo) getArguments().getSerializable("goodsInfo");
        KLog.i(goodsInfo);

        //设置文字中间一条横线
        tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        setGoodsInfo();
        setGoodsHeadImg();
        /*commentList(ApiData.getGoodsCommentList());*/
        getReviewList();
    }

    /*商品评价信息*/
    private void getReviewList() {
//        commentList(ApiData.getGoodsCommentList());
        ApiRepo.getReviewList(String.valueOf(goodsInfo.getGoodsId()), 1).subscribeWith(new RxSubscriber<ReviewListInfo>() {

            @Override
            public void onSuccess(ReviewListInfo response) {
//                KLog.i(response.getErrorMsg() + response.getError_desc());
////                if (!response.isSuccess()) {
////                    ToastUtils.showLong(response.getErrorMsg());
////                } else {
////                }
                commentList(response.getData());
            }

            @Override
            public void onFailure(String msg) {
                KLog.i(msg);
                ToastUtils.showLong(msg);
            }

            @Override
            public void onError(Throwable t) {
                KLog.i(t.getMessage());
                ToastUtils.showLong("请稍后再试");
            }
        });

    }


    @Override
    public void onStateChanged(SlideLayout.Status status) {
        if (shoppingDetailsActivity != null) {
            shoppingDetailsActivity.setViewContent(status == SlideLayout.Status.OPEN);
            KLog.i("上拉查看图文详情");

            getChildFragmentManager().beginTransaction().replace(R.id.fl_fragment,
                    GoodsInfoDetailMainFragment.newInstance(goodsInfo)).commitAllowingStateLoss();
        }
    }


    @OnClick({R.id.ll_pull_up, R.id.ll_comment, R.id.iv_like})
    public void onClick(View v) {
        if (v.getId() == R.id.ll_pull_up) {//上拉查看图文详情
            KLog.i("上拉查看图文详情");
            /*getChildFragmentManager().beginTransaction().replace(R.id.fl_fragment, fragment).commitAllowingStateLoss();*/
            svSwitch.smoothOpen(true);
        } else if (v.getId() == R.id.ll_comment) {
            //查看评论
            shoppingDetailsActivity.setCurrentFragment(2);
        } else if (v.getId() == R.id.iv_like) {
            /*添加收藏*/
            getUnlike();
        }
    }

    private void getUnlike() {
        ApiRepo.getUnlike(goodsInfo.getGoodsId()).subscribeWith(new RxSubscriber<UserInfo>() {
            @Override
            public void onSuccess(UserInfo response) {
                KLog.i(response.getErrorMsg() + response.getError_desc());
                if (!response.isSuccess()) {
                    ToastUtils.showLong(response.getErrorMsg());
                } else {
                    /*修改成功*/
                    /*ToastUtils.showLong("收藏成功");*/
                }
            }

            @Override
            public void onFailure(String msg) {
                KLog.i(msg);
            }
        });

    }

    /**
     * 设置商品头图 轮播
     */
    private void setGoodsHeadImg() {
        if (goodsInfo != null) {
            vpItemGoodsImg.setPages(new BannerImgAdapter() {
                @Override
                public ImageView getImageView() {
                    ImageView imageView = new ImageView(getContext());
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
                    return imageView;
                }
            }, goodsInfo.getGoodsHeadImg())
                    .setPageIndicator(new int[]{R.drawable.market_banner_index_white, R.drawable.market_shape_round_red})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        }
    }

    /**
     * 设置商品信息
     */
    private void setGoodsInfo() {
        if (goodsInfo != null) {
            tvGoodsName.setText(goodsInfo.getGoodsName());
            tvGoodsPrice.setText(String.format("¥%s", goodsInfo.getGoodsPrice()));
            tvOldPrice.setText(String.format("¥%s", goodsInfo.getGoodsOldPrice()));
            tvCommentCount.setText(String.format("用户点评(%S)", goodsInfo.getCommentCount()));
            tvPraiseRate.setText(String.format("好评率%s", goodsInfo.getPraiseRate()));
        }
    }


    public void commentList(List<GoodsComment> commentList) {
        if (commentList.size() > 0) {
            tvEmptyComment.setVisibility(View.GONE);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new GoodsCommentAdapter(getContext(), commentList);
            recyclerView.setAdapter(adapter);
        } else {
            tvEmptyComment.setVisibility(View.VISIBLE);
            tvEmptyComment.setText("暂无精彩评论");
        }
    }

//    public void goodsInfo(GoodsInfo goodsInfo) {
//        this.goodsInfo = goodsInfo;
//        setGoodsHeadImg();
//        setGoodsInfo();
//    }

    /**
     * 当前商品数量
     *
     * @return
     */
    public int getGoodsCount() {
        return ccvClick.getCount();
    }
}
