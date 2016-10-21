package com.liujs.library.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.graphics.BitmapFactory.decodeStream;

/**
 * Created by liujs on 2016/10/14.
 * 邮箱：725459481@qq.com
 */

public class BitmapUtil {

    /**
     * 将Drawable 转换成 Bitmap
     */

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        // canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 将Bitmap转换成Drawable
      */

    public static Drawable BitmapToDrawable(Resources res,Bitmap bit) {
        Drawable drawable;
        drawable = new BitmapDrawable(res,bit);
        int w = (int) (drawable.getIntrinsicWidth() * 1.5);
        int h = (int) (drawable.getIntrinsicHeight() * 1.5);
        drawable.setBounds(0, 0, w, h);
        return drawable;
    }


    /**
     * 读取
     *
     * @param filePath 文件路径
     * @return
     */
    public static Bitmap readBitmap(final String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        } else {
            // BitmapFactory.Options options=new BitmapFactory.Options();
            // options.inSampleSize = 8;
            return BitmapUtil.decodeFile(filePath);
        }
    }
    public static Bitmap decodeFile(String pathName) {
        Bitmap bitmap = null;
        try {
            bitmap = null;
            if (FileUtil.isFileExist(pathName)) {
                File file = new File(pathName);
                InputStream is  = new FileInputStream(file);
                bitmap = decodeStream(is);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    /**
     * 把bitmap保存到SD卡上
     *
     * @param bitmap 源图片
     * @param savePath 保存路径
     * @param format 图片格式
     */
    public static boolean saveBitmap(Bitmap bitmap, String savePath, Bitmap.CompressFormat format) {
        if (bitmap == null || TextUtils.isEmpty(savePath)) {
            return false;
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(savePath);
            bitmap.compress(format, 80, fos);
        } catch (FileNotFoundException e) {
            return false;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 把bitmap保存到SD卡上
     *
     * @param bitmap 源图片
     * @param savePath 保存路径
     * @param format 图片格式
     */
    public static boolean saveBitmap(Bitmap bitmap, String savePath, Bitmap.CompressFormat format,
                                     int quality) {
        if (bitmap == null || TextUtils.isEmpty(savePath)) {
            return false;
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(savePath);
            bitmap.compress(format, quality, fos);
        } catch (FileNotFoundException e) {
            return false;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 缩放图片
     *
     * @param bitmap 源图片
     * @param w 新图片宽
     * @param h 新图片高
     * @return 新图片
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidht = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidht, scaleHeight);
        Bitmap newbmp = createBitmap(bitmap, 0, 0, width, height, matrix, true);
        // bitmap.recycle();
        return newbmp;
    }

    public static Bitmap createBitmap(Bitmap source, int x, int y, int width, int height, Matrix m,
                                      boolean filter) {
        Bitmap bitmap = null;
        if (source != null && !source.isRecycled()) {
            try {
                bitmap = Bitmap.createBitmap(source, 0, 0, width, height, m, true);
            } catch (OutOfMemoryError e) {
                System.gc();
                try {
                    bitmap = Bitmap.createBitmap(source, 0, 0, width, height, m, true);
                } catch (OutOfMemoryError e1) {
                }
            } catch (Exception e2) {
            }
        }
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
        }
        return bitmap;
    }
}
