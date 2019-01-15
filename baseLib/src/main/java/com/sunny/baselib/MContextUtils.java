package com.sunny.baselib;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

/**
 * Created by zhangxin17 on 2019/1/14
 */
public class MContextUtils {

    private static Context sAppContext;

    private static Resources sResources;

    public static void init(Application application) {
        sAppContext = application;
        sResources = sAppContext.getResources();
    }

    public static Context getAppContext() {
        if (sAppContext == null) {
            throw new NullPointerException("sAppContext is null");
        }
        return sAppContext;
    }

    public static int getColorResId(int colorId) {
        return sResources.getColor(colorId);
    }
}
