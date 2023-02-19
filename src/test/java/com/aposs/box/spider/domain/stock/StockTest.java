package com.aposs.box.spider.domain.stock;

import com.aposs.box.spider.domain.stock.dao.TradingDateRecordMapper;
import com.aposs.box.spider.domain.stock.entity.TradingDateRecord;
import com.aposs.box.spider.service.StockSpiderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    @Resource
    private TradingDateRecordMapper tradingDateRecordMapper;



    @Test
    public void test() {
//        stockSpiderService.runStockInfoSpider();
//        stockSpiderService.crawKline("000001",0,1);
//        stockSpiderService.runKlineSpider(250, "300605", null);
//        stockRealTimeSpider.checkAndUpdateTradingDate();
//        newStockSpider.crawNewStockInfo();
//        stockRealTimeSpider.checkAndUpdateTradingDate();

//        stockSpiderService.runKlineSpider(2);
//        stockSpiderService.runKlineSpider(20,"000001","000001");

//        LocalDate localDate = tradingDateRecordMapper.selectMaxDateRecord();
//        System.out.println(localDate);

//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate localDate = LocalDate.from(dateTimeFormatter.parse("2020-12-19"));
//        TradingDateRecord tradingDateRecord = tradingDateRecordMapper.selectByTradingDate(localDate);
//        System.out.println(tradingDateRecord);

        stockRealTimeSpider.checkAndUpdateTradingDate();

    }

    /**
     * 测试股票信息爬取
     */
    @Test
    public void runStockInfoSpiderTest(){
        stockSpiderService.runStockInfoSpider();
    }

}
