package com.sunny.androidstu.view.wheel_view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sunny.androidstu.R;

/**
 * Created by zhangxin17 on 2019/1/7
 * 自定义竖直滑动选择器的装饰
 */
public class WheelViewDecoration extends RelativeLayout {

    private WheelView wheelView;

    private ImageView leftWidgetImg;
    private ImageView rightWidgetImg;

    public WheelViewDecoration(Context context) {
        this(context, null);
    }

    public WheelViewDecoration(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WheelViewDecoration(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {

        View rootView = LayoutInflater.from(context).inflate(R.layout.view_wheel_view_decoration, this, true);

        wheelView = rootView.findViewById(R.id.center_wheel_view);

        leftWidgetImg = rootView.findViewById(R.id.left_Widget_img);
        rightWidgetImg = rootView.findViewById(R.id.right_Widget_img);

        ViewGroup focusLayout = rootView.findViewById(R.id.wheel_focus_layout);
        focusLayout.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                wheelView.doOnFocusChange(hasFocus);

                if (hasFocus) {
                    WheelViewDecoration.this.setBackgroundColor(Color.GRAY);
                    v.setBackgroundColor(Color.YELLOW);
                } else {
                    WheelViewDecoration.this.setBackgroundColor(Color.WHITE);
                    v.setBackgroundColor(Color.GRAY);
                }
            }
        });

        focusLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                wheelView.doOnClick();
            }
        });

        focusLayout.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN || keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        wheelView.doOnKeyDown(keyCode);
                    } else if (event.getAction() == KeyEvent.ACTION_UP) {
                        wheelView.doOnKeyUp(keyCode);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void setLeftWidgetImg(int resId) {
        leftWidgetImg.setImageResource(resId);
    }

    public void setRightWidgetImg(int resId) {
        rightWidgetImg.setImageResource(resId);
    }

    public WheelView getWheelView() {
        return wheelView;
    }
}
