-- 20200620 aaron
drop table tbl_spider_mate_data;
CREATE TABLE `stock`.`tbl_spider_mate_data`
(
    `id`                 int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `title`              varchar(32) NOT NULL COMMENT '标题，展示用',
    `description`        varchar(255) NULL COMMENT '说明，展示用',
    `spider_type`        varchar(32) NULL DEFAULT 'simple' COMMENT '爬虫类型，\"simple\" 简单类型',
    `spider_name`        varchar(64) NOT NULL COMMENT '爬虫名称，全局唯一，逻辑处理用，全小写下划线',
    `spider_url`         varchar(255) NULL COMMENT '爬取静态URL',
    `spider_status`      tinyint(4) NULL DEFAULT 0 COMMENT '爬虫状态，0 关闭，1 开始',
    `scheduler_id`       int(11) NULL COMMENT '绑定调度器ID',
    `process_property`   varchar(512) NULL COMMENT 'process 数据处理配置项，JSON对象',
    `pipe_line_property` varchar(512) NULL COMMENT 'PipeLine 数据存储配置项，JSON对象',
    `created_time`       datetime NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_time`       datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `index_tbl_spider_mate_data_spider_name`(`spider_name`)
);
