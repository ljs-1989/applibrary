package com.liujs.library.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by liujs on 2016/8/29.
 */
public class FileUtil {
    /**
     * 检测sdcard是否可用
     *
     * @return true为可用，否则为不可用
     */
    public static boolean isSDCardAvailable() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡根路径
     * @return
     */
    public static String getSDPath(){
      return  Environment.getExternalStorageDirectory().getPath();
    }
    /**
     * 获取 程序sdcard目录
     */
    public static String getSDCardAppCachePath(@NonNull Context context) {
        File file = context.getExternalCacheDir();
        if (file == null) {
            return null;
        }
        return file.getAbsolutePath();
    }


    /**
     * 删除目录 及其 子目录
     *
     * @param filePath
     * @return
     */
    public static boolean deleteDirectory(String filePath) {
        if (null == filePath) {
            Log.e("fileUtil","param invalid, filePath: " + filePath);
            return false;
        }

        File file = new File(filePath);

        if (file == null || !file.exists()) {
            return false;
        }

        if (file.isDirectory()) {
            File[] list = file.listFiles();

            for (int i = 0; i < list.length; i++) {
                Log.d("fileUtil","delete filePath: " + list[i].getAbsolutePath());

                if (list[i].isDirectory()) {
                    deleteDirectory(list[i].getAbsolutePath());
                } else {
                    list[i].delete();
                }
            }
        }

        Log.e("fileUtil","delete filePath: " + file.getAbsolutePath());

        file.delete();
        return true;
    }

    /**
     * 获取文件大小
     *
     * @param filePath
     * @return
     */
    public static long getFileSize(String filePath) {
        if (null == filePath) {
            Log.e("fileUtil","Invalid param. filePath: " + filePath);
            return 0;
        }

        File file = new File(filePath);
        if (file == null || !file.exists()) {
            return 0;
        }

        return file.length();
    }
    // 获取sd卡剩余大小
    public static int getAvailableBlocks() {
        File filePath = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(filePath.getPath());
        return (int) (statFs.getBlockSize()
                * ((long) statFs.getAvailableBlocks()) / 1024 / 1024);
    }
    /**
     * 获取文件最后修改时间
     *
     * @param filePath
     * @return
     */
    public static long getFileModifyTime(String filePath) {
        if (null == filePath) {
            Log.e("fileUtil","Invalid param. filePath: " + filePath);
            return 0;
        }

        File file = new File(filePath);
        if (file == null || !file.exists()) {
            return 0;
        }

        return file.lastModified();
    }

    /**
     * 设置文件最后修改时间
     *
     * @param filePath
     * @param modifyTime
     * @return
     */
    public static boolean setFileModifyTime(String filePath, long modifyTime) {
        if (null == filePath) {
            return false;
        }

        File file = new File(filePath);
        if (file == null || !file.exists()) {
            return false;
        }

        return file.setLastModified(modifyTime);
    }
    public static boolean isCheckSDCardWarning() {
        return !isSDCardAvailable();
    }

    public static boolean createDir(String path) {
        if (isCheckSDCardWarning()) {
            return false;
        }

        if (TextUtils.isEmpty(path)) {
            return false;
        }

        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        return true;
    }

    public static File createFile(String path, String filename) {
        if (!createDir(path)) {
            return null;
        }

        if (TextUtils.isEmpty(filename)) {
            return null;
        }

        File file = null;
        file = new File(path, filename);
        if (file.exists()) {
            return file;
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return file;
    }

    public static File createFile(String absolutePath) {
        if (TextUtils.isEmpty(absolutePath)) {
            return null;
        }

        if (isCheckSDCardWarning()) {
            return null;
        }

        File file = new File(absolutePath);
        if (file.exists()) {
            return file;
        } else {
            File dir = file.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }

            try {

                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;

            }
        }
        return file;
    }

    public static boolean isFileExist(String filePath) {
        if (TextUtils.isEmpty(filePath))
            return false;

        File file = new File(filePath);
        return file != null && file.exists() && file.isFile();
    }



    public static File createNewFile(String path, String name) {
        if (isCheckSDCardWarning()) {
            return null;
        }

        File file = new File(path, name);
        if (file.exists()) {
            file.delete();
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
           e.printStackTrace();
        }
        return file;
    }

    public static File getApkFile(Context context, String fileName) {
        return createFile(getSDCardAppCachePath(context), fileName);
    }

    public static String getApkAbsolutePath(Context context, String fileName) {
        return getSDCardAppCachePath(context) + File.separator +  fileName;
    }


    /**
     * Perform an fsync on the given FileOutputStream.  The stream at this
     * point must be flushed but not yet closed.
     */
    public static boolean sync(FileOutputStream stream) {
        try {
            if (stream != null) {
                stream.getFD().sync();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // copy a file from srcFile to destFile, return true if succeed, return
    // false if fail
    public static boolean copyFile(File srcFile, File destFile) {
        boolean result = false;
        try {
            InputStream in = new FileInputStream(srcFile);
            try {
                result = copyToFile(in, destFile);
            } finally  {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     * Copy data from a source stream to destFile.
     * Return true if succeed, return false if failed.
     */
    public static boolean copyToFile(InputStream inputStream, File destFile) {
        try {
            if (destFile.exists()) {
                destFile.delete();
            }
            FileOutputStream out = new FileOutputStream(destFile);
            try {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) >= 0) {
                    out.write(buffer, 0, bytesRead);
                }
            } finally {
                out.flush();
                try {
                    out.getFD().sync();
                } catch (IOException e) {
                }
                out.close();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
