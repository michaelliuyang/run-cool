package com.rc.app.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    public static final String YYYYMMDD = "yyyy-MM-dd";

    public static final String DEFAULT_TIMEZONG_ID = "GMT+8";

    public static final String DEFAULT_TIMEZONG_NAME = "Asia/Shanghai";

    /**
     * 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 按时区格式化日期
     *
     * @param timeMillis
     * @param timeZone
     * @param pattern
     * @return
     * @throws Exception
     */
    public static String getTimeByZone(long timeMillis, String timeZone, String pattern) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        sdf.applyPattern(pattern);
        String result = sdf.format(new Date(timeMillis));
        return result;
    }

    public static Date parse(String date, String pattern) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(date);
    }

    public static Date changeTimeZone(Date date, TimeZone oldZone, TimeZone newZone) {
        Date dateTmp = null;
        if (date != null) {
            int timeOffset = oldZone.getRawOffset() - newZone.getRawOffset();
            dateTmp = new Date(date.getTime() - timeOffset);
        }
        return dateTmp;
    }
}
