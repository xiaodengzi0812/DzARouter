package com.dengzi.moduleb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/libB/two")
public class TwoActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_two_activity_main);
        setTitle("Module B");
    }

    @Override
    public void onClick(View v) {
        ARouter.getInstance().build("/libA/one").navigation();
    }
}
