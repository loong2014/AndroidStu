package com.sunny.androidstu.view.wheel_view;

/**
 * Created by zhangxin17 on 2019/1/4
 * 自定义竖直滑动选择器-数据模型
 */
public class WheelViewItem {

    private String showName;

    private int type;

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowName() {
        return showName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "WheelViewItem {" +
                " type :" + type +
                " showName :" + showName +
                " }";
    }
}
