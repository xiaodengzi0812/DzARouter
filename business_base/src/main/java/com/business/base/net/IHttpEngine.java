package com.business.base.net;

import java.util.Map;

/**
 * @author Djk
 * @Title: 第三方网络框架接入规范
 * @Time: 2017/8/22.
 * @Version:1.0.0
 */
public interface IHttpEngine {
    //get请求
    void get(Object tag, String url, Map<String, Object> params, HttpCallBack callBack);

    //post请求
    void post(Object tag, String url, Map<String, Object> params, HttpCallBack callBack);

    //put请求
    void put(Object tag, String url, Map<String, Object> params, HttpCallBack callBack);

    //delete请求
    void delete(Object tag, String url, Map<String, Object> params, HttpCallBack callBack);

    //下载
    void download(Object tag, String url, String downloadLocation, HttpCallBack callback);

    //上传
    void upLoad(Object tag, String url, Map<String, Object> params, HttpCallBack callback);

    //取消请求
    void cancelRequest(Object tag);

}
