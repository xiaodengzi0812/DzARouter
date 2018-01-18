package com.dengzi.dzarouter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dengzi.dzarouter.testinject.TestObj;
import com.dengzi.dzarouter.testinject.TestParcelable;
import com.dengzi.dzarouter.testinterceptor.LoginInterceptor;
import com.dengzi.dzarouter.testservice.HelloService;
import com.dengzi.dzarouter.testservice.SingleService;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.dengzi.dzarouter.util.ExtraUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
    }

    public static Activity getThis() {
        return activity;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // ---------------------------------------------------------------
            case R.id.openLog:
                ARouter.openLog();
                break;
            case R.id.openDebug:
                ARouter.openDebug();
                break;
            case R.id.init:
                // 调试模式不是必须开启，但是为了防止有用户开启了InstantRun，但是
                // 忘了开调试模式，导致无法使用Demo，如果使用了InstantRun，必须在
                // 初始化之前开启调试模式，但是上线前需要关闭，InstantRun仅用于开
                // 发阶段，线上开启调试模式有安全风险，可以使用BuildConfig.DEBUG
                // 来区分环境
                ARouter.openDebug();
                ARouter.init(getApplication());
                break;
            case R.id.destroy:
                ARouter.getInstance().destroy();
                break;
            // ---------------------------------------------------------------
            case R.id.normalNavigation://简单的应用内跳转
                ARouter.getInstance()
                        .build("/test/activity2")
                        .navigation();
                break;
            case R.id.normalNavigation2://跳转ForResult
                ARouter.getInstance()
                        .build("/test/activity2")
                        .navigation(this, 666);
                break;
            case R.id.getFragment://获取Fragment实例
                Fragment fragment = (Fragment) ARouter.getInstance().build("/test/fragment").navigation();
                Toast.makeText(this, "找到Fragment:" + fragment.toString(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.normalNavigationWithParams://携带参数的应用内跳转
                // ARouter.getInstance()
                //         .build("/test/activity2")
                //         .withString("key1", "value1")
                //         .navigation();
                Uri testUriMix = Uri.parse("arouter://m.aliyun.com/test/activity2");
                ARouter.getInstance().build(testUriMix)
                        .withString("key1", "dengzi")
                        .navigation();
                break;
            case R.id.oldVersionAnim://旧版本转场动画
                ARouter.getInstance()
                        .build("/test/activity2")
                        .withTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                        .navigation(this);
                break;
            case R.id.newVersionAnim://新版本转场动画
                if (Build.VERSION.SDK_INT >= 16) {
                    ActivityOptionsCompat compat = ActivityOptionsCompat.
                            makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);
                    ARouter.getInstance()
                            .build("/test/activity2")
                            .withOptionsCompat(compat)
                            .navigation();
                } else {
                    Toast.makeText(this, "API < 16,不支持新版本动画", Toast.LENGTH_SHORT).show();
                }
                break;
            // ---------------------------------------------------------------
            case R.id.navByUrl://通过URL跳转
                ARouter.getInstance()
                        .build("/test/webview")
                        .withString("url", "file:///android_asset/schame-test.html")
                        .navigation();
                break;
            case R.id.interceptor://拦截器测试
                ARouter.getInstance()
                        .build("/test/activity4")
                        .navigation(this, new NavCallback() {
                            @Override
                            public void onArrival(Postcard postcard) {
                                Log.e("dengzi", "正常通过");
                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {
                                Log.e("dengzi", "被拦截了");
                            }
                        });
                break;
            case R.id.autoInject://依赖注入(参照代码)
                TestParcelable testParcelable = new TestParcelable("jack", 666);
                TestObj testObj = new TestObj("Rose", 777);
                List<TestObj> objList = new ArrayList<>();
                objList.add(testObj);

                Map<String, List<TestObj>> map = new HashMap<>();
                map.put("testMap", objList);

                ARouter.getInstance().build("/test/activity1")
                        .withString("name", "老王")
                        .withInt("age", 18)
                        .withBoolean("boy", true)
                        .withLong("high", 180)
                        .withString("url", "https://a.b.c")
                        .withParcelable("pac", testParcelable)
                        .withObject("obj", testObj)
                        .withObject("objList", objList)
                        .withObject("map", map)
                        .navigation();
                break;
            // ---------------------------------------------------------------
            case R.id.navByName://ByName调用服务
                HelloService helloServiceByName = (HelloService) ARouter.getInstance().build("/service/hello").navigation();
                helloServiceByName.sayHello("dengzi");
                break;
            case R.id.navByType://ByType调用服务
                HelloService helloServiceByType = ARouter.getInstance().navigation(HelloService.class);
                helloServiceByType.sayHello("dengzi");
                break;
            case R.id.callSingle:// 调用单类
                ARouter.getInstance().navigation(SingleService.class).sayHello("dengzi");
                break;
            // ---------------------------------------------------------------
            case R.id.navToMoudle1:
                ARouter.getInstance().build("/libA/one").navigation();
                break;
            case R.id.navToMoudle2:
                // 这个页面主动指定了Group名
                ARouter.getInstance().build("/libB/two").navigation();
                break;
            // ---------------------------------------------------------------
            case R.id.failNav://跳转失败，单独降级
                ARouter.getInstance().build("/xxx/xxx").navigation(this, new NavCallback() {
                    @Override
                    public void onFound(Postcard postcard) {
                        Log.e("dengzi", "找到了");
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        Log.e("dengzi", "找不到了");
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        Log.e("dengzi", "跳转完了");
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        Log.e("dengzi", "被拦截了");
                    }
                });
                break;
            case R.id.failNav2://跳转失败，全局降级
                ARouter.getInstance().build("/xxx/xxx").navigation();
                break;
            case R.id.failNav3://服务调用失败
                ARouter.getInstance().navigation(MainActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 666:
                Log.e("dengzi", String.valueOf(resultCode));
                break;
            default:
                break;
        }
    }
}
