package com.liujs.library.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by liujs on 2016/10/14.
 * 邮箱：725459481@qq.com
 */

public class DipAndPxUtil {

    /**
     * dip转px
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int dipToPx(Context context, int dipValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, context
                .getResources().getDisplayMetrics());
    }

    /**
     * 转换px为dip
     *
     * @param context
     * @param px
     * @return
     */
    public static int pxToDip(Context context, int px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f * (px >= 0 ? 1 : -1));
    }

}
