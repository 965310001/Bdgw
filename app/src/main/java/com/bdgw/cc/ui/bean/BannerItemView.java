package com.bdgw.cc.ui.bean;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.home.bean.Banner;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.trecyclerview.holder.AbsHolder;
import com.trecyclerview.holder.AbsItemHolder;

import me.goldze.common.utils.ActivityToActivity;
import me.goldze.common.utils.ImageUtils;

/**
 * @author GuoFeng
 * @date : 2019/1/20 15:09
 * @description:
 */
public class BannerItemView extends AbsItemHolder<Banner, BannerItemView.ViewHolder> {

    public BannerItemView(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.common_banner_view;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull BannerItemView.ViewHolder holder, @NonNull final Banner banner) {
        ImageUtils.loadBanner(holder.mBannerView, banner.getImags(), new
                com.bigkoo.convenientbanner.listener.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        ActivityToActivity.toWebView(banner.getUrl(position));
                    }
                });
    }

    @Override
    protected void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        RecyclerView.LayoutParams clp = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        if (clp instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) clp).setFullSpan(true);
        }
    }

    static class ViewHolder extends AbsHolder {

        final ConvenientBanner mBannerView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mBannerView = getViewById(R.id.convenientBanner);
            mBannerView.startTurning(2000);
        }

    }

}

