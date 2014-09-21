package com.rc.app.tools;

public class NumberUtil {

    public static long getRandomNum(long min, long max) {
        return Math.round(min + Math.random() * (max - min));
    }
}
