package com.business.base.base.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.business.base.R;
import com.business.base.ui.view.H5WebView;

import static com.business.base.base.common.Constant.H5_KEY_URL;

/**
 * Created by hua on 2017/11/13.
 */

public class H5Fragment extends BaseFragment {
    private H5WebView mWebView;

    @Override
    protected int getLayoutResId() {
        return R.layout.base__h5_fragment;
    }
    @Override
    protected void onInflated(View container, Bundle savedInstanceState) {
        mWebView = (H5WebView) container.findViewById(R.id.base__h5_fragment);
        initWebView(mWebView);

        Bundle bundle = getArguments();
        if (null !=  bundle) {
            loadUrl(bundle.getString(H5_KEY_URL));
        }
    }

    protected void loadUrl(String url) {
        if (TextUtils.isEmpty(url) || null == mWebView)
            return;
        mWebView.loadUrl(url);
    }

    protected void initWebView(WebView webView) {
        webView.setWebViewClient(new WebViewClient());
    }

}
