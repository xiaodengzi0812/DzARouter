package com.business.base.net;

import java.util.Map;

/**
 * @Title: 网络访问回调
 * @Author: djk
 * @Time: 2017/11/13
 * @Version:1.0.0
 */
public interface HttpCallBack {
    //网络访问失败默认码
    int ERROR_CODE = 404;
    //网络访问失败默认字符串
    String EMPTY_RESULT = "empty result!";

    //准备执行，可以在这个方法中添加默认参数
    void onPre(Map<String, Object> mParams);

    // 失败的回调
    void onError(int code, String msg);

    //成功的回调
    void onSuccess(String result);

    HttpCallBack getDefaultCallBack();
}
