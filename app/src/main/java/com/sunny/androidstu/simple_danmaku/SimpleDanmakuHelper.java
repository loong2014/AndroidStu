package com.sunny.androidstu.simple_danmaku;

import com.sunny.baselib.MStringUtils;
import com.sunny.baselib.log.MLog;
import com.sunny.baselib.log.MLogTag;

import java.util.Random;

/**
 * Created by zhangxin17 on 2019/1/28
 */
public class SimpleDanmakuHelper {
    private static final String TAG = MLogTag.DANMAKU + "SimpleDanmakuHelper";

    private final DanmakuCallback mDanmakuCallback;
    private SimpleDanmakuSurfaceView mDanmakuSurfaceView;

    private boolean isFetching = false;

    public SimpleDanmakuHelper(DanmakuCallback callback) {
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

                SimpleDanmakuInfo info;
                while (isFetching) {
                    SimpleDanmakuSurfaceView view = getDanmakuSurfaceView();
                    if (view != null && view.isReady()) {
                        info = new SimpleDanmakuInfo();
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

    public void setDanmakuSurfaceView(SimpleDanmakuSurfaceView view) {
        this.mDanmakuSurfaceView = view;
    }

    public SimpleDanmakuSurfaceView getDanmakuSurfaceView() {
        return mDanmakuSurfaceView;
    }


    public interface DanmakuCallback {
        void onDanmakuInfoReady();
    }
}
