box系列爬虫项目
---
# 1 项目简介
## 1.1 功能介绍
- 基于WebMagic-0.7.3开源框架实现的爬虫项目
- 基于SpringBoot框架搭建
- 支持爬取主流新闻网站的新闻数据
- 支持爬取东方财富网A股所有股票250日行情，数据量达到100万
- 支持自动更新数据
- 支持CCTV欧洲杯2020比赛数据爬取

## 1.2 数据落库位置
- 新闻数据存储在MongoDB
- 股票数据存储在MySQL的stock数据库

## 1.3 目前支持爬取网站：

### 新闻
- 腾讯新闻
- 凤凰资讯

### 股票
- 东方财富 个股信息
- 东方财富 k线信息
- 东方财富 交易日信息
- 东方财富 新股信息

### 竞技赛事
- CCTV 欧洲杯2020赛事


# 2 使用说明

## 2.1 数据库准备
1. 准备有效MongoDB、MySQL数据库，并配置到resources/application-dev.yml
2. 初始化数据库
- mongoDB脚本：doc/mongoDB.sql
- MySQL脚本：doc/stock.sql

## 2.2 启动程序
1. 执行 src/test/java/com/aposs/box/spider/domain/stock/StockTest.java 的 runStockInfoSpiderTest 方法，即可爬取A股所有股票清单
2. 从com.aposs.box.spider.BoxSpiderApplication类启动项目，即可启动服务定时爬取A股行情

## 2.3 爬取股票数据说明
### 2.3.1 初始化 tbl_stock_info 表
- 开始爬取股票k线数据前，需要确认tbl_stock_info表有数据，因为程序是依赖此表中的股票数据执行爬取任务的；

- 初始化或更新 tbl_stock_info 表的数据入口为：
> com.aposs.box.spider.service.StockSpiderService.runStockInfoSpider

- 也可以选择执行 doc/tbl_stock_info.sql 脚本初始化 tbl_stock_info 表数据，但是可能漏掉新股

### 2.3.2 爬取A股所有股票250日行情
- 以下方法为爬取入口为，传入参数 250 即可开始爬取所有股票250日行情
> com.aposs.box.spider.service.StockSpiderService.runKlineSpider(java.lang.Integer)

## 2.4 通用爬虫（包括各种赛事、新闻爬取）
1. 初始化爬虫元数据表 tbl_spider_mate_data（数据库脚本：doc/tbl_spider_mate_data.sql）
2. 启动项目即可自动爬取相关数据到mongoDB对应的集合中（集合名称为{spider_name}）








