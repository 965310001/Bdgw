package me.goldze.common.activity;

import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.socks.library.KLog;

import butterknife.BindView;
import me.goldze.common.R;
import me.goldze.common.R2;
import me.goldze.common.base.mvvm.base.BaseActivity;
import me.goldze.common.base.mvvm.stateview.LoadingState;
import me.goldze.common.constants.ARouterConfig;

@Route(path = ARouterConfig.WEBVIEWACTIVITY)
public class WebViewActivity extends BaseActivity implements View.OnClickListener {

    @Autowired
    String URL;

    @BindView(R2.id.webview)
    WebView webView;

    @BindView(R2.id.iv_back)
    ImageView ivBack;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.rl_title_bar)
    RelativeLayout rlTitleBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        ARouter.getInstance().inject(this);

     /*   String url = getIntent().getStringExtra("URL");
        KLog.i(url + " " + URL);*/

        loadManager.showStateView(LoadingState.class);

        rlTitleBar.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(this);

        initWebView();

        webView.loadUrl(URL);

//        webView.setWebChromeClient(new MyWebChromeClient());
        webView.setWebViewClient(new MyWebClient());
    }

    private void initWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSaveFormData(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);

        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        //5.0以上支持混合https和http
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            KLog.i(newProgress + "'");
            if (newProgress == 100) {
                loadManager.showSuccess();
            }
        }
    }

    private class MyWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            KLog.i("shouldOverrideUrlLoading");
            webView.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            KLog.i("onReceivedSslError");
            handler.proceed(); // 接受所有网站的证书
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            tvTitle.setText(view.getTitle());
            loadManager.showSuccess();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showFinish();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    private void showFinish() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }


    /*public static void actionStart(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("URL", url);
        context.startActivity(intent);
    }*/
}
