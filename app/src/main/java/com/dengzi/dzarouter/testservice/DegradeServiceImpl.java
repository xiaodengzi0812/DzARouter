package com.dengzi.dzarouter.testservice;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;

/**
 * @author Djk
 * @Title:
 * @Time: 2018/1/17.
 * @Version:1.0.0
 */
@Route(path = "/service/degrade")
public class DegradeServiceImpl implements DegradeService {
    @Override
    public void onLost(Context context, Postcard postcard) {
        Log.e("dengzi", "DegradeServiceImpl - onLost");
    }

    @Override
    public void init(Context context) {

    }
}
