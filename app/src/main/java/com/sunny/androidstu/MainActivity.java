package com.sunny.androidstu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sunny.androidstu.app_icon.AppIconManager;
import com.sunny.androidstu.bilibili_danmaku.BiliDanmakuPlayerActivity;
import com.sunny.androidstu.fresco.FrescoActivity;
import com.sunny.androidstu.localVideo.LocalVideoActivity;
import com.sunny.androidstu.memory_leak.inner_class.MemoryLeakInnerClassActivity;
import com.sunny.androidstu.resize.SmallPlayerWindowChangedActivity;
import com.sunny.androidstu.view.DemoTextView;
import com.sunny.baselib.activity.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private DemoTextView demoTxView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        demoTxView = findViewById(R.id.demo_tx);

        findViewById(R.id.enter_memory_leak).setOnClickListener(this);
        findViewById(R.id.other_btn).setOnClickListener(this);
        findViewById(R.id.enter_video_player).setOnClickListener(this);
        findViewById(R.id.local_video_player).setOnClickListener(this);
        findViewById(R.id.fresco_lib).setOnClickListener(this);
        findViewById(R.id.small_window_changed).setOnClickListener(this);
        registerReceiver(StuApplication.getDemoReceiver(), StuApplication.getDemoIntentFliter());

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enter_memory_leak: {
                startActivity(new Intent(this, MemoryLeakInnerClassActivity.class));
                break;
            }
            case R.id.other_btn: {
                AppIconManager.getInstance().autoUpdateIcon();
                break;
            }
            case R.id.enter_video_player: {
                startActivity(new Intent(this, BiliDanmakuPlayerActivity.class));
                break;
            }

            case R.id.local_video_player: {
                startActivity(new Intent(this, LocalVideoActivity.class));
                break;
            }
            case R.id.fresco_lib: {
                startActivity(new Intent(this, FrescoActivity.class));
                break;
            }
            case R.id.small_window_changed: {
                startActivity(new Intent(this, SmallPlayerWindowChangedActivity.class));
                break;
            }

            default:
                break;
        }
    }
}
