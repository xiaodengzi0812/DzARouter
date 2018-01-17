我们自己搭一个网络框架（包装第三方网络框架的框架，没有第三方的网络框架，我还真不知道这个框架要怎么搭）
1、客户端不需要直接与第三方的网络框架接触，方便以后切换网络框架
2、回调都用我们自己写的，也是不与第三方的网络框架接触，方便以切换网络框架
3、链式调用，模仿OkHttp的写法
4、实现一键式切换，实现网络引擎随便切换
5、框架中不自带网络引擎和回调实现，需要自己去实现
6、在demo中实现了网络引擎和回调实现，可以参考来实现自己需要的

使用：
1、使用时需要自己实现网络引擎，并自己实现带业务bean的回调方法，详见包com.business.core.net

2、在Application中初始化默认的网络引擎 HttpManager.initEngine(OkHttpEngine.getInstance());

2、在需要调用的时候
    HttpManager.getInstence(this).url("http://www.baidu.com")
        .get().execute(new FastJsonCallBack<BaseBean>() {
        @Override
        public void onSuccess(BaseBean bean) {
            tv.setText("success:" + bean.getMessage());
        }

        @Override
        public void onFailed(int code, String message) {
            tv.setText("error:" + message);
        }
    });