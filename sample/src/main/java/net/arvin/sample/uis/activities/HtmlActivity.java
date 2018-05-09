package net.arvin.sample.uis.activities;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.orhanobut.logger.Logger;

import net.arvin.afbaselibrary.uis.activities.BaseHeaderActivity;
import net.arvin.sample.R;

import butterknife.BindView;

/**
 * Created by arvinljw on 18/3/5 13:21
 * Function：
 * Desc：
 */
public class HtmlActivity extends BaseHeaderActivity {

    public static final String URL = "url";
    public static final String TITLE = "title";
    protected String url;
    protected String title;


    @BindView(R.id.web_html)
    WebView mWebView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    public int getContentView() {
        return R.layout.activity_html;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        url = getIntent().getStringExtra(URL);
        title = getIntent().getStringExtra(TITLE);
        super.init(savedInstanceState);
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        initWebView();
    }

    @Override
    public String getTitleText() {
        return title;
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        final WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setDomStorageEnabled(true);
        settings.setGeolocationEnabled(true);

        mWebView.setWebViewClient(new CustomWebViewClient());
        mWebView.setWebChromeClient(new CustomChromeClient());
        mWebView.loadUrl(url);
    }

    public class CustomWebViewClient extends WebViewClient {

        CustomWebViewClient() {
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Logger.i("shouldOverrideUrlLoading URL" + url);
            mWebView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (tvTitle != null && TextUtils.isEmpty(title)) {
                title = view.getTitle();
                tvTitle.setText(view.getTitle());
            }
        }

        /**
         * 若访问https的网页需要重写下边两个方法
         */
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }
    }

    private class CustomChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (progressBar == null) {
                return;
            }
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(newProgress);
            if (newProgress == 100) {
                progressBar.setVisibility(View.GONE);
            }
        }
    }
}
