package com.kingsbet.wzry.util;

import java.math.BigDecimal;

public class Util {

    /**
     * 将指定的字符串转换成制定小数点位数的String字符串
     *
     * @param d
     * @param scales
     * @return
     */
    public static String changeDouble(String d, int scales) {

        BigDecimal bd1 = new BigDecimal(d);
//        BigDecimal bd2 = new BigDecimal(scales);
        bd1 = bd1.setScale(scales, BigDecimal.ROUND_HALF_UP);
        return bd1.toString();
    }
}
