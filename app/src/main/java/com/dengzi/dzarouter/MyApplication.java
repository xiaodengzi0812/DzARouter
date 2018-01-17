package com.dengzi.dzarouter;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.business.core.glide.MyAppGlideModule;


public class MyApplication extends Application {
    private MyAppGlideModule appGlideModule;

    @Override
    public void onCreate() {
        super.onCreate();
        initArouter();
        initGlideModule();
    }

    private void initArouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();// 打印日志
            ARouter.openDebug();// 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
    }

    private void initGlideModule() {
        appGlideModule = new MyAppGlideModule();
    }
}
