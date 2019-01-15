package com.sunny.androidstu.view.wheel_view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.sunny.androidstu.R;
import com.sunny.baselib.MStringUtils;
import com.sunny.baselib.log.MLog;
import com.sunny.baselib.log.MLogTag;

import java.util.ArrayList;
import java.util.List;

public class WheelViewActivity extends Activity {
    private static final String TAG = MLogTag.VIEW + "MainActivity";

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
            item.setShowName(MStringUtils.getRandomStr() + "-" + i);
            list.add(item);
        }

        wheelView.setWheelViewItemList(list, defIndex);
    }


}
