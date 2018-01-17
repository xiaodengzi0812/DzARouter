package com.dengzi.dzarouter.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Looper;
import android.widget.Toast;

/**
 * @author Djk
 * @Title:
 * @Time: 2018/1/16.
 * @Version:1.0.0
 */

public class CheckNetUtil {
    /**
     * 检查当前网络是否可用
     *
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        boolean result = false;
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        result = true;
                    }
                }
            }
        }
        if (!result) {
            Looper.prepare();
            Toast.makeText(context, "网络联接失败，请重试", Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
        return result;
    }

}
