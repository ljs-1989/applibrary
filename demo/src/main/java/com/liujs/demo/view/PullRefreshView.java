package com.liujs.demo.view;

import android.content.Context;
import com.liujs.demo.R;
import com.liujs.library.view.refresh.BGAMeiTuanRefreshViewHolder;
import com.liujs.library.view.refresh.BGAMoocStyleRefreshViewHolder;
import com.liujs.library.view.refresh.BGANormalRefreshViewHolder;
import com.liujs.library.view.refresh.BGARefreshLayout;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by liujs on 2016/9/13.
 * 下拉刷新控件(不支持上拉加载)，开发者可以通过重写BGARefreshViewHolder自定义自己想要的下拉刷新样式
 */
public class PullRefreshView extends BGARefreshLayout {


    public PullRefreshView(Context context) {
        super(context);
    }

    public PullRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initRefreshStyle(Context context,int refreshStyle) {
        switch (refreshStyle){
            case 1:
                //仿美团下拉刷新
                BGAMeiTuanRefreshViewHolder meiTuanRefreshViewHolder = new BGAMeiTuanRefreshViewHolder(context, true);
                meiTuanRefreshViewHolder.setPullDownImageResource(R.drawable.anim_loading);
                meiTuanRefreshViewHolder.setChangeToReleaseRefreshAnimResId(R.drawable.anim_loading);
                meiTuanRefreshViewHolder.setRefreshingAnimResId(R.drawable.anim_loading);
                this.setRefreshViewHolder(meiTuanRefreshViewHolder);
                break;
            case 2:
                   //仿慕课网下拉刷新风格
                BGAMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(context, true);
                moocStyleRefreshViewHolder.setOriginalImage(R.mipmap.bga_refresh_moooc);
                moocStyleRefreshViewHolder.setUltimateColor(R.color.imoocstyle);
                this.setRefreshViewHolder(moocStyleRefreshViewHolder);
                break;
            case 3:
                //仿新浪微博下拉刷新风格
                this.setRefreshViewHolder(new BGANormalRefreshViewHolder(context, true));
                break;
        }
        setClickable(false);
        setFocusable(false);
    }

//设置被刷新的View
public void setRefreshContent(View contentView){
    this.addView(contentView,1);
}


}