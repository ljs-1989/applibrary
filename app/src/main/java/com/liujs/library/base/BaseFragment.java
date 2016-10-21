package com.liujs.library.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.liujs.library.R;

/**
 * Created by liujs on 2016/10/8.
 * 邮箱：725459481@qq.com
 */

public class BaseFragment extends Fragment {
    protected Button mPrivousButton;
    protected  ImageButton nextButton;
    protected TextView mTvTitle;
    protected TextView nextTextView;
    protected Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = this.getActivity();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected View.OnClickListener previousListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mActivity.onBackPressed();
        }
    };


    protected  void initActionBar(){

        mPrivousButton = (Button) mActivity.findViewById(R.id.btn_previous);
        nextButton = (ImageButton) mActivity.findViewById(R.id.btn_other);
        mTvTitle = (TextView)mActivity.findViewById(R.id.tv_title);
        nextTextView = (TextView)mActivity.findViewById(R.id.tv_other);
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
}
