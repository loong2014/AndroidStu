package com.sunny.androidstu;

import android.content.Intent;
import android.content.IntentFilter;

import com.sunny.androidstu.fresco.FrescoConfig;
import com.sunny.androidstu.receiver.DemoReceiver;
import com.sunny.baselib.applicatin.BaseApplication;

/**
 * Created by zhangxin17 on 2019/1/11
 */
public class StuApplication extends BaseApplication {

    private static DemoReceiver demoReceiver;
    private static IntentFilter intentFilter;

    @Override
    public void onCreate() {
        super.onCreate();
        FrescoConfig.init(this);
    }

    public static DemoReceiver getDemoReceiver() {
        if (demoReceiver == null) {
            demoReceiver = new DemoReceiver();
        }
        return demoReceiver;
    }

    public static IntentFilter getDemoIntentFliter(){
        if (intentFilter ==null){
            intentFilter = new IntentFilter();
            intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        }
        return intentFilter;
    }
}
