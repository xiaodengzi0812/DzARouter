package com.business.base.net;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: 自己的网络访问工具类
 * @Author: djk
 * @Time: 2017/11/13
 * @Version:1.0.0
 */
public class HttpManager {

    //上下文
    private Context mContext;
    //网络引擎
    private static IHttpEngine mHttpEngine;
    //网络地址
    private String mUrl;
    //请求参数
    private Map<String, Object> mParams = new HashMap<>();
    //下载保存地址
    private String mDownPath;
    //请求方式
    private TYPE mHttpType = TYPE.GET;

    //请求方式的枚举
    public enum TYPE {
        GET,
        POST,
        PUT,
        DELETE,
        DOWNLOAD,
        UPLOAD
    }

    //给一个默认构造函数，不能new
    private HttpManager() {
    }

    //给一个有参构造函数，不能new
    private HttpManager(Context mContext) {
        this.mContext = mContext;
    }

    //初始化工具类
    public static HttpManager getInstence(Context context) {
        return new HttpManager(context);
    }

    //在application中加载请求引擎
    public static void initEngine(IHttpEngine httpEngine) {
        mHttpEngine = httpEngine;
    }

    //更改网络请求引擎
    public HttpManager changeEngine(IHttpEngine httpEngine) {
        this.mHttpEngine = httpEngine;
        return this;
    }

    //设置请求地址
    public HttpManager url(String url) {
        this.mUrl = url;
        return this;
    }

    //设置下载保存目录
    public HttpManager downPath(String downPath) {
        this.mDownPath = downPath;
        return this;
    }

    //get方法
    public HttpManager get() {
        this.mHttpType = TYPE.GET;
        return this;
    }

    //post方法
    public HttpManager post() {
        this.mHttpType = TYPE.POST;
        return this;
    }

    //put方法
    public HttpManager put() {
        this.mHttpType = TYPE.PUT;
        return this;
    }

    //delete方法
    public HttpManager delete() {
        this.mHttpType = TYPE.DELETE;
        return this;
    }

    //download方法
    public HttpManager download() {
        this.mHttpType = TYPE.DOWNLOAD;
        return this;
    }

    //upload方法
    public HttpManager upload() {
        this.mHttpType = TYPE.UPLOAD;
        return this;
    }

    //添加参数
    public HttpManager param(String key, Object value) {
        this.mParams.put(key, value);
        return this;
    }

    //添加参数
    public HttpManager param(Map<String, Object> params) {
        this.mParams.putAll(params);
        return this;
    }

    //执行有回调的方法
    public void execute(HttpCallBack callBack) {
        //如果无回调，默认给一个空回调
        if (callBack == null) {
            callBack = callBack.getDefaultCallBack();
        }
        //请求前的准备
        callBack.onPre(mParams);
        if (mHttpType == TYPE.GET) {
            mHttpEngine.get(mContext, mUrl, mParams, callBack);
        }
        if (mHttpType == TYPE.POST) {
            mHttpEngine.post(mContext, mUrl, mParams, callBack);
        }
        if (mHttpType == TYPE.PUT) {
            mHttpEngine.put(mContext, mUrl, mParams, callBack);
        }
        if (mHttpType == TYPE.DELETE) {
            mHttpEngine.delete(mContext, mUrl, mParams, callBack);
        }
        if (mHttpType == TYPE.DOWNLOAD) {
            mHttpEngine.download(mContext, mUrl, mDownPath, callBack);
        }
        if (mHttpType == TYPE.UPLOAD) {
            mHttpEngine.upLoad(mContext, mUrl, mParams, callBack);
        }
    }

    //执行无回调的方法
    public void execute() {
        execute(null);
    }

}
