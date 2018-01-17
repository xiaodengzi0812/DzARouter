package com.business.base.util;

import android.app.Activity;

import java.util.Iterator;
import java.util.Stack;

/**
 * @Title:Activity管理类
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public class ActivityManager {
    private static volatile ActivityManager mInstance;
    // 删除和添加比较多，用链表
    private Stack<Activity> mActivities;

    private ActivityManager() {
        mActivities = new Stack<>();
    }

    public static ActivityManager getInstance() {
        if (mInstance == null) {
            synchronized (ActivityManager.class) {
                if (mInstance == null) {
                    mInstance = new ActivityManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 添加统一管理
     *
     * @param activity
     */
    public void attach(Activity activity) {
        if (activity == null) return;
        mActivities.add(activity);
    }

    /**
     * 移除解绑 - 防止内存泄漏
     *
     * @param detachActivity
     */
    public void detach(Activity detachActivity) {
        if (detachActivity == null) return;
        Iterator<Activity> iterator = mActivities.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity != null && activity == detachActivity) {
                iterator.remove();
            }
        }
    }

    /**
     * 关闭当前的 Activity
     *
     * @param finishActivity
     */
    public void finishActivity(Activity finishActivity) {
        if (finishActivity == null) return;
        Iterator<Activity> iterator = mActivities.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity != null && activity == finishActivity) {
                iterator.remove();
                activity.finish();
            }
        }
    }

    /**
     * 根据Activity的类名关闭 Activity
     */
    public void finishActivity(Class<? extends Activity> activityClass) {
        if (activityClass == null) return;
        Iterator<Activity> iterator = mActivities.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity != null && activity.getClass().getCanonicalName().equals(activityClass.getCanonicalName())) {
                iterator.remove();
                activity.finish();
            }
        }
    }

    /**
     * 退出整个应用
     */
    public void exitApp() {
        Iterator<Activity> iterator = mActivities.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity != null) {
                activity.finish();
            }
            iterator.remove();
        }
    }

    /**
     * 获取当前的Activity（最前面）
     *
     * @return
     */
    public Activity currentActivity() {
        return mActivities.lastElement();
    }
}
