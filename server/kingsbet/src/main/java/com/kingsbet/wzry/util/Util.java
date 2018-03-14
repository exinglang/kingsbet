package com.kingsbet.wzry.util;

import com.kingsbet.wzry.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Util {
    @Autowired
    private UserDao dao;
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


    public static String getCurrentTimeYYYYMMDDHHMMSS() {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//将毫秒级long值转换成日期格式
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(System.currentTimeMillis());
        return dateformat.format(gc.getTime());
//        dao.saveException("","");
    }



}
