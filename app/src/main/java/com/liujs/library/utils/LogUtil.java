package com.liujs.library.utils;

import com.liujs.library.BuildConfig;
import android.text.TextUtils;
import android.util.Log;


public class LogUtil {
	
	/**
	 * 封装log
	 * 正式版不打日志 安全
	 */

	public static void println(String msg) {
		if (TextUtils.isEmpty(msg)) {
			return;
		}
		if (BuildConfig.DEBUG) {
			System.out.println(msg);
		}
	}
	public static void Log(String msg){
		if (BuildConfig.DEBUG) {
			Log.i("tag", msg == null ? "null" : msg);
		}
	}

	//调试LOG
	public static void d(String tag, String msg){
		if (TextUtils.isEmpty(msg)) {
			return;
		}
		if (BuildConfig.DEBUG) {
			Log.d(tag, msg);
		}
	}

	//消息LOG
	public static void i(String tag, String msg){
		if (TextUtils.isEmpty(msg)) {
			return;
		}
		if (BuildConfig.DEBUG) {
			Log.i(tag, msg);
		}
	}

    //警告LOG
    public static void w(String tag, String msg){
		if (TextUtils.isEmpty(msg)) {
			return;
		}
        if (BuildConfig.DEBUG) {
            Log.w(tag, msg);
        }
    }

    //错误LOG
    public static void e(String tag, String msg){
		if (TextUtils.isEmpty(msg)) {
			return;
		}
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }
}
