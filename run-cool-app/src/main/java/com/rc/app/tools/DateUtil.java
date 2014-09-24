package com.rc.app.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期帮助类
 * Created by michael 2014/9/18.
 */
public class DateUtil {

    public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String YYYYMMDD = "yyyy-MM-dd";

    /**
     * 格式化日期
     *
     * @param date    日期对象
     * @param pattern 格式化格式
     * @return 格式化后的日期
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

}
