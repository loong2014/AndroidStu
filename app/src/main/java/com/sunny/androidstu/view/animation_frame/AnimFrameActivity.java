package com.sunny.androidstu.view.animation_frame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.sunny.baselib.log.MLogTag;
import com.sunny.androidstu.R;
import com.sunny.baselib.log.MLog;

public class AnimFrameActivity extends Activity {
    private static final String TAG = MLogTag.VIEW + "MainActivity";


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
