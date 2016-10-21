package com.liujs.demo.testactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liujs.demo.R;
import com.liujs.library.base.BaseActivity;

public class CircleImageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_image);
         initActionBar();
        setTitle("裁圆演示");
    }
}
