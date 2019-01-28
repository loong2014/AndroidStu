package com.sunny.androidstu.player.danmaku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.sunny.baselib.log.MLog;
import com.sunny.baselib.log.MLogTag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxin17 on 2019/1/28
 * 弹幕显示控件
 */
public class DanmakuSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = MLogTag.DANMAKU + "DanmakuSurfaceView";

    private SurfaceHolder mSurfaceHolder;

    private List<DanmakuInfo> mDanmakuInfoList = new ArrayList<>();

    private boolean isReady = false; // 是否准备好了
    private boolean isRunning = false; // 是否开始运行

    public DanmakuSurfaceView(Context context) {
        this(context, null);
    }

    public DanmakuSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DanmakuSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);

        // 背景透明
        setZOrderOnTop(true);
        mSurfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
    }

    public boolean isReady() {
        return isReady;
    }

    public void addDanmakuInfo(DanmakuInfo info) {
        mDanmakuInfoList.add(info);
    }

    public void startShowing() {
        MLog.d(TAG, "startShowing");
        isRunning = true;
        startRefreshRunnable();
    }

    public void stopShowing() {
        isRunning = false;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        MLog.d(TAG, "surfaceCreated");
        isReady = true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isReady = false;
    }

    private void startRefreshRunnable() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                // 弹幕绘制
                Canvas canvas = null;
                DanmakuInfo info = null;
                Paint paint = null;

                MLog.d(TAG, "doRefreshCanvas  isReady :" + isReady + " , isRunning :" + isRunning);
                while (isRunning) {
                    try {
                        Thread.sleep(5);


                        if (!isReady() || mDanmakuInfoList.isEmpty()) {
                            continue;
                        }

                        // 获取画布
                        canvas = mSurfaceHolder.lockCanvas();

                        // 清空画布
                        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);


                        // 遍历弹幕集合
                        // 异步，动态获取集合大小
                        for (int i = 0; i < mDanmakuInfoList.size(); i++) {
                            info = mDanmakuInfoList.get(i);

                            // 生产画笔
                            paint = new Paint();
                            paint.setColor(info.getColor());
                            paint.setTextSize(info.getSize());
                            paint.setStrokeWidth(3F);

                            // 绘制文本
                            canvas.drawText(info.getText(), info.getPosX(), info.getPosY(), paint);

                            // 更新弹幕位置
                            if (info.getPosX() <= 0) {
                                mDanmakuInfoList.remove(info);
                            } else {
                                info.setPosX(info.getPosX() - info.getSpeed());
                            }
                        }

                        // 解锁画布
                        mSurfaceHolder.unlockCanvasAndPost(canvas);

                    } catch (Exception e) {
                        e.printStackTrace();
                        isRunning = false;
                        MLog.d(TAG, "doRefreshCanvas error :" + e);
                        break;
                    }
                }
            }
        }).start();
    }

}
