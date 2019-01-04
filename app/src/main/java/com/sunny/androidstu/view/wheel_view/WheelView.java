package com.sunny.androidstu.view.wheel_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.sunny.androidstu.LogTagConfig;
import com.sunny.baselib.log.MLog;

/**
 * Created by zhangxin17 on 2019/1/4
 * 自定义竖直滑动选择器
 */
public class WheelView extends View {
    private static final String TAG = LogTagConfig.VIEW + "WheelView";

    public WheelView(Context context) {
        super(context);
        initView(context);
    }

    public WheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public WheelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        MLog.i(TAG, "initView");
    }

}
