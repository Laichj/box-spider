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

INSERT INTO `stock`.`tbl_spider_mate_data`(`id`, `title`, `description`, `spider_type`, `spider_name`, `spider_url`, `spider_status`, `scheduler_id`, `process_property`, `pipe_line_property`, `created_time`, `updated_time`) VALUES (1, '欧洲杯2020-CCTV-小组排名', '欧洲杯2020-CCTV-小组排名', 'simple', 'UEFA2020_CCTV_GROUP_RANKING', 'https://cbs-i.sports.cctv.com/cache/f5c5f5fee1f6013f43883f1ef592e992?ran=1624192795368', 1, NULL, '{\"position\":{\"next\":{\"next\":{\"key\":\"rankings\"},\"key\":\"0\"},\"key\":\"results\"}}', '{\"storeType\":\"mongo\",\"idColumn\":\"group\"}', '2021-06-20 12:46:17', '2021-06-28 15:52:35');
INSERT INTO `stock`.`tbl_spider_mate_data`(`id`, `title`, `description`, `spider_type`, `spider_name`, `spider_url`, `spider_status`, `scheduler_id`, `process_property`, `pipe_line_property`, `created_time`, `updated_time`) VALUES (2, 'NBA2021-Tencent-季后赛赛程', 'NBA2021-Tencent-季后赛赛程', 'simple', 'NBA2021_TENCENT_PLAYOFFS', 'https://matchweb.sports.qq.com/kbs/list?from=NBA_PC&columnId=100000&startTime=2021-06-27&endTime=2021-07-03&from=sporthp&callback=ajaxExec&_=1624811819000&matchType=2', 1, NULL, '{\"position\":{\"key\":\"data\"}}', '{\"storeType\":\"mongo\",\"idColumn\":\"key\"}', '2021-06-27 04:00:52', '2021-06-27 17:04:38');
INSERT INTO `stock`.`tbl_spider_mate_data`(`id`, `title`, `description`, `spider_type`, `spider_name`, `spider_url`, `spider_status`, `scheduler_id`, `process_property`, `pipe_line_property`, `created_time`, `updated_time`) VALUES (3, '欧洲杯2020-CCTV-赛程', '欧洲杯2020-CCTV-赛程', 'simple', 'UEFA2020_CCTV_MATCHES', 'http://cbs-i.sports.cctv.com/cache/723519a99f275a224497afcc4f09011b?ran=1623761177401', 1, NULL, '{\"position\":{\"key\":\"results\"}}', '{\"storeType\":\"mongo\",\"idColumn\":\"id\"}', '2021-06-28 15:51:49', '2021-06-28 15:53:45');
INSERT INTO `stock`.`tbl_spider_mate_data`(`id`, `title`, `description`, `spider_type`, `spider_name`, `spider_url`, `spider_status`, `scheduler_id`, `process_property`, `pipe_line_property`, `created_time`, `updated_time`) VALUES (4, '腾讯新闻-深圳', '腾讯新闻-深圳', 'simple', 'TENCENT_NEWS_SZ', 'https://i.news.qq.com/trpc.qqnews_web.kv_srv.kv_srv_http_proxy/list?sub_srv_id=gd&srv_id=pc&offset=0&limit=150&strategy=1&ext=%7b%22pool%22%3a%5b%22top%22%5d%2c%22is_filter%22%3a10%2c%22check_type%22%3atrue%7d', 1, NULL, '{\"position\":{\"key\":\"data\",\"next\":{\"key\":\"list\"}}}', '{\"storeType\":\"mongo\",\"idColumn\":\"cms_id\"}', '2021-06-29 16:21:09', '2021-06-29 16:27:16');
INSERT INTO `stock`.`tbl_spider_mate_data`(`id`, `title`, `description`, `spider_type`, `spider_name`, `spider_url`, `spider_status`, `scheduler_id`, `process_property`, `pipe_line_property`, `created_time`, `updated_time`) VALUES (5, '凤凰新闻', '凤凰新闻', 'simple', 'IFENG_NEWS', 'https://shankapi.ifeng.com/shanklist/_/getColumnInfo/_/dynamicFragment/6737905981943976661/{currentTimeMillis}/100//3-35191-/', 1, NULL, '{\"position\":{\"key\":\"data\",\"next\":{\"key\":\"newsstream\"}}}', '{\"storeType\":\"mongo\",\"idColumn\":\"id\"}', '2021-06-29 16:26:53', '2021-06-29 16:40:03');
INSERT INTO `stock`.`tbl_spider_mate_data`(`id`, `title`, `description`, `spider_type`, `spider_name`, `spider_url`, `spider_status`, `scheduler_id`, `process_property`, `pipe_line_property`, `created_time`, `updated_time`) VALUES (6, '2020东京奥运奖牌榜', '2020东京奥运奖牌榜', 'simple', 'OLYMPIC_GAMES_MEDAL', 'https://app.sports.qq.com/tokyoOly/medalsList?from=h5&medalsType=all&callback=jQuery111301296969865306643_1627095097035&_=1627095097036', 0, NULL, '{\"position\":{\"key\":\"data\",\"next\":{\"key\":\"data\",\"next\":{\"key\":\"total\"}}}}', '{\"storeType\":\"mongo\",\"idColumn\":\"nocId\"}', '2021-07-24 17:32:54', '2022-02-12 08:32:01');
