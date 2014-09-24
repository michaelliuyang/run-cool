package com.rc.app.tools;

import java.math.BigDecimal;

/**
 * 数字计算帮助类
 * Created by michael 2014/9/18.
 */
public class NumberUtil {

    /**
     * 在最小值到最大值之前获取随机数
     *
     * @param min 最小值
     * @param max 最大值
     * @return 随机数
     */
    public static long getRandomNum(long min, long max) {
        return Math.round(min + Math.random() * (max - min));
    }

    /**
     * 格式化Double对象
     *
     * @param number double数字
     * @return 四舍五入后的整型值
     */
    public static int formatNumber(double number) {
        BigDecimal bg = new BigDecimal(number);
        return bg.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }

}
