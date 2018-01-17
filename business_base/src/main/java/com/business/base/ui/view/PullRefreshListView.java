package com.business.base.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by hua on 2017/11/13.
 */

public abstract class PullRefreshListView extends ViewGroup {

    public PullRefreshListView(Context context) {
        super(context);
    }

    public PullRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullRefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
