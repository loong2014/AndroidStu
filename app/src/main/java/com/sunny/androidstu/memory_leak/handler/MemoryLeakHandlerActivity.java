package com.sunny.androidstu.memory_leak.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.sunny.androidstu.R;
import com.sunny.baselib.activity.BaseActivity;
import com.sunny.baselib.time.MTimeUtils;

import java.lang.ref.WeakReference;

/**
 * Created by zhangxin17 on 2019/1/14
 */
public class MemoryLeakHandlerActivity extends BaseActivity {

    private TextView timeClockView;

    /* handler引起的内存泄漏
     解决方案:
        1. 使用static 生命Handler
        2. 清空Handler中的message
    */
    // 发生内存泄漏的Handler写法
//    private Handler mHandlerLeak = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == 1) {
//                updateTimeClock();
//            }
//        }
//    };

    // handler solve 1 使用static Handler和弱引用
    private static class MyHandler extends Handler {

        WeakReference<MemoryLeakHandlerActivity> memoryLeakActivity;

        public MyHandler(MemoryLeakHandlerActivity activity) {
            memoryLeakActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                MemoryLeakHandlerActivity activity = memoryLeakActivity.get();
                if (activity != null) {
                    activity.updateTimeClock();
                }
            }
        }
    }

    private MyHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_memory_leak);
        mHandler = new MyHandler(this);
        initView();
        startTimeClock();
    }

    private void initView() {
        timeClockView = findViewById(R.id.time_clock);
    }

    private void startTimeClock() {
        updateTimeClock();
    }

    private void updateTimeClock() {
        timeClockView.setText(MTimeUtils.getCurTimeYMDHMS());

        mHandler.sendEmptyMessageDelayed(1, 3 * 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // handler solve 2 退出时清空handler中的消息
        mHandler.removeMessages(1);
    }
}
