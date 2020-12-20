package com.aposs.box.spider.domain.stock;

import com.alibaba.fastjson.JSONObject;
import com.aposs.box.spider.config.MyPageProcessor;
import com.aposs.box.spider.domain.stock.dao.TradingDateRecordMapper;
import com.aposs.box.spider.domain.stock.entity.TradingDateRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * 个股实时行情
 *
 * @author Aaron
 * @date 2020/12/20
 */
@Component
public class StockRealTimeSpider {
    private Logger logger = LoggerFactory.getLogger(StockRealTimeSpider.class);


    @Resource
    private TradingDateRecordMapper tradingDateRecordMapper;


    /**
     * 爬取实时数据
     *
     * @param codes
     */
    public void crawRealTime(String... codes) {
        String[] urls = new String[codes.length];
        for (int i = 0; i < codes.length; i++) {
            urls[i] = getUrl(codes[i]);
        }
        // undo spider
    }

    /**
     * 监测今日交易状态 并 更新交易日记录
     */
    public void checkAndUpdateTradingDate() {
        String url = getUrl("300059");
        Spider.create(new StockRealTimePageProcessor()).addUrl(url).addPipeline(new UpdateTradingDatePipeline()).run();
    }

    /**
     * 拼接url
     *
     * @param code
     * @return
     */
    private String getUrl(String code) {
        char c = code.charAt(0);
        Integer market = (c <= '3') ? 0 : 1;
        String secid = market + "." + code;
        long time = new Date().getTime();
        String fields = "f43,f44,f45,f46,f47,f48,f50,f51,f52,f57,f58,f60,f86,f127,f128,f292";
        String url = "http://push2.eastmoney.com/api/qt/stock/get" +
                "?ut=fa5fd1943c7b386f172d6893dbfba10b" +
                "&invt=2&fltt=2" +
                "&fields=" + fields +
                "&secid=" + secid +
                "&cb=jQuery112403418241714388266_" + time +
                "&_=" + time;
        return url;

    }

    /**
     * 解析数据
     * f43: 26.9,	实时价格
     * f44: 27.23,	最高
     * f45: 26.72,	最底
     * f46: 27.09,	今开
     * f47: 1672064,		成交量
     * f48: 4509427200,	成交额
     * f50: 0.81,	量比
     * f51: 32.77,	涨停
     * f52: 21.85,	跌停
     * f57: "300059",
     * f58: "东方财富",
     * f60: 27.31,		昨收
     * f86: 1608276843,	时间戳（秒）
     * f127: "电子信息",
     * f128: "上海板块",
     * f292: 5,			股票状态，2 交易中，5 已收盘
     */
    private class StockRealTimePageProcessor extends MyPageProcessor {
        @Override
        public void process(Page page) {
            String pageString = page.getRawText();
            int startIndex = pageString.indexOf("(");
            int endIndex = pageString.lastIndexOf(")");
            String jsonString = pageString.substring(startIndex + 1, endIndex);
            JSONObject pageJson = JSONObject.parseObject(jsonString);
            JSONObject dataJson = pageJson.getJSONObject("data");
            page.putField("data", dataJson);
        }
    }


    private class UpdateTradingDatePipeline implements Pipeline {

        @Override
        public void process(ResultItems resultItems, Task task) {
            JSONObject dataJson = resultItems.get("data");
            Boolean isOpen = "2".equals(dataJson.getString("f292"));
            if (isOpen) {
                Long time = dataJson.getLong("f86");
                Calendar calendar = new Calendar.Builder().setInstant(time * 1000).build();
                Date todayDate = new Date();
                Calendar today = new Calendar.Builder().setInstant(todayDate).build();
                if (today.compareTo(calendar) == 0) {
                    TradingDateRecord record = new TradingDateRecord();
                    record.setTradingDate(today.getTime());
                    // 检测到今日开市状态
                    TradingDateRecord select = tradingDateRecordMapper.selectByTradingDate(todayDate);
                    if (select == null) {
                        tradingDateRecordMapper.insert(record);
                    }
                    logger.info("UpdateTradingDatePipeline success! Today is Trading date!");
                }
            } else {
                logger.info("UpdateTradingDatePipeline success! Today is not Trading date!");
            }
        }
    }
}



