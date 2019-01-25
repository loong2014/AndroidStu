package com.sunny.androidstu.view.round_angle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.sunny.baselib.MViewUtils;

/**
 * Created by zhangxin17 on 2019/1/24
 */
public class RoundAngleRelativeLayout extends RelativeLayout {

    public RoundAngleRelativeLayout(Context context) {
        super(context);
    }

    public RoundAngleRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundAngleRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Path path = new Path();
        path.addRoundRect(new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight()),
                MViewUtils.dp2px(16),
                MViewUtils.dp2px(16),
                Path.Direction.CW);
        canvas.clipPath(path, Region.Op.REPLACE);
        super.dispatchDraw(canvas);
    }
}
