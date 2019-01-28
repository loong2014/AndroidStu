package com.sunny.androidstu.player.danmaku;

/**
 * Created by zhangxin17 on 2019/1/28
 */
public class DanmakuInfo {

    // 文字的内容+大小+颜色
    private String text;
    private float size;
    private Integer color;

    // 文字的位置
    private float posX;
    private float posY;

    // 位置的移动速度
    private int speed;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
