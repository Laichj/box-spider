package com.aposs.box.spider.constant.enums;

/**
 * 存储类型
 *
 * @Date 2021/6/20
 * @Created by Aaron
 */
public enum StoreTypeEnum {
    MONGO_DB("mongo"),
    MYSQL("mysql"),
    ;
    String value;

    StoreTypeEnum(String value) {
        this.value = value;
    }
}
