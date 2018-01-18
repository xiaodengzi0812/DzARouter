package com.dengzi.dzarouter.testinterceptor;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.dengzi.dzarouter.util.CheckNetUtil;

/**
 * 判断网络拦截器
 */
@Interceptor(priority = 1)
public class NetInterceptor implements IInterceptor {
    Context mContext;

    @Override
    public void init(Context context) {
        mContext = context;
    }

    @Override
    public void process(final Postcard postcard, final InterceptorCallback callback) {
        Log.e("testService", "网络拦截器");
        if (CheckNetUtil.isNetworkAvailable(mContext)) {
            callback.onContinue(postcard);
        } else {
            callback.onInterrupt(null);
        }
    }

}
