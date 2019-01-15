package com.sunny.baselib.time;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zhangxin17 on 2019/1/14
 */
public class MTimeUtils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);//设置日期格式

    public static String getCurTimeYMDHMS() {
        return sdf.format(new Date());
    }
}
