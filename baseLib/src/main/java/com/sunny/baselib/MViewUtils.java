package com.sunny.baselib;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by zhangxin17 on 2019/1/24
 */
public class MViewUtils {
    private static Context sContext;
    private static Resources sRes;

    private static float sDensity;

    public static void init(Context context) {
        sContext = context;
        sRes = context.getResources();
        sDensity = sRes.getDisplayMetrics().density;
    }

    public static int dp2px(float dpValue) {
        return (int) (dpValue * sDensity + 0.5F);
    }

    public static float px2dp(int pxValue) {
        return pxValue / sDensity + 0.5F;
    }
}
