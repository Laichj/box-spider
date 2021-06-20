package com.aposs.box.spider.constant.enums;

/**
 * 爬虫类型，对于爬虫元数据spiderType字段
 *
 * @Date 2021/6/20
 * @Created by Aaron
 */
public enum SpiderTypeEnum {
    /**
     * 简单类型
     */
    SIMPLE("simple"),
    ;
    String value;

    SpiderTypeEnum(String value) {
        this.value = value;
    }
}
