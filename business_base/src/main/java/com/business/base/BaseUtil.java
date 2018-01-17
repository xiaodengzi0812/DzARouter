package com.business.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

import com.business.base.util.CrashUtils;

/**
 * @Title:Utils初始化相关
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public final class BaseUtil {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private static volatile BaseUtil mDZUtil;

    private BaseUtil(@NonNull final Context context) {
        BaseUtil.context = context.getApplicationContext();
        initCrash();
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static BaseUtil init(@NonNull final Context context) {
        if (mDZUtil == null) {
            synchronized (BaseUtil.class) {
                if (mDZUtil == null) {
                    mDZUtil = new BaseUtil(context.getApplicationContext());
                }
            }
        }
        return mDZUtil;
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("you should init first");
    }

    /**
     * 初始化异常捕获器
     */
    public void initCrash() {
        CrashUtils.init();
    }
}
