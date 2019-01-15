package com.sunny.baselib;

import java.util.Random;

/**
 * Created by zhangxin17 on 2019/1/14
 */
public class MStringUtils {

    private static final String BASE_CHAR = "abcdefghijklmnopqrstuvwxyz0123456789";

    public static String getRandomStr() {

        int baseCharLen = BASE_CHAR.length();
        int randomNum;
        char randomChar;
        Random random = new Random();
        // StringBuffer类型的可以append增加字符
        StringBuffer str = new StringBuffer();

        int len = random.nextInt(10);
        for (int i = 0; i < len; i++) {
            // 可生成[0,n)之间的整数，获得随机位置
            randomNum = random.nextInt(baseCharLen);
            // 获得随机位置对应的字符
            randomChar = BASE_CHAR.charAt(randomNum);
            // 组成一个随机字符串
            str.append(randomChar);
        }
        return str.toString();
    }

    public static String getRandomColorStr() {
        Random random = new Random();

        String r, g, b;
        r = Integer.toHexString(random.nextInt(256)).toLowerCase();
        g = Integer.toHexString(random.nextInt(256)).toLowerCase();
        b = Integer.toHexString(random.nextInt(256)).toLowerCase();

        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;

        return "#" + r + g + b;
    }

    public static int getRandomColor() {
        return 0xff000000 | (new Random()).nextInt(0x00ffffff);
    }
}
