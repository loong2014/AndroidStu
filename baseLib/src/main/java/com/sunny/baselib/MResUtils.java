package com.sunny.baselib;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;

/**
 * Created by zhangxin17 on 2019/7/16
 */
public class MResUtils {

    private static Context sContext;

    public static Context getContext() {
        if (sContext == null) {
            sContext = MContextUtils.getAppContext();
        }
        return sContext;
    }

    private static Resources getResources() {
        return getContext().getResources();
    }


    public static Drawable getDrawableByResId(int resId) {
        return getResources().getDrawable(resId);
    }

    public static String getStringByResId(int resId) {
        return getResources().getString(resId);
    }

    public static String getStringByResId(int resId, Object... formatArgs) {
        return getResources().getString(resId, formatArgs);
    }

    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    public static String getString(int resId, Object... formatArgs) {
        return getResources().getString(resId, formatArgs);
    }

    public static int getColorByResId(int resId) {
        return getResources().getColor(resId);
    }

    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }


    public static int getDimensionPixelSize(int resId) {
        if (resId <= 0) {
            return 0;
        }
        return getResources().getDimensionPixelSize(resId);
    }

    public static float getDimension(int resId) {
        if (resId <= 0) {
            return 0;
        }
        return getResources().getDimension(resId);
    }

    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    public static Uri getUriByResId(int resId) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + getResources().getResourcePackageName(resId) + "/"
                + getResources().getResourceTypeName(resId) + "/"
                + getResources().getResourceEntryName(resId));
    }

}
