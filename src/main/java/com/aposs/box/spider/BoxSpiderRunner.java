package com.aposs.box.spider;

import com.aposs.box.spider.constant.SpiderProperties;
import com.aposs.box.spider.domain.stock.NewStockSpider;
import com.aposs.box.spider.domain.stock.StockRealTimeSpider;
import com.aposs.box.spider.service.NewsSpiderService;
import com.aposs.box.spider.service.StockSpiderService;
import com.aposs.box.spider.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

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
    private NewsSpiderService newsSpiderService;
    @Resource
    private StockSpiderService stockSpiderService;
    @Resource
    private StockRealTimeSpider stockRealTimeSpider;
    @Resource
    private NewStockSpider newStockSpider;


    @Value("${spring.profiles.active}")
    private String active;

    private SpiderProperties tencentSpiderProperties;
    private SpiderProperties ifengSpiderProperties;

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("---------------------------run-------------------------");
        tencentSpiderProperties = PropertiesUtil.getProperties(env, "box.spider.tencentNews", SpiderProperties.class);
        ifengSpiderProperties = PropertiesUtil.getProperties(env, "box.spider.ifengNews", SpiderProperties.class);

        // 开机执行一次新闻爬取程序
        processNewsSpiderSchedule();

    }

    /**
     * 定时执行器，程序启动后立刻执行一次
     */
    @Scheduled(cron = "${box.spider.cron}")
    public void processNewsSpiderSchedule() {
        logger.info("processNewsSpiderSchedule start");
        newsSpiderService.runTencentNewsSpider(tencentSpiderProperties);
        newsSpiderService.runIfengNewsSpider(ifengSpiderProperties);
        logger.info("processNewsSpiderSchedule finished");
    }


    /**
     * 每天 10:00 执行
     */
    @Scheduled(cron = "0 0 10 1/1 * ?")
    public void processCheckTradingDateSchedule() {
        // 检查交易日并记录
        stockRealTimeSpider.checkAndUpdateTradingDate();

        // 爬取新股
        newStockSpider.crawNewStockInfo();
    }

    /**
     * 每天 15:30 执行
     */
    @Scheduled(cron = "0 30 15 1/1 * ?")
    public void processStockSpiderSchedule() {
        // 如果当天未交易日，则执行行情数据爬取
        if (stockSpiderService.checkTradingDate(new Date())) {
            stockSpiderService.runKlineSpider(1);
        }
    }


}
