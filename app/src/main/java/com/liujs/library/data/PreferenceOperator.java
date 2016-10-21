
package com.liujs.library.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 首选项设置操作类，所有设置的父类
 * 
 * @author liujs
 */
public class PreferenceOperator {

    private Context context;

    private String name;

    private int mode;

    /**
     * 构造函数
     * 
     * @param context　上下文
     * @param prefname　pref文件名
     */
    public PreferenceOperator(Context context, String prefname) {
        this.context = context;
        this.name = prefname;
        this.mode = Context.MODE_PRIVATE;
    }

    private SharedPreferences getSharedPreferences() {
        SharedPreferences sp = context.getSharedPreferences(name, mode);
        return sp;
    }

    public String getString(String key, String defaultValue) {
        return getSharedPreferences().getString(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        return getSharedPreferences().getInt(key, defaultValue);
    }

    public long getLong(String key, long defaultValue) {
        return getSharedPreferences().getLong(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return getSharedPreferences().getBoolean(key, defaultValue);
    }

    public float getFloat(String key, float defaultValue) {
        return getSharedPreferences().getFloat(key, defaultValue);
    }

    public boolean putString(String key, String value) {
        return getSharedPreferences().edit().putString(key, value).commit();
    }

    public boolean putInt(String key, int value) {
        return getSharedPreferences().edit().putInt(key, value).commit();
    }

    public boolean putLong(String key, long value) {
        return getSharedPreferences().edit().putLong(key, value).commit();
    }

    public boolean putBoolean(String key, boolean value) {
        return getSharedPreferences().edit().putBoolean(key, value).commit();
    }

    public boolean putFloat(String key, float value) {
        return getSharedPreferences().edit().putFloat(key, value).commit();
    }

    public Editor getEditor() {
        return getSharedPreferences().edit();
    }

    public boolean contains(String key) {
        return getSharedPreferences().contains(key);
    }

    /*************** 支持资源ID ******************/

    public String getString(int resId, String defValue) {
        return getString(context.getString(resId), defValue);
    }

    public int getInt(int resId, int defValue) {
        return getInt(context.getString(resId), defValue);
    }

    public float getFloat(int resId, float defValue) {
        return getFloat(context.getString(resId), defValue);
    }

    public boolean getBoolean(int resId, boolean defValue) {
        return getBoolean(context.getString(resId), defValue);
    }

    public long getLong(int resId, long defValue) {
        return getLong(context.getString(resId), defValue);
    }

    public boolean putInt(int resId, int value) {
        return putInt(context.getString(resId), value);
    }

    public boolean putFloat(int resId, float value) {
        return putFloat(context.getString(resId), value);
    }

    public boolean putBoolean(int resId, boolean value) {
        return putBoolean(context.getString(resId), value);
    }

    public boolean putString(int resId, String value) {
        return putString(context.getString(resId), value);
    }

    public boolean putLong(int resId, long value) {
        return putLong(context.getString(resId), value);
    }

    public boolean removeKey(String key) {
        return getEditor().remove(key).commit();
    }

}
