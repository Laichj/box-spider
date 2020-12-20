package com.aposs.box.spider.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * @author Aaron
 * @date 2020/12/7
 * 计时工具
 */
public class ClockUtil {

    private final Date startTime;
    private Date lastStopTime;

    public ClockUtil() {
        startTime = new Date();
    }

    /**
     * 获取开始计时时间
     *
     * @return
     */
    public String getStartTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
        return simpleDateFormat.format(startTime);
    }

    /**
     * 停表一次，并返回总消耗时间（单位：ms）
     *
     * @return
     */
    public Long stopClock() {
        lastStopTime = new Date();
        return getTotalElapsedTime();
    }

    /**
     * 返回总消耗时间（单位：ms）
     *
     * @return
     */
    public Long getTotalElapsedTime() {
        return getElapsedTime(startTime);
    }

    /**
     * 返回上次停表至今消耗时间（单位：ms）
     *
     * @return
     */
    public Long getLastElapsedTime() {
        return getElapsedTime(lastStopTime);
    }

    private Long getElapsedTime(Date start) {
        return new Date().getTime() - start.getTime();
    }



}
