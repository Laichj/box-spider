package com.aposs.box.spider;

import java.time.*;
import java.util.Date;

/**
 * @author Aaron
 * @date 2020/12/31
 */
public class LocalDateTest {
    public static void main(String[] args) {
        Long time = System.currentTimeMillis();


        LocalDate localDate = Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = Instant.ofEpochMilli(time+100000000).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDateTime localDateTime = Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDateTime();


        System.out.println(localDate);
        System.out.println(localDateTime);
        System.out.println(localDate.compareTo(localDate2));

        Date date = new Date();
        LocalDate localDate1 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


        LocalDate.now();

    }

}
