package com.sunny.androidstu.localVideo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sunny.androidstu.R;
import com.sunny.baselib.activity.BaseActivity;
import com.sunny.baselib.log.MLog;

/**
 * Created by zhangxin17 on 2019/3/23
 */
public class LocalVideoActivity extends BaseActivity {

    private static final String TAG = "LocalVideoActivity";

    private MediaPlayer mediaPlayer;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    private ImageView mImageView;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_local_video);

        surfaceView = findViewById(R.id.video_sv);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(callback);
        mediaPlayer = MediaPlayer.create(this, R.raw.local_video_bear);


        mImageView = findViewById(R.id.video_bg);
//        Glide.with(this).load(R.raw.timg).into(mImageView);

        findViewById(R.id.startPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startVideo();
            }
        });
    }

    private SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            mediaPlayer.setDisplay(holder);
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    surfaceView.setVisibility(View.GONE);
                }
            }, 2000);

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {

                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    MLog.d(TAG, "onCompletion");

                    surfaceView.setVisibility(View.GONE);
//                    mp.seekTo(0);
//                    mp.start();
                }
            });
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    };

    private void releaseVideo() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseVideo();
    }
}
