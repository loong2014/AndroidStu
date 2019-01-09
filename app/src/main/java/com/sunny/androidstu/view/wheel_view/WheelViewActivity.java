package com.sunny.androidstu.view.wheel_view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.sunny.androidstu.LogTagConfig;
import com.sunny.androidstu.R;
import com.sunny.baselib.log.MLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WheelViewActivity extends Activity {
    private static final String TAG = LogTagConfig.VIEW + "MainActivity";

    private LinearLayout mWheelViewLayout;

    private static final int defWheelViewIndex1 = 100;
    private static final int defWheelViewIndex2 = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_wheel_view);

        mWheelViewLayout = findViewById(R.id.wheelViewLayout);


        WheelViewDecoration wheelView1 = buildWheelViewDecoration(this, 11111, defWheelViewIndex1);
        mWheelViewLayout.addView(wheelView1);

        WheelViewDecoration wheelView2 = buildWheelViewDecoration(this, 22222, defWheelViewIndex2);
        mWheelViewLayout.addView(wheelView2);
    }


    private WheelViewDecoration buildWheelViewDecoration(Context context, final int type, int defIndex) {
        final WheelViewDecoration wheelViewDecoration = new WheelViewDecoration(context);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, 300);
        params.setMargins(5, 5, 5, 5);
        wheelViewDecoration.setLayoutParams(params);

        final WheelView wheelView = wheelViewDecoration.getWheelView();
        initWheelViewData(type, wheelView, defIndex);

        wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onFocusChange(boolean hasFocus) {

                MLog.i(TAG, type + "  onFocusChange  hasFocus :" + hasFocus);
            }

            @Override
            public void onClick(WheelViewItem item, int index) {
                MLog.i(TAG, type + "  onClick  index :" + index + " , item :" + item);
                wheelView.setDefItemIndex(index);
            }

            @Override
            public void onSelect(WheelViewItem item, int index) {
                MLog.i(TAG, type + "  onSelect  index :" + index + " , item :" + item);
            }

        });
        return wheelViewDecoration;
    }

    private WheelView buildWheelView(Context context, final int type, int defIndex) {
        final WheelView wheelView = new WheelView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, 300);
        params.setMargins(5, 5, 5, 5);
        wheelView.setLayoutParams(params);
        wheelView.setBackgroundColor(Color.GRAY);
        wheelView.setFocusable(true);

        initWheelViewData(type, wheelView, defIndex);

        wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onFocusChange(boolean hasFocus) {

                MLog.i(TAG, type + "  onFocusChange  hasFocus :" + hasFocus);
            }

            @Override
            public void onClick(WheelViewItem item, int index) {
                MLog.i(TAG, type + "  onClick  index :" + index + " , item :" + item);
                wheelView.setDefItemIndex(index);
            }

            @Override
            public void onSelect(WheelViewItem item, int index) {
                MLog.i(TAG, type + "  onSelect  index :" + index + " , item :" + item);
            }

        });
        return wheelView;
    }

    private void initWheelViewData(int type, WheelView wheelView, int defIndex) {

        List<WheelViewItem> list = new ArrayList<>();
        WheelViewItem item;
        for (int i = 0; i < 10; i++) {
            item = new WheelViewItem();
            item.setType(type);
            item.setShowName(getRandomStr() + "-" + i);
            list.add(item);
        }

        wheelView.setWheelViewItemList(list, defIndex);
    }

    public static String getRandomStr() {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        int randomNum;
        char randomChar;
        Random random = new Random();
        // StringBuffer类型的可以append增加字符
        StringBuffer str = new StringBuffer();

        int len = random.nextInt(10);
        for (int i = 0; i < len; i++) {
            // 可生成[0,n)之间的整数，获得随机位置
            randomNum = random.nextInt(base.length());
            // 获得随机位置对应的字符
            randomChar = base.charAt(randomNum);
            // 组成一个随机字符串
            str.append(randomChar);
        }
        return str.toString();
    }

}
