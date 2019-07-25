package com.sunny.androidstu.resize;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.sunny.androidstu.R;
import com.sunny.baselib.activity.BaseActivity;

/**
 * Created by zhangxin17 on 2019/7/18
 */
public class SmallPlayerWindowChangedActivity extends BaseActivity {


    private View playViewLayout;

    private boolean isFullScreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_small_window_changed);

        playViewLayout = findViewById(R.id.play_layout);

        findViewById(R.id.change_to_full).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doChangedToFullScreen();
            }
        });
    }


    private void doChangedToFullScreen() {
        if (isFullScreen) {
            return;
        }
        isFullScreen = true;

        RelativeLayout.LayoutParams playLayoutParams = (RelativeLayout.LayoutParams) playViewLayout.getLayoutParams();
        if (playLayoutParams == null) {
            playLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        } else {
            playLayoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;
            playLayoutParams.height = RelativeLayout.LayoutParams.MATCH_PARENT;
        }
        playViewLayout.setLayoutParams(playLayoutParams);
    }


    private void doChangedToSmallScreen() {
        if (!isFullScreen) {
            return;
        }
        isFullScreen = false;
        RelativeLayout.LayoutParams playLayoutParams = (RelativeLayout.LayoutParams) playViewLayout.getLayoutParams();
        if (playLayoutParams == null) {
            playLayoutParams = new RelativeLayout.LayoutParams(600, 500);

        } else {
            playLayoutParams.width = 600;
            playLayoutParams.height = 500;
        }
        playViewLayout.setLayoutParams(playLayoutParams);
    }

    @Override
    public void onBackPressed() {
        if (isFullScreen) {
            doChangedToSmallScreen();
            return;
        }

        super.onBackPressed();
    }
}
