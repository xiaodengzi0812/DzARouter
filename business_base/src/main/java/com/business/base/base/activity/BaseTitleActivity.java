package com.business.base.base.activity;


import android.os.Bundle;
import android.view.View;

import com.business.base.R;
import com.business.base.ui.view.BaseTitleView;
import com.business.base.ui.view.listener.BaseTitleListener;

public abstract class BaseTitleActivity extends BaseActivity implements BaseTitleListener {

    protected BaseTitleView mTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleView = (BaseTitleView)findViewById(R.id.base__title_activity__title);
        initContentView(findViewById(R.id.base__title_activity__content));
        mTitleView.setBaseTitleListener(this);
    }

    @Override
    protected final int getContentLayoutId() {
        return R.layout.base__title_activity;
    }

    // #### BaseTitleListener ####
    @Override
    public void onLeftGroupViewClicked(View leftView) {
        finish();
    }
    @Override
    public void onRightGroupViewClicked(View rightView) {

    }
    @Override
    public void onCenterTitleViewClicked(View centerView) {

    }

    protected abstract void initContentView(View contentView);

}
