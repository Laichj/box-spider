package com.aposs.box.spider.service;

import com.aposs.box.spider.constant.SpiderProperties;
import com.aposs.box.spider.pipeline.IfengNewsPipeline;
import com.aposs.box.spider.pipeline.TencentNewsPipeline;
import com.aposs.box.spider.processor.IfengNewsProcessor;
import com.aposs.box.spider.processor.TencentNewsProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Aaron
 * @date 2020/11/27
 */
@Service
public class SpiderService {

    private Logger logger = LoggerFactory.getLogger(SpiderService.class);

    @Resource
    private TencentNewsProcessor tencentNewsProcessor;
    @Resource
    private IfengNewsProcessor ifengNewsProcessor;
    @Resource
    private TencentNewsPipeline tencentNewsPipeline;
    @Resource
    private IfengNewsPipeline ifengNewsPipeline;

    // 执行腾讯新闻爬取任务
    public void runTencentNewsSpider(SpiderProperties spiderProperties) {
        logger.info("------------ start runTencentNewsSpider ... --------------");
        String url = spiderProperties.getUrl() +
                spiderProperties.getLimit() +
                "&strategy=1&ext=%7b%22pool%22%3a%5b%22top%22%5d%2c%22is_filter%22%3a10%2c%22check_type%22%3atrue%7d";
        Spider.create(tencentNewsProcessor).addUrl(url).addPipeline(tencentNewsPipeline).run();
        logger.info("----------- runTencentNewsSpider finished! ---------------");
    }

    // 执行凤凰新闻爬取任务
    public void runIfengNewsSpider(SpiderProperties spiderProperties) {
        logger.info("------------ start runIfengNewsSpider ... --------------");

        String url = spiderProperties.getUrl() +
                new Date().getTime() +
                "/" +
                spiderProperties.getLimit() +
                "/3-35191-/";
        Spider.create(ifengNewsProcessor).addUrl(url).addPipeline(ifengNewsPipeline).run();
        logger.info("----------- runIfengNewsSpider finished! ---------------");
    }
}
