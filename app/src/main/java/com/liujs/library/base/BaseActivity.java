package com.liujs.library.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.liujs.library.R;

/**
 * Created by liujs on 2016/10/8.
 * 邮箱：725459481@qq.com
 */

public class BaseActivity extends AppCompatActivity {
    protected Button mPrivousButton;
    protected  ImageButton nextButton;
    protected TextView mTvTitle;
    protected TextView nextTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarStyle();
    }
    protected View.OnClickListener previousListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    };
   protected  void initActionBar(){

       mPrivousButton = (Button) this.findViewById(R.id.btn_previous);
       nextButton = (ImageButton) this.findViewById(R.id.btn_other);
       mTvTitle = (TextView)this.findViewById(R.id.tv_title);
       nextTextView = (TextView)this.findViewById(R.id.tv_other);
       if(mPrivousButton!=null)mPrivousButton.setOnClickListener(previousListener);
   }
    public  void setTitle(@StringRes int stringRes){
        if(mTvTitle!=null){
            mTvTitle.setText(getText(stringRes));
        }
    }

    public void setTitle(String title){
        if(mTvTitle!=null){
            mTvTitle.setText(title);
        }
    }
    public void setNextText(String string){
        if(nextTextView!=null){
            if(nextTextView.getVisibility()==View.GONE){
                nextTextView.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.GONE);
            }
            nextTextView.setText(string);
        }
    }
    /**
     * 右边按钮的文字说明
     * @param stringRes
     */
    public void setNextText(@StringRes int stringRes){
        setNextText(getString(stringRes));
    }

    /**
     * 右边按钮的logo
     * @param drawableRes
     */
    public void setNextLogo(@DrawableRes int drawableRes){
              if(nextButton!=null){
                  if(nextButton.getVisibility()==View.GONE){
                      nextButton.setVisibility(View.VISIBLE);
                      nextTextView.setVisibility(View.GONE);
                  }
                  nextButton.setImageResource(drawableRes);
              }
    }

    /***
     * actionBar 右边点击事件
     * @param clickListener
     */
 public void setNextClickListener(View.OnClickListener clickListener){
     if(nextButton!=null){
         if(nextButton.getVisibility()==View.GONE){
             nextButton.setVisibility(View.VISIBLE);
             nextTextView.setVisibility(View.GONE);
         }
         nextButton.setOnClickListener(clickListener);
     }
 }

    /**
     * 返回按钮的图片设置
     * @param drawableRes
     */
    public void setReturnLogo(@DrawableRes int drawableRes){
        if(mPrivousButton!=null){
            mPrivousButton.setCompoundDrawablesWithIntrinsicBounds(0,0,0,drawableRes);
        }
    }

    private void statusBarStyle() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,  android.R.color.transparent));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
}
