package com.sunny.androidstu.view.wheel_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

import com.sunny.baselib.log.MLogTag;
import com.sunny.baselib.log.MLog;

import java.lang.ref.WeakReference;
import java.security.InvalidParameterException;
import java.util.List;

/**
 * Created by zhangxin17 on 2019/1/4
 * 自定义竖直滑动选择器
 * 参考：https://blog.csdn.net/angrysword/article/details/79837696
 */
public class WheelView extends View {
    private String TAG = MLogTag.VIEW + "WheelView";

    private List<WheelViewItem> mItemList;
    private int mSelectedItemIndex = 0;
    private int mDefItemIndex = 0;

    private int viewWidth;
    private int viewHeight;

    private float itemHeight = 0;
    private int itemNumber = 5;
    private int halfItemNumber = itemNumber / 2;

    private boolean resetDefItemAfterLoseFocus = true;
    private boolean hasFocus = false;

    // paint
    private TextPaint selectedTextPaint;
    private TextPaint normalTextPaint;
    private Paint selectedBgPaint;
    private Paint selectedNormalLinePaint;
    private Paint selectedFocusLinePaint;

    private WheelViewHandler mWheelViewHandler;
    private OnWheelViewListener mWheelViewListener;

    public WheelView(Context context) {
        super(context);
        initView(context);
    }

    public WheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public WheelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        MLog.i(TAG, "initView");

        selectedTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        normalTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        selectedBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectedNormalLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectedFocusLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        selectedTextPaint.setColor(Color.RED);
        normalTextPaint.setColor(Color.BLACK);
        selectedBgPaint.setColor(Color.GRAY);
        selectedNormalLinePaint.setColor(Color.GRAY);
        selectedFocusLinePaint.setColor(Color.YELLOW);

        mWheelViewHandler = new WheelViewHandler(this);
    }

    public void setOnWheelViewListener(OnWheelViewListener listener) {
        mWheelViewListener = listener;
    }

    public void setDefItemIndex(int index) {
        mDefItemIndex = index;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
        itemHeight = viewHeight / itemNumber;
    }

    public void setWheelViewItemList(List<WheelViewItem> list, int selectedItemIndex) {
        if (list == null) {
            throw new InvalidParameterException("list is null");
        }

        if (selectedItemIndex < 0) {
            selectedItemIndex = 0;
        } else if (selectedItemIndex >= list.size()) {
            selectedItemIndex = list.size() - 1;
        }

        this.mItemList = list;

        this.mSelectedItemIndex = selectedItemIndex;
        this.mDefItemIndex = selectedItemIndex;
    }

    private void setSelectedItemIndex(int index) {
        this.mSelectedItemIndex = index;
    }

    private int getSelectedItemIndex() {
        return mSelectedItemIndex;
    }

    private int getItemListSize() {
        return mItemList.size();
    }

    private WheelViewItem getItem(int index) {
        return mItemList.get(index);
    }

    private void doInvalidateWheelView(boolean isSelected) {
        invalidate();
        if (isSelected) {
            if (mWheelViewListener != null) {
                mWheelViewListener.onSelect(getItem(mSelectedItemIndex), mSelectedItemIndex);
            }
        }
    }

    public void doOnKeyDown(int keyCode) {
        MLog.i(TAG, "doOnKeyDown");
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
            case KeyEvent.KEYCODE_DPAD_DOWN:
                mWheelViewHandler.handleStartMoveUpDown(keyCode);
                break;

            default:
                break;
        }
    }

    public void doOnKeyUp(int keyCode) {
        MLog.i(TAG, "doOnKeyUp");
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
            case KeyEvent.KEYCODE_DPAD_DOWN:
                mWheelViewHandler.doRefresh();
                break;
            default:
                break;
        }
    }

    public void doOnClick() {
        if (mWheelViewListener != null) {
            mWheelViewListener.onClick(getItem(mSelectedItemIndex), mSelectedItemIndex);
        }
    }

    public void doOnFocusChange(boolean hasFocus) {
        this.hasFocus = hasFocus;
        doInvalidateWheelView(false);

        if (!hasFocus && resetDefItemAfterLoseFocus) {
            mSelectedItemIndex = mDefItemIndex;
        }
        if (mWheelViewListener != null) {
            mWheelViewListener.onFocusChange(hasFocus);
        }
    }

    private static class WheelViewHandler extends Handler {

        static final int MSG_MOVE_UP_DOWN = 1;
        static final int DELAY_HANDLER_INTERVAL = 500;

        static int ON_KEY_DOWN = 0;
        static int ON_KEY_UP = 1;

        private int lastKeyCode = -1;

        private WeakReference<WheelView> mWheelViewWeakReference;

        private int nextSelectedItemIndex = 0;

        public WheelViewHandler(WheelView wheelView) {
            mWheelViewWeakReference = new WeakReference<>(wheelView);
        }

        public void handleStartMoveUpDown(int keyCode) {

            if (hasMessages(MSG_MOVE_UP_DOWN)) {
                return;
            }
            Message message = Message.obtain();
            message.what = MSG_MOVE_UP_DOWN;
            message.arg1 = keyCode;
            message.arg2 = ON_KEY_DOWN;

            lastKeyCode = keyCode;
            sendMessageDelayed(message, DELAY_HANDLER_INTERVAL);
        }

        public void doRefresh() {
            if (lastKeyCode == -1) {
                return;
            }

            removeMessages(MSG_MOVE_UP_DOWN);
            Message message = Message.obtain();
            message.what = MSG_MOVE_UP_DOWN;
            message.arg1 = lastKeyCode;
            message.arg2 = ON_KEY_UP;
            sendMessage(message);
        }

        @Override
        public void handleMessage(Message msg) {

            WheelView wheelView = mWheelViewWeakReference.get();
            if (wheelView == null) {
                return;
            }
            switch (msg.what) {

                case MSG_MOVE_UP_DOWN:

                    if (msg.arg1 == KeyEvent.KEYCODE_DPAD_UP) {
                        nextSelectedItemIndex = wheelView.getSelectedItemIndex() - 1;
                        if (nextSelectedItemIndex < 0) {
                            nextSelectedItemIndex = 0;
                        }
                    } else if (msg.arg1 == KeyEvent.KEYCODE_DPAD_DOWN) {
                        nextSelectedItemIndex = wheelView.getSelectedItemIndex() + 1;
                        if (nextSelectedItemIndex >= wheelView.getItemListSize()) {
                            nextSelectedItemIndex = wheelView.getItemListSize() - 1;
                        }
                    } else {
                        break;
                    }

                    wheelView.setSelectedItemIndex(nextSelectedItemIndex);
                    wheelView.doInvalidateWheelView(msg.arg2 == ON_KEY_UP);
                    lastKeyCode = -1;
                    break;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mItemList == null || mItemList.isEmpty()) {
            return;
        }

        int selectIndex;
        if (!hasFocus && resetDefItemAfterLoseFocus) {
            selectIndex = mDefItemIndex;
        } else {
            selectIndex = mSelectedItemIndex;
        }

        MLog.i(TAG, "onDraw  hasFocus :" + hasFocus + " , selectIndex :" + selectIndex);

        // 1 绘制选中item的背景和线条——wheelView中间的选中框
        Paint linePaint;
        if (hasFocus) {
            linePaint = selectedFocusLinePaint;
        } else {
            linePaint = selectedNormalLinePaint;
        }
        canvas.drawLine(0, itemHeight * halfItemNumber - 2,
                viewWidth, itemHeight * halfItemNumber, linePaint);
        canvas.drawLine(0, itemHeight * (halfItemNumber + 1),
                viewWidth, itemHeight * (halfItemNumber + 1) + 2, linePaint);
        canvas.drawRect(0, itemHeight * halfItemNumber,
                viewWidth, itemHeight * (halfItemNumber + 1), selectedBgPaint);

        // 2 绘制item信息
        WheelViewItem item;
        Paint textPaint;
        float midY;
        int startIndex = Math.max(0, selectIndex - (halfItemNumber + 1));
        int itemCount = Math.min(mItemList.size() - 1, selectIndex + (halfItemNumber + 1));
        for (int i = startIndex; i <= itemCount; i++) {
            item = mItemList.get(i);

            midY = itemHeight * (halfItemNumber - (selectIndex - i)) + itemHeight / 2;
            if (i == selectIndex) {
                textPaint = selectedTextPaint;
            } else {
                textPaint = normalTextPaint;
            }

            textPaint.setTextSize(20);
            canvas.drawText(item.getShowName(), (viewWidth - getTextWidth(textPaint, item.getShowName())) / 2,
                    midY + getTextBaseLineToCenter(textPaint), textPaint);
        }
    }

    private float getTextWidth(Paint paint, String text) {
        return paint.measureText(text);
    }

    private float getTextBaseLineToCenter(Paint paint) {
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        return ((float) (-fontMetrics.bottom - fontMetrics.top)) / 2;
    }

    public interface OnWheelViewListener {

        void onFocusChange(boolean hasFocus);

        void onClick(WheelViewItem item, int index);

        void onSelect(WheelViewItem item, int index);
    }

}
