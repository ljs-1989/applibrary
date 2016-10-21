package com.liujs.demo.testactivity;

import android.content.Intent;
import com.liujs.demo.R;
import com.liujs.demo.okhttpdemo.okhttputils.OkHttpUtilsActivity;
import com.liujs.library.base.BaseActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {
    public static final int REFRESH_STATE_MEITUAN = 1,REFRESH_STATE_MOOC = 2,REFRESH_STATE_NORMAL = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.findViewById(R.id.refresh_button1).setOnClickListener(clickListener);
        this.findViewById(R.id.refresh_button2).setOnClickListener(clickListener);
        this.findViewById(R.id.refresh_button3).setOnClickListener(clickListener);
        this.findViewById(R.id.refresh_button4).setOnClickListener(clickListener);
        this.findViewById(R.id.refresh_button5).setOnClickListener(clickListener);
        this.findViewById(R.id.refresh_button6).setOnClickListener(clickListener);
        initActionBar();
        setTitle("总览");
        mPrivousButton.setVisibility(View.GONE);
    }

    private View.OnClickListener clickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,TestRefreshActivity.class);
            switch (v.getId()){
                case R.id.refresh_button1:
                    intent.putExtra("REFRESH_STYLE",REFRESH_STATE_MEITUAN);
                    startActivity(intent);
                    break;
                case R.id.refresh_button2:
                    intent.putExtra("REFRESH_STYLE",REFRESH_STATE_MOOC);
                    startActivity(intent);
                    break;
                case R.id.refresh_button3:
                    intent.putExtra("REFRESH_STYLE",REFRESH_STATE_NORMAL);
                    startActivity(intent);
                    break;
                case R.id.refresh_button4:
                    Intent httpIntent = new Intent(MainActivity.this, OkHttpUtilsActivity.class);
                    startActivity(httpIntent);
                    break;
                case R.id.refresh_button5:
                    Intent cricleIntent = new Intent(MainActivity.this, CircleImageActivity.class);
                    startActivity(cricleIntent);
                    break;
                case  R.id.refresh_button6:
                    Intent providerIntent = new Intent(MainActivity.this, TestProviderActivity.class);
                    startActivity(providerIntent);
                    break;
            }

        }
    };
}
