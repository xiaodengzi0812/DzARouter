package com.dengzi.dzarouter.testactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.dengzi.dzarouter.R;
import com.dengzi.dzarouter.testinject.TestObj;
import com.dengzi.dzarouter.testinject.TestParcelable;
import com.dengzi.dzarouter.testservice.HelloService;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Route(path = "/test/login")
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClick(View view) {
        EventBus.getDefault().post("success");
        this.finish();
    }
}
