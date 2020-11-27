package com.aposs.box.spider;

import com.aposs.box.spider.constant.SpiderProperties;
import com.aposs.box.spider.service.SpiderService;
import com.aposs.box.spider.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Aaron
 * @date 2020/11/26
 * <p>
 * 爬虫程序调度入口
 */
@Component
public class BoxSpiderRunner implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private Environment env;
    @Resource
    private SpiderService spiderService;

    private SpiderProperties tencentSpiderProperties;
    private SpiderProperties ifengSpiderProperties;

    @Override
    public void run(ApplicationArguments args) {
        tencentSpiderProperties = PropertiesUtil.getProperties(env, "box.spider.tencentNews", SpiderProperties.class);
        ifengSpiderProperties = PropertiesUtil.getProperties(env, "box.spider.ifengNews", SpiderProperties.class);
        spiderService.runTencentNewsSpider(tencentSpiderProperties);
        spiderService.runIfengNewsSpider(ifengSpiderProperties);
    }

    /**
     * 定时执行器，程序启动后立刻执行一次
     */
    @Scheduled(cron = "${box.spider.cron}")
    public void schedule() {
        logger.info("schedule start");
        spiderService.runTencentNewsSpider(tencentSpiderProperties);
        spiderService.runIfengNewsSpider(ifengSpiderProperties);
        logger.info("schedule finished");

    }


}
