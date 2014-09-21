package com.rc.app.tools;

import java.math.BigDecimal;

public class NumberUtil {

    public static long getRandomNum(long min, long max) {
        return Math.round(min + Math.random() * (max - min));
    }

    public static int formatNumber(double number) {
        BigDecimal bg = new BigDecimal(number);
        return bg.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    public static void main(String[] args) {
        System.out.println(formatNumber(98.1d));
    }
}
