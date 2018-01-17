package com.business.base.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by hua on 2017/11/13.
 */

public class H5WebView extends WebView {

    public H5WebView(Context context) {
        this(context, null);
    }

    public H5WebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public H5WebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
