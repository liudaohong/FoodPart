package com.qf.kanfang.utils;

import android.util.Log;

/**
 * 打印工具类
 */
public class L {
    /**
     * Log开关
     */
    private static final boolean DEBUG = true;
    /**
     * 默认的Tag
     */
    private static final String TAG = "ytmfdw";

    public static void d(String str) {
        if (DEBUG) {
            Log.d(TAG, str);
        }
    }

    public static void d(String tag, String str) {
        if (DEBUG) {
            Log.d(tag, str);
        }
    }
}
