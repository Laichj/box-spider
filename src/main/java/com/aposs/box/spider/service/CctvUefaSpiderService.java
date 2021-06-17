package com.aposs.box.spider.service;

import com.aposs.box.spider.constant.SpiderProperties;
import com.aposs.box.spider.domain.match.pipeline.CctvUefaPipeline;
import com.aposs.box.spider.domain.match.processor.CctvUefaProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;

/**
 * @Date 2021/6/17
 * @Created by Aaron
 */
@Service
public class CctvUefaSpiderService {
    private final Logger logger = LoggerFactory.getLogger(CctvUefaSpiderService.class);
    @Resource
    private CctvUefaPipeline cctvUefaPipeline;
    @Resource
    private CctvUefaProcessor cctvUefaProcessor;


    // 执行腾讯新闻爬取任务
    public void runUefaMatchSpider(SpiderProperties spiderProperties) {
        logger.info("------------ start runUefaMatchSpider ... --------------");
        String url = spiderProperties.getUrl();
        Spider.create(cctvUefaProcessor).addUrl(url).addPipeline(cctvUefaPipeline).run();
        logger.info("----------- runUefaMatchSpider finished! ---------------");
    }
}
