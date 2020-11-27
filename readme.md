box系列爬虫项目
---
# 1 项目简介
- 本项目是基于WebMagic-0.7.3开源框架实现的爬虫项目，主要用于爬取主流新闻网站的新闻数据。
- 本项目基于SpringBoot框架搭建
- 目前是每30分钟爬取一次数据，爬取的数据会和历史数据进行去重后保存在MongoDB中

#### 目前支持爬取新闻网站：
- 腾讯新闻
- 凤凰资讯

# 2 使用说明
- 在 resources/application.yml 中配置有效MongoDB数据库链接
- 从com.aposs.box.spider.BoxSpiderApplication类启动项目








