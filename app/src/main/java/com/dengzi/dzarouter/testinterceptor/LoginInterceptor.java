package com.dengzi.dzarouter.testinterceptor;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.dengzi.dzarouter.MainLooper;
import com.dengzi.dzarouter.util.ExtraUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * 登录拦截器
 */
@Interceptor(priority = 2)
public class LoginInterceptor implements IInterceptor {
    Context mContext;
    Postcard mPostcard;
    InterceptorCallback mCallback;

    @Override
    public void init(Context context) {
        mContext = context;
        EventBus.getDefault().register(LoginInterceptor.this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 50, sticky = true)
    public void onLoginSuccess(String loginType) {
        mCallback.onContinue(mPostcard);
    }

    @Override
    public void process(final Postcard postcard, final InterceptorCallback callback) {
        Log.e("testService", "登录拦截器");
        int extra = postcard.getExtra();
        if (extra != Integer.MIN_VALUE) {
            int type = ExtraUtil.getExtra(extra, 0);
            if (type == 1) {
                mPostcard = postcard;
                mCallback = callback;
                MainLooper.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ARouter.getInstance()
                                .build("/test/login")
                                .navigation();
                    }
                });
            } else {
                callback.onContinue(postcard);
            }
        } else {
            callback.onContinue(postcard);
        }
    }

}
