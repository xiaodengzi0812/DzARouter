package com.business.base.base.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.business.base.util.ActivityManager;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutId());
        ActivityManager.getInstance().attach(this);
    }

    protected abstract int getContentLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().detach(this);
    }
}
