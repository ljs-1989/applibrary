package com.liujs.library.data;

import com.liujs.library.utils.FileUtil;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by liujs on 2016/8/29.
 */
public class FileOperator {

    /**
     * 写文件，默认编码：UTF-8
     * @param absolutePath 文件的绝对路径
     * @param data  数据源
     * @param isAdd  是否以追加的形式写进去
     * @return
     */
    public static boolean save(@NonNull String absolutePath, @NonNull String data,boolean isAdd){
        FileWriter fileWriter = null;
        File file = FileUtil.createFile(absolutePath);
        if(file!=null){
            try {
                 fileWriter = new FileWriter(file,isAdd);
                fileWriter.write(data);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }finally {
                try {
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return false;
    };

    /**
     * 读取文件
     * @param absolutePath
     * @return
     */
    public static String readFile(@NonNull String absolutePath){
        BufferedReader reader = null;
        StringBuffer readConten = new StringBuffer();
        try {
          if(FileUtil.isFileExist(absolutePath)){
              reader = new BufferedReader(new FileReader(absolutePath));
              String tempString = null;
              while ((tempString = reader.readLine())!=null){
                  readConten.append(tempString);
                  tempString = null;
              }
          }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return readConten.toString();
    }

    /**
     * 删除某个文件
     * @param filePath
     */
    public static void deleteFile(String filePath) {
        if (TextUtils.isEmpty(filePath))
            return;

        File file = new File(filePath);
        if (file != null && file.exists()) {
            file.delete();
            file = null;
        }
    }
}
