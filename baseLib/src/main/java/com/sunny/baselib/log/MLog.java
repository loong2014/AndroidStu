package com.sunny.baselib.log;

import android.util.Log;

/**
 * Created by zhangxin17 on 2019/1/4
 * 自定义日志打印
 */
public class MLog {

    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }
}
