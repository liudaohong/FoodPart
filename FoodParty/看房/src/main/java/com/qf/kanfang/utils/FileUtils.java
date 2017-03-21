package com.qf.kanfang.utils;

import android.content.Context;

import com.qf.kanfang.App;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/1/17.
 */
public class FileUtils {
    /**
     * 把字符串缓存到指定的文件 名中
     * <p/>
     * 保存到内部存储中
     *
     * @param value
     * @param fileName
     */
    public static void saveString(String value, String fileName) {
        //得到文件，转换成OutputStream
        try {
            FileOutputStream out = App.getApp().openFileOutput(fileName, Context.MODE_PRIVATE);
            //把字符串写入到out
            out.write(value.getBytes("utf-8"));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取指定文件 ，转换成 String
     *
     * @param fileName
     * @return
     */
    public static String readString(String fileName) {
        try {
            FileInputStream in = App.getApp().openFileInput(fileName);
            //in-->String
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
            StringBuilder sb = new StringBuilder();
            String tmp = null;
            while ((tmp = reader.readLine()) != null) {
                sb.append(tmp);
            }
            reader.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
