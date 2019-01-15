package com.sunny.baselib.applicatin;

import android.app.Activity;
import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.sunny.baselib.MContextUtils;

/**
 * Created by zhangxin17 on 2019/1/11
 */
public class BaseApplication extends Application {

    private static RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        MContextUtils.init(this);

        if (LeakCanary.isInAnalyzerProcess(this)) {
            refWatcher = RefWatcher.DISABLED;
        } else {
            refWatcher = LeakCanary.install(this);
        }
    }

    public static void doActWatch(Activity activity) {
        refWatcher.watch(activity);
    }

}
