package com.liujs.library.utils;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;

/**
 * Created by liujs on 2016/10/14.
 * 邮箱：725459481@qq.com
 */

public class ObjectFileUtil {

    /***
     * 保存对象到文件
     *
     * @param context
     * @param fileName
     * @param obj
     */
    public static void saveObjectToFile(Context context, String fileName,
                                        Object obj) {
        try {
            FileOutputStream fos = context.openFileOutput(fileName,
                    Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(obj);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * 从文件中读取对象
     *
     * @param context
     * @param fileName
     * @return
     */
    public static Object loadObjectFromFile(Context context, String fileName) {
        Object obj = null;
        boolean isExit = true;
        String[] files = context.fileList();
        for (int i = 0; i < files.length; i++) {
            if (files[i].equals(fileName)) {
                isExit = false;
            }
        }
        if (!isExit) {
            FileInputStream fis = null;
            ObjectInputStream in = null;
            try {
                fis = context.openFileInput(fileName);
                in = new ObjectInputStream(fis);
                obj = in.readObject();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (OptionalDataException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return obj;
    }
}
