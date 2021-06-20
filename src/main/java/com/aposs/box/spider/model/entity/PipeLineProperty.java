package com.aposs.box.spider.model.entity;

import lombok.Data;

/**
 * PipeLine 数据存储配置项
 * @Date 2021/6/20
 * @Created by Aaron
 */
@Data
public class PipeLineProperty {

    /**
     * 存储类型，“mongo","mysql"
     */
    private String storeType;

    /**
     * 主键字段
     */
    private String idColumn;


    /**
     * 索引
     */
    private Index index;

    @Data
    public static class Index{
        /**
         * 数据库字段名称
         */
        private String column;

        /**
         * 数据库字段类型
         */
        private String type;

        /**
         * "desc" or "asc"
         */
        private String sort;
    }

}
