package com.dengzi.dzarouter.util;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Djk
 * @Title:
 * @Time: 2018/1/18.
 * @Version:1.0.0
 */
public class ExtraUtil {

    /**
     * 一个int中可以配置31个1或者0，而每一个0或者1都可以表示一项配置，
     * 这时候只需要从这31个位置中随便挑选出一个表示是否需要登录就可以了，
     * 只要将标志位置为1，就可以在刚才声明的拦截器中获取到这个标志位
     *
     * @param extra
     * @return
     */
    public static List<Integer> getExtra(int extra) {
        List<Integer> extraList = new ArrayList<>(32);
        for (int i = 0; i < 32; i++) {
            if ((extra & 1) == 1) {
                extraList.add(i);
            }
            extra = extra >> 1;
        }
        Log.e("dengzi", "extraList = " + extraList.toString());
        return extraList;
    }

    public static Integer getExtra(int extra, int index) {
        extra = extra >> index;
        return extra & 1;
    }
}
