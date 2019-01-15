package com.sunny.androidstu.memory_leak.adapter;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sunny.androidstu.R;
import com.sunny.baselib.MStringUtils;
import com.sunny.baselib.activity.BaseActivity;
import com.sunny.baselib.log.MLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxin17 on 2019/1/14
 */
public class MemoryLeakAdapterActivity extends BaseActivity {

    private static final String TAG = "MemoryLeakAdapterActivity";
    private ListView mRandomStrList;
    private RandomAdapter mRandomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_memory_leak);

        initView();

        initListViewData();
    }


    private Runnable run1 = new MyRunnable();

    private Runnable run2 = new Runnable() {
        @Override
        public void run() {

        }
    };


    private Runnable run3 = new Runnable() {
        @Override
        public void run() {

        }
    };

    private Runnable run4 = new Runnable() {
        @Override
        public void run() {

        }
    };

    private class MyRunnable implements Runnable {

        @Override
        public void run() {

        }
    }

    private void initView() {
        mRandomStrList = findViewById(R.id.random_str_list);
        mRandomAdapter = new RandomAdapter();
        mRandomStrList.setAdapter(mRandomAdapter);
        mRandomStrList.setOnItemClickListener(randomItemClickListener);

        mRandomStrList.setOnItemSelectedListener(randomItemSelectedListener);
    }

    private AdapterView.OnItemClickListener randomItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String itemStr = mRandomAdapter.getItem(position);
            MLog.i(TAG, "onItemClick  itemStr :" + itemStr);
        }
    };

    private AdapterView.OnItemSelectedListener randomItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            RandomAdapter.ViewHolder holder = (RandomAdapter.ViewHolder) view.getTag();
            holder.nameView.setTextColor(Color.RED);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void initListViewData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(MStringUtils.getRandomStr());
        }

        mRandomAdapter.setList(list);
    }
}
