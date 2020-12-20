package com.aposs.box.spider.utils;

import java.util.Date;

/**
 * @author Aaron
 * @date 2020/12/20
 */
public class DateUtil {
    public static Boolean isSameDate(Date date1, Date date2) {
        long ms = 24 * 3600 * 1000;
        long h8 = 8 * 3600 * 1000;
        if (date1 == null || date2 == null) {
            return false;
        }
        long day1 = Long.divideUnsigned(date1.getTime() + h8, ms);
        long day2 = Long.divideUnsigned(date2.getTime() + h8, ms);
        return day1 - day2 == 0;
    }

}
