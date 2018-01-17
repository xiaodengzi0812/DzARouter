package com.dengzi.dzarouter.testactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dengzi.dzarouter.R;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/test/activity2")
public class Test2Activity extends AppCompatActivity {

    @Autowired
    String key1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        ARouter.getInstance().inject(this);
        String value = getIntent().getStringExtra("key1");
        if (!TextUtils.isEmpty(key1)) {
            Toast.makeText(this, "exist param :" + key1 + "," + value, Toast.LENGTH_LONG).show();
        }

        setResult(999);
    }
}
