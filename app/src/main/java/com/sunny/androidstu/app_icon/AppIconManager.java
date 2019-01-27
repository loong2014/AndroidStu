package com.sunny.androidstu.app_icon;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

import com.sunny.baselib.MContextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by zhangxin17 on 2019/1/27
 * 应用图标管理
 */
public class AppIconManager {


    private static final String DefDateTimeFormat = "yyyy-MM-dd hh:mm";

    private static final String DefLauncherActivityPath = "com.sunny.androidstu.MainActivity";
    private static final String AliasLauncherActivityPath1 = "com.sunny.androidstu.LauncherActivity1";
    private static final String AliasLauncherActivityPath2 = "com.sunny.androidstu.LauncherActivity2";
    private static final String AliasLauncherActivityPath3 = "com.sunny.androidstu.LauncherActivity3";

    public enum LauncherIconType {
        ALIAS1("bulldozer", "2019-01-06 00:00", "2019-02-04 00:00", AliasLauncherActivityPath1),
        ALIAS2("robot", "2019-02-04 00:00", "2019-02-11 00:00", AliasLauncherActivityPath2),
        ALIAS3("soldier", "2019-02-11 00:00", "2019-02-19 00:00", AliasLauncherActivityPath3),
        DEF("def", "1970-01-01 00:00", "2200-01-01 00:00", DefLauncherActivityPath),
        ;

        final String name;
        final String actPath;
        final String validTimeStartStr;
        final String validTimeEndStr;
        final long validTimeStart;
        final long validTimeEnd;

        LauncherIconType(String name, String validTimeStartStr, String validTimeEndStr, String actPath) {
            this.name = name;
            this.validTimeStartStr = validTimeStartStr;
            this.validTimeEndStr = validTimeEndStr;
            this.actPath = actPath;

            this.validTimeStart = strTime2Long(validTimeStartStr);
            this.validTimeEnd = strTime2Long(validTimeEndStr);
        }

        public boolean isContain(long time) {
            return time >= validTimeStart && time < validTimeEnd;
        }
    }

    private static AppIconManager sAppIconManager = null;

    public static AppIconManager getInstance() {

        if (sAppIconManager == null) {
            sAppIconManager = new AppIconManager();
        }
        return sAppIconManager;
    }

    private AppIconManager() {
    }

    public void autoUpdateIcon() {
        long curTimeMs = System.currentTimeMillis();
        LauncherIconType showType = getIconTypeByTime(curTimeMs);
        updateIcon(showType);
    }

    private LauncherIconType getIconTypeByTime(long time) {

        LauncherIconType[] list = LauncherIconType.values();
        for (LauncherIconType iconType : list) {
            if (iconType.isContain(time)) {
                return iconType;
            }
        }

        return LauncherIconType.DEF;
    }

    /**
     * 通过系统别名的方式实现应用图标替换
     */
    public void updateIcon(LauncherIconType showType) {

        Context context = MContextUtils.getAppContext();
        PackageManager pm = context.getPackageManager();

        LauncherIconType[] list = LauncherIconType.values();
        for (LauncherIconType type : list) {
            if (showType == type) {
                pm.setComponentEnabledSetting(new ComponentName(context, type.actPath),
                        PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

            } else {
                pm.setComponentEnabledSetting(new ComponentName(context, type.actPath),
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
            }
        }
    }


    private static long strTime2Long(String timeStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(DefDateTimeFormat);
        try {
            return sdf.parse(timeStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
