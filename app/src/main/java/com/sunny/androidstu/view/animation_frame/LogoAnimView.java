package com.sunny.androidstu.view.animation_frame;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;

import com.sunny.androidstu.R;

/**
 * Created by zhangxin17 on 2019/1/8
 */
public class LogoAnimView extends RelativeLayout {

    private static final String HY_LOGO_ANIM_PNG_PREFIX_NAME = "hy_logo_anim_";

    private static final int HY_LOGO_ANIM_FRAME_COUNT = 65;
    private static final int HY_LOGO_ANIM_FRAME_DURATION = 40;

    private Context mContext;
    private View logoBottomView;
    private View logoAnimView;

    private static final int MSG_LOGO_ANIM_STEP_1 = 2;
    private static final int MSG_LOGO_ANIM_STEP_2 = 3;
    private static final int MSG_LOGO_ANIM_STEP_3 = 4;
    private static final int MSG_LOGO_ANIM_STEP_4 = 5;
    private static final int MSG_LOGO_ANIM_UPDATE_FRAME = 6;
    private static final int MSG_LOGO_ANIM_STEP_FINISH = 7;

    private LogoAnimHandler animHandler = new LogoAnimHandler();

    private boolean noAnim = false;
    private boolean animStopped = true; // 动画停止

    private int[] animFrameList = null;

    public LogoAnimView(Context context) {
        this(context, null);
    }

    public LogoAnimView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LogoAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_logo_anim_layout, this, true);
        logoBottomView = rootView.findViewById(R.id.bottom_layer_img);
        logoAnimView = rootView.findViewById(R.id.anim_img);
    }

    private class LogoAnimHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            if (noAnim || animStopped) {
                return;
            }

            switch (msg.what) {

                // step1 hy-def 消失 1s
                case MSG_LOGO_ANIM_STEP_1:

                    logoAnimView.setVisibility(GONE);
                    logoAnimView.setBackgroundResource(R.mipmap.hy_logo_huaying);
                    doAlphaAnim(logoAnimView, false, false, 1000, new AnimatorListenerImpl() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            logoAnimView.setVisibility(VISIBLE);
                            logoBottomView.setVisibility(GONE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            // 停留1s后执行——step2
                            animHandler.sendEmptyMessageDelayed(MSG_LOGO_ANIM_STEP_2, 1000);
                        }
                    });
                    break;

                // step2 letv 可见 1s
                case MSG_LOGO_ANIM_STEP_2:

                    logoAnimView.setVisibility(GONE);
                    logoAnimView.setBackgroundResource(R.mipmap.hy_logo_letv);
                    doAlphaAnim(logoAnimView, false, true, 1000, new AnimatorListenerImpl() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            logoAnimView.setVisibility(VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            // 停留1s后执行——step3
                            animHandler.sendEmptyMessageDelayed(MSG_LOGO_ANIM_STEP_3, 1000);
                        }
                    });
                    break;

                // step3 帧动画 2.6s
                case MSG_LOGO_ANIM_STEP_3:

                    initAnimFrameList();

                    logoAnimView.setVisibility(VISIBLE);
                    sendEmptyMessage(MSG_LOGO_ANIM_UPDATE_FRAME);
                    break;

                // 刷新显示frame
                case MSG_LOGO_ANIM_UPDATE_FRAME:
                    int index = animFrameList[0];

                    // 显示下一帧
                    if (index <= HY_LOGO_ANIM_FRAME_COUNT) {
                        animFrameList[0] = index + 1;
                        sendEmptyMessageDelayed(MSG_LOGO_ANIM_UPDATE_FRAME, HY_LOGO_ANIM_FRAME_DURATION);
                        logoAnimView.setBackgroundResource(animFrameList[index]);
                    }

                    // 帧动画结束
                    else {
                        logoAnimView.setBackgroundResource(R.mipmap.hy_logo_huaying_tint);

                        // 停留1.6s后执行——step4
                        animHandler.sendEmptyMessageDelayed(MSG_LOGO_ANIM_STEP_4, 1600);
                    }
                    break;

                // step4 top hy-tint 消失 2s，bottom hy-def 可见
                case MSG_LOGO_ANIM_STEP_4:

                    logoAnimView.setBackgroundResource(R.mipmap.hy_logo_huaying_tint);
                    doAlphaAnim(logoAnimView, true, false, 2000, new AnimatorListenerImpl() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            logoBottomView.setVisibility(VISIBLE);
                            logoAnimView.setVisibility(VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            logoAnimView.setVisibility(GONE);
                            logoBottomView.setVisibility(VISIBLE);

                            // 停留5.8s后动画结束
                            animHandler.sendEmptyMessageDelayed(MSG_LOGO_ANIM_STEP_FINISH, 5800);
                        }
                    });
                    break;

                // 动画结束，开始下一个循环
                case MSG_LOGO_ANIM_STEP_FINISH:
                    sendEmptyMessage(MSG_LOGO_ANIM_STEP_1);
                    break;

                default:
                    break;
            }
        }
    }


    public void startAnim() {
        if (noAnim) {
            return;
        }
        if (!animStopped) {
            return;
        }
        animStopped = false;

        // 间隔1s后执行——step1
        animHandler.sendEmptyMessageDelayed(MSG_LOGO_ANIM_STEP_1, 1000);
    }

    public void stopAnim() {
        if (noAnim) {
            return;
        }
        animStopped = true;

        logoAnimView.clearAnimation();
        logoBottomView.setVisibility(VISIBLE);
        logoAnimView.setVisibility(GONE);
    }

    private void doAlphaAnim(View view, boolean useAcc, boolean isShown, int duration, Animator.AnimatorListener listener) {

        ObjectAnimator alphaAnim;
        if (isShown) {
            alphaAnim = ObjectAnimator.ofFloat(view, "alpha", 0F, 1F);
        } else {
            alphaAnim = ObjectAnimator.ofFloat(view, "alpha", 1F, 0F);
        }
        alphaAnim.setDuration(duration);
        if (useAcc) {
            alphaAnim.setInterpolator(new AccelerateInterpolator());
        } else {
            alphaAnim.setInterpolator(new LinearInterpolator());
        }
        alphaAnim.addListener(listener);
        alphaAnim.start();
    }

    private void initAnimFrameList() {
        if (animFrameList == null) {
            animFrameList = new int[HY_LOGO_ANIM_FRAME_COUNT + 1];

            Resources res = mContext.getResources();
            String pkgName = mContext.getPackageName();
            for (int i = 1; i <= HY_LOGO_ANIM_FRAME_COUNT; i++) {
                animFrameList[i] = res.getIdentifier(HY_LOGO_ANIM_PNG_PREFIX_NAME + i, "mipmap", pkgName);
            }
        }
        animFrameList[0] = 1;
    }

    private class AnimatorListenerImpl implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }
}
