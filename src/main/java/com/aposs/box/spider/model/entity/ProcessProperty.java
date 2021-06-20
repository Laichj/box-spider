package com.aposs.box.spider.model.entity;

import lombok.Data;

/**
 * process 爬虫数据处理配置项
 * @Date 2021/6/20
 * @Created by Aaron
 */
@Data
public class ProcessProperty {


    /**
     * 数据位置描述 JSON
     * 格式参考 Position
     */
    private Position position;


    @Data
    public static class Position{
        private String key;
        /**
         * “object" or "array"
         */
        private String type;

        private Position next;
    }


}
