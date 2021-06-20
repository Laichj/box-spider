package com.aposs.box.spider.model.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * 爬虫元数据
 *
 * @Date 2021/6/20
 * @Created by Aaron
 */
@Data
@Builder
@Table(name = "tbl_spider_mate_data")
public class SpiderMateData {
    private Integer id;
    // 标题，展示用
    private String title;
    // 说明，展示用
    private String description;


    /**
     * 爬虫类型，"simple" 简单类型
     */
    private String spiderType;

    /**
     * 爬虫名称，全局唯一，逻辑处理用，全小写下划线
     */
    private String spiderName;

    /**
     * 爬取URL
     */
    private String spiderUrl;

    /**
     * 爬虫状态，0 关闭，1 开始
     */
    private Integer spiderStatus;

    /**
     * 绑定调度器ID
     */
    private Integer schedulerId;

    /**
     * process 数据处理配置项，JSON对象
     */
    private String processProperty;

    /**
     * PipeLine 数据存储配置项，JSON对象
     */
    private String pipeLineProperty;


    private Date createdTime;

    private Date updatedTime;


}


