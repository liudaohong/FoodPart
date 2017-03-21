package com.qf.kanfang.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.qf.kanfang.App;

/**
 * Created by Administrator on 2017/1/16.
 */
public class SharedUtils {
    //定义共享参数文件 名
    private static final String FILENAME = "shared_utils";

    //第一次启动
    private static final String KEY_FIRSTRUN = "firstRun";


    /**
     * 第一次启动到主界面时，写入一个false的值
     */
    public static void saveFirstRun() {
        //得到shard
        SharedPreferences shared = App.getApp().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        shared.edit().putBoolean(KEY_FIRSTRUN, false).commit();
    }

    /**是否是第一次启动
     * @return
     */
    public static boolean isFirstRun() {
        //得到shard
        SharedPreferences shared = App.getApp().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return shared.getBoolean(KEY_FIRSTRUN, true);
    }

}
