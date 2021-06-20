package com.aposs.box.spider.constant.enums;

/**
 * 位置类型
 *
 * @Date 2021/6/20
 * @Created by Aaron
 */
public enum PositionTypeEnum {
    OBJECT("object"),
    ARRAY("array"),
    ;
    String value;

    PositionTypeEnum(String value) {
        this.value = value;
    }
}
