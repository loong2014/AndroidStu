package com.sunny.androidstu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sunny.androidstu.app_icon.AppIconManager;
import com.sunny.androidstu.memory_leak.inner_class.MemoryLeakInnerClassActivity;
import com.sunny.baselib.activity.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.enter_memory_leak).setOnClickListener(this);
        findViewById(R.id.other_btn).setOnClickListener(this);


    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enter_memory_leak: {
                Intent intent = new Intent(this, MemoryLeakInnerClassActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.other_btn: {
                AppIconManager.getInstance().autoUpdateIcon();
                break;
            }
            default:
                break;
        }
    }
}
