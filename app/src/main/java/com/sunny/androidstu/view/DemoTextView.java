package com.sunny.androidstu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.sunny.baselib.log.MLog;

/**
 * Created by zhangxin17 on 2019/2/21
 */
public class DemoTextView extends TextView {
    public DemoTextView(Context context) {
        super(context);
    }

    public DemoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DemoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        MLog.d("DemoTV", "setText 11111 :" + text + " , " + isLayoutRequested());

        super.setText(text, type);
        MLog.d("DemoTV", "setText 22222 :" + text);
    }

    @Override
    public void requestLayout() {

        MLog.d("DemoTV", "requestLayout  11111  " + isLayoutRequested() + " , " + getParent());

        super.requestLayout();
        MLog.d("DemoTV", "requestLayout  22222");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        MLog.d("DemoTV", "DemoTextView  onLayout");

    }
}
