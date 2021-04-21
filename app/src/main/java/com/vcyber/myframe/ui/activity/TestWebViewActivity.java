package com.vcyber.myframe.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.vcyber.myframe.R;
import com.vcyber.myframe.base.BasePresenter;
import com.vcyber.myframe.base.MvpActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Create by zjl on 2019/10/12
 * ---- ----
 */
public class TestWebViewActivity extends MvpActivity {

    @BindView(R.id.webView)
    WebView mWebView;

    @BindView(R.id.imv_back)
    ImageView imvBack;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test_webview;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);
        if (getIntent().getStringExtra("url") != null && getIntent().getStringExtra("url").length() > 0) {
            mWebView.loadUrl(getIntent().getStringExtra("url"));
        } else {
            mWebView.loadUrl("http://vcyber.DSmyNAS.COM:8083/webchat/toqueue.jsp?workgroup=ar1000@workgroup.10.100.168.93&customerID=12121&customerName=aven");

        }
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.e("url1========", url);
                Log.e("url2========", "http://vcyber.DSmyNAS.COM:8083/webchat/toqueue.jsp?workgroup=ar1000@workgroup.10.100.168.93&customerID=12121&customerName=aven");
            }
        });
    }

    @OnClick(R.id.imv_back)
    public void setImvBack() {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (null != mWebView && mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
