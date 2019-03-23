package com.sunny.androidstu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.sunny.baselib.log.MLog;

/**
 * Created by zhangxin17 on 2019/2/21
 */
public class DemoRelativeLayout extends RelativeLayout {
    public DemoRelativeLayout(Context context) {
        super(context);
    }

    public DemoRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DemoRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        MLog.d("DemoTV", "DemoRelativeLayout  onLayout");

    }
}
