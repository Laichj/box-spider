package com.aposs.box.spider;

import com.aposs.box.spider.domain.stock.NewStockSpider;
import com.aposs.box.spider.domain.stock.StockRealTimeSpider;
import com.aposs.box.spider.service.SimpleSpiderService;
import com.aposs.box.spider.service.StockSpiderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * @author Aaron
 * @date 2020/11/26
 * <p>
 * 爬虫程序调度入口
 */
@Component
public class BoxSpiderRunner implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private StockSpiderService stockSpiderService;
    @Resource
    private StockRealTimeSpider stockRealTimeSpider;
    @Resource
    private NewStockSpider newStockSpider;
    @Resource
    private SimpleSpiderService simpleSpiderService;



    @Override
    public void run(ApplicationArguments args) {
        // 启动程序立刻执行一次爬取程序
        processNewsSpiderSchedule();
    }

    /**
     * 定时执行器，程序启动后立刻执行一次
     */
    @Scheduled(cron = "${box.spider.cron}")
    public void processNewsSpiderSchedule() {
        logger.info("---------- SpiderSchedule start ----------");
        simpleSpiderService.runAllSimpleSpider();
        logger.info("---------- SpiderSchedule finished ----------");
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
        // 如果当天为交易日，则执行行情数据爬取
        if (stockSpiderService.checkTradingDate(LocalDate.now())) {
            stockSpiderService.runKlineSpider(1);
        }
    }


}
