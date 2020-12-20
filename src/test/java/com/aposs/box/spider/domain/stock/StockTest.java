package com.aposs.box.spider.domain.stock;

import com.aposs.box.spider.service.StockSpiderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Aaron
 * @date 2020/12/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StockTest {

    @Resource
    private StockSpiderService stockSpiderService;

    @Resource
    private StockRealTimeSpider stockRealTimeSpider;

    @Resource
    private NewStockSpider newStockSpider;



    @Test
    public void test() {
//        stockSpiderService.runStockInfoSpider();
//        stockSpiderService.crawKline("000001",0,1);
//        stockSpiderService.runKlineSpider(250, "300605", null);
//        stockRealTimeSpider.checkAndUpdateTradingDate();
//        newStockSpider.crawNewStockInfo();
    }

}
