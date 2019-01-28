package com.sunny.androidstu.simple_danmaku;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.MediaController;
import android.widget.VideoView;

import com.sunny.androidstu.R;
import com.sunny.baselib.activity.BaseActivity;

import java.lang.ref.WeakReference;

/**
 * Created by zhangxin17 on 2019/1/28
 * 视频播放
 */
public class SimpleDanmakuPlayerActivity extends BaseActivity {

    private static final int MSG_VIDEO_START = 1;
    private static final int MSG_DANMAKU_START = 2;
    private static final int MSG_DANMAKU_STOP = 3;


    //
    private static final Uri videoUri = Uri.parse("http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4");
    private VideoView mVideoView;
    private boolean isVideoStart = false;


    //
    private SimpleDanmakuHelper mDanmakuHelper;

    private VideoHandler mVideoHandler;

    private static class VideoHandler extends Handler {

        private WeakReference<SimpleDanmakuPlayerActivity> actWeakReference;

        VideoHandler(SimpleDanmakuPlayerActivity activity) {
            actWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            SimpleDanmakuPlayerActivity activity = actWeakReference.get();
            if (activity == null) {
                return;
            }

            switch (msg.what) {
                case MSG_VIDEO_START:
                    activity.startVideoPlay();
                    break;

                case MSG_DANMAKU_START:
                    activity.mDanmakuHelper.startDanmakuRunning();
                    break;
                case MSG_DANMAKU_STOP:
                    activity.mDanmakuHelper.stopDanmakuRunning();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_simple_danmaku_player);
        mVideoHandler = new VideoHandler(this);
        initVideoView();

        initDanmakuView();

        mVideoHandler.sendEmptyMessage(MSG_VIDEO_START);
    }

    private void initDanmakuView() {
        SimpleDanmakuSurfaceView danmakuSurfaceView = findViewById(R.id.danmaku_view);

        mDanmakuHelper = new SimpleDanmakuHelper(mDanmakuCallback);
        mDanmakuHelper.setDanmakuSurfaceView(danmakuSurfaceView);

        mDanmakuHelper.doFetchDanmakuInfo();
    }

    private SimpleDanmakuHelper.DanmakuCallback mDanmakuCallback = new SimpleDanmakuHelper.DanmakuCallback() {
        @Override
        public void onDanmakuInfoReady() {
        }
    };

    private void initVideoView() {
        mVideoView = findViewById(R.id.video_view);
    }

    private void startVideoPlay() {
        if (isVideoStart) {
            return;
        }

        isVideoStart = true;
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.setVideoURI(videoUri);

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mVideoHandler.sendEmptyMessage(MSG_DANMAKU_STOP);
            }
        });

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mVideoHandler.sendEmptyMessage(MSG_DANMAKU_START);
            }
        });
        mVideoView.start();
    }

    private void stopVideoPlay() {
        if (isVideoStart && mVideoView != null) {
            mVideoView.stopPlayback();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopVideoPlay();
        mDanmakuHelper.doRelease();
        mVideoHandler.removeCallbacksAndMessages(null);
    }
}
