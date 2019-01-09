package com.sunny.androidstu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.sunny.androidstu.view.animation_frame.LogoAnimView;
import com.sunny.baselib.log.MLog;

public class MainActivity extends Activity {
    private static final String TAG = LogTagConfig.VIEW + "MainActivity";


    private LogoAnimView logoAnimView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logoAnimView = findViewById(R.id.hy_logo_anim_view);

        findViewById(R.id.start_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doStartLogoAnim();
            }
        });

        findViewById(R.id.stop_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doStopLogoAnim();
            }
        });
    }

    private void doStartLogoAnim() {
        MLog.i(TAG, "doStartLogoAnim");
        logoAnimView.startAnim();
    }

    private void doStopLogoAnim() {
        MLog.i(TAG, "doStopLogoAnim");
        logoAnimView.stopAnim();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (logoAnimView != null) {
            logoAnimView.stopAnim();
        }
    }
}