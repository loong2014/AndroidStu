package com.sunny.androidstu.player.danmaku;

import com.sunny.baselib.MStringUtils;
import com.sunny.baselib.log.MLog;
import com.sunny.baselib.log.MLogTag;

import java.util.Random;

/**
 * Created by zhangxin17 on 2019/1/28
 */
public class DanmakuHelper {
    private static final String TAG = MLogTag.DANMAKU + "DanmakuHelper";

    private final DanmakuCallback mDanmakuCallback;
    private DanmakuSurfaceView mDanmakuSurfaceView;

    private boolean isFetching = false;

    public DanmakuHelper(DanmakuCallback callback) {
        mDanmakuCallback = callback;
    }

    public void doFetchDanmakuInfo() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mDanmakuCallback.onDanmakuInfoReady();
            }
        }).start();
    }

    public void startDanmakuRunning() {
        if (isFetching) {
            return;
        }
        isFetching = true;

        MLog.d(TAG, "startDanmakuRunning");
        getDanmakuSurfaceView().startShowing();
        new Thread(new Runnable() {
            @Override
            public void run() {

                Random random = new Random();

                DanmakuInfo info;
                while (isFetching) {
                    DanmakuSurfaceView view = getDanmakuSurfaceView();
                    if (view != null && view.isReady()) {
                        info = new DanmakuInfo();
                        info.setText(MStringUtils.getRandomStr());
                        info.setColor(MStringUtils.getRandomColor());
                        info.setSize(40);
                        info.setSpeed(3);

                        info.setPosX(view.getWidth());
                        int h = view.getHeight();
                        if (h > 0) {
                            info.setPosY(random.nextInt(h));
                        }
                        view.addDanmakuInfo(info);
                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void stopDanmakuRunning() {
        isFetching = false;

    }

    public void doRelease() {
        MLog.d(TAG, "doRelease");
        isFetching = false;
        getDanmakuSurfaceView().stopShowing();
    }

    public void setDanmakuSurfaceView(DanmakuSurfaceView view) {
        this.mDanmakuSurfaceView = view;
    }

    public DanmakuSurfaceView getDanmakuSurfaceView() {
        return mDanmakuSurfaceView;
    }


    public interface DanmakuCallback {
        void onDanmakuInfoReady();
    }
}
