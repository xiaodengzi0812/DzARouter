package com.dengzi.modulea;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/libA/one")
public class OneActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_one_activity_main);
        setTitle("Module A");
    }

    @Override
    public void onClick(View v) {
        ARouter.getInstance().build("/libB/two").navigation();
    }
}
