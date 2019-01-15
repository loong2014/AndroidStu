package com.sunny.baselib.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.sunny.baselib.applicatin.BaseApplication;
import com.sunny.baselib.log.MLog;
import com.sunny.baselib.log.MLogTag;

/**
 * Created by zhangxin17 on 2019/1/11
 */
public class BaseActivity extends Activity {

    private final String BASE_TAG = MLogTag.ACT_LIFE_CYCLE_TAG + this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MLog.i(BASE_TAG, "onCreate");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        MLog.i(BASE_TAG, "onNewIntent");
    }


    @Override
    protected void onStart() {
        super.onStart();
        MLog.i(BASE_TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        MLog.i(BASE_TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        MLog.i(BASE_TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        MLog.i(BASE_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MLog.i(BASE_TAG, "onDestroy");
        BaseApplication.doActWatch(this);
    }
}
