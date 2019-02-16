package com.bdgw.cc.ui.home;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bdgw.cc.R;

import butterknife.BindView;
import me.goldze.common.base.mvvm.base.BaseFragment;

/**
 * 商品详情 - 图文详情 Fragment
 */
public class GoodsInfoWebFragment extends BaseFragment {
    @BindView(R.id.hwv_detail)
    public WebView webView;
    private WebSettings webSettings;

    public GoodsInfoWebFragment() {
    }

    public static GoodsInfoWebFragment newInstance() {
        return new GoodsInfoWebFragment();
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_goods_info_web;
    }

    @Override
    protected int getContentResId() {
        return R.id.content_layout;
    }

    @Override
    public void initView(Bundle state) {
        showSuccess();
        initWebView();
    }

    private void initWebView() {
        String url = "http://m.okhqb.com/item/description/1000334264.html?fromApp=true";
        webView.setFocusable(false);
        webView.loadUrl(url);
        webSettings = webView.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setBlockNetworkImage(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.setWebViewClient(new GoodsDetailWebViewClient());
    }

    private class GoodsDetailWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            webSettings.setBlockNetworkImage(false);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return true;
        }
    }


}
