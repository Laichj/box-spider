package com.aposs.box.spider.constant.enums;

/**
 * 爬虫状态，对于爬虫元数据spiderType字段
 *
 * @Date 2021/6/20
 * @Created by Aaron
 */
public enum SpiderStatusEnum {
    /**
     * 简单类型
     */
    OFF(0),
    ON(1);
    int value;

    SpiderStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
