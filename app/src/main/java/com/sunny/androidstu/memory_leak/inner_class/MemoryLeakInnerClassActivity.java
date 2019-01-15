package com.sunny.androidstu.memory_leak.inner_class;

import android.os.Bundle;

import com.sunny.androidstu.R;
import com.sunny.baselib.activity.BaseActivity;

/**
 * Created by zhangxin17 on 2019/1/14
 */
public class MemoryLeakInnerClassActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_memory_leak);


        new Thread(run1).start();
    }

    private Runnable run1 = new MyRunnable();

//    /**
//     * 内部类会持有当前activity的引用
//     * 如果该内部类被异步线程引用，则会引起内存泄漏
//     */
//    private Runnable run2 = new Runnable() {
//        @Override
//        public void run() {
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
//    };

    private class MyRunnable implements Runnable {

        @Override
        public void run() {

            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            run2.run();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
