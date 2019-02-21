package com.bdgw.cc.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.ApiRepo;
import com.bdgw.cc.ui.classify.bean.ReviewListInfo;
import com.bdgw.cc.ui.home.bean.GoodsComment;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.goldze.common.base.mvvm.base.BaseFragment;
import me.goldze.common.http.rx.RxSubscriber;
import me.goldze.common.utils.ToastUtils;

/**
 * 商品详情 -商品评价
 */
public class GoodsCommentFragment extends BaseFragment {
    @BindView(R.id.tv_comment_count)
    TextView tvCommentCount;

    @BindView(R.id.tv_praise_rate)
    TextView tvPraiseRate;

    @BindView(R.id.tv_empty_comment)
    TextView tvEmptyComment;

    @BindView(R.id.recycle_view)
    RecyclerView recycleView;

    @BindView(R.id.iv_right)
    ImageView ivRight;

    private final List<GoodsComment> commentList = new ArrayList<>();
    private GoodsCommentAdapter adapter;

    public GoodsCommentFragment() {
    }

    public static GoodsCommentFragment newInstance(String id) {
        GoodsCommentFragment fragment = new GoodsCommentFragment();
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_goods_comment;
    }

    @Override
    protected int getContentResId() {
        return R.id.content_layout;
    }

    @Override
    public void initView(Bundle state) {
        showSuccess();

        adapter = new GoodsCommentAdapter(getContext(), commentList);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);

        ivRight.setVisibility(View.GONE);
        tvEmptyComment.setVisibility(View.GONE);
        recycleView.setVisibility(View.VISIBLE);
        tvCommentCount.setText("用户点评(999)");
        tvPraiseRate.setText("好评率97.8%");

        /*commentList(ApiData.getGoodsCommentList());*/
        getReviewList();
    }

    private void getReviewList() {
//        commentList(ApiData.getGoodsCommentList());
        ApiRepo.getReviewList(getArguments().getString("id"), 1).subscribeWith(new RxSubscriber<ReviewListInfo>() {

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


    void commentList(List<GoodsComment> commentList) {
        this.commentList.addAll(commentList);
        adapter.notifyDataSetChanged();
    }
}
