package com.liujs.library.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.util.List;

/**
 * Created by liujs on 2016/9/27.
 * 邮箱：725459481@qq.com
 */

public class AppUtil {

    /**
     * 返回设备id或者Mac地址
     * @return
     */
    public static String getDeviceId(Context applicationContext) {
        TelephonyManager tm = (TelephonyManager) applicationContext.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm.getDeviceId() == null || tm.getDeviceId().trim().length() == 0) {
            WifiManager wifi = (WifiManager) applicationContext.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            return info.getMacAddress();
        } else {
            return tm.getDeviceId();
        }
    }

    /**
     * Manifest.xml
     */
    public static String getMetaValue(Context applicationContext,String metaKey) {
        if (TextUtils.isEmpty(metaKey)) {
            return null;
        }
        try {
            ApplicationInfo ai = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(),
                    PackageManager.GET_META_DATA);
            Bundle metaData = ai.metaData;
            if (null != metaData) {
                return metaData.getString(metaKey);
            }
        } catch (PackageManager.NameNotFoundException e) {

        }
        return null;
    }
    /**
     * 返回App版本号
     */
    public static int getAppVersionCode(Context applicationContext) {
        int versionCode = 1;
        try {
            versionCode = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            versionCode = 1;
        }
        return versionCode;
    }

    /**
     * 获取app版本号名称
     *
     * @return
     */
    public static String getAppVersionName(Context applicationContext) {
        String loadversion = "1.00";
        PackageManager nPackageManager = applicationContext.getPackageManager();// 得到包管理器
        PackageInfo nPackageInfo;
        try {
            nPackageInfo = nPackageManager.getPackageInfo(applicationContext.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            loadversion = nPackageInfo.versionName;// 得到现在app的版本号名称
        } catch (PackageManager.NameNotFoundException e) {
        }

        return loadversion;
    }

    /**
     * 获取应用程序名称
     */
    public static String getApplicationName(Context applicationContext) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = applicationContext.getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        return (String) packageManager.getApplicationLabel(applicationInfo);
    }


    /**
     * 返回操作系统版本
     *
     * @return
     */
    public static String getOSVerson() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 获取屏幕密度
     *
     * @param activity
     * @return
     */
    public static int getDpi(Context activity) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = activity.getResources().getDisplayMetrics();
        int densityDpi = dm.densityDpi; // 屏幕密度DPI（120 / 160 / 240）
        return densityDpi;
    }

    /**
     * 屏幕类型
     *
     * @param context
     * @return
     */
    public static ScreenType getScreenType(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        if (dm.densityDpi >= DisplayMetrics.DENSITY_HIGH) {
            return ScreenType.HSCREEN;
        } else if (dm.densityDpi == DisplayMetrics.DENSITY_MEDIUM) {
            return ScreenType.MSCREEN;
        } else if (dm.densityDpi == DisplayMetrics.DENSITY_LOW) {
            return ScreenType.LSCREEN;
        } else {
            return ScreenType.MSCREEN;
        }
    }

    /**
     * 应用是否被隐藏<在后台运行>
     *
     * @param context
     * @return
     */
    public static boolean IsRunOnBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
        if (context.getPackageName().equals(tasksInfo.get(0).topActivity.getPackageName())) {
            return false;
        }
        return true;
    }

    /**
     * 创建快捷方式
     *
     * @param context
     * @param cls
     * @param icon 快捷方式图标
     * @param app_name 快捷方式名称
     */
    public static void createShortcut(Context context, Class cls, int icon, int app_name) {
        final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
        // 是否可以有多个快捷方式的副本
        final String EXTRA_SHORTCUT_DUPLICATE = "duplicate";

        Intent createIntent = new Intent(ACTION_INSTALL_SHORTCUT);
        // 快捷方式的名称
        createIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, context.getString(app_name));
        // 不允许重复创建
        createIntent.putExtra(EXTRA_SHORTCUT_DUPLICATE, false);

        // 指定快捷方式的启动对象
        Intent target = new Intent(Intent.ACTION_MAIN);
        target.addCategory(Intent.CATEGORY_LAUNCHER);
        target.setComponent(new ComponentName(context.getPackageName(), cls.getName()));
        target.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
        createIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, target);

        // 快捷方式的图标
        createIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(context, icon));

        context.sendBroadcast(createIntent);
    }

    /**
     * . * 删除当前应用的桌面快捷方式 . * . * @param cx .
     */
    public static void delShortcut(Context cx) {
        Intent shortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");

        // 获取当前应用名称
        String title = null;
        try {
            final PackageManager pm = cx.getPackageManager();
            title = pm.getApplicationLabel(
                    pm.getApplicationInfo(cx.getPackageName(), PackageManager.GET_META_DATA))
                    .toString();
        } catch (Exception e) {
        }
        // 快捷方式名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
        Intent shortcutIntent = cx.getPackageManager().getLaunchIntentForPackage(
                cx.getPackageName());
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        cx.sendBroadcast(shortcut);
    }

}
