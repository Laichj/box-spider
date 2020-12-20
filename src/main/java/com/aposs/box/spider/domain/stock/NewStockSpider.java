package com.aposs.box.spider.domain.stock;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aposs.box.spider.config.MyPageProcessor;
import com.aposs.box.spider.domain.stock.dao.StockInfoDao;
import com.aposs.box.spider.domain.stock.dao.StockInfoMapper;
import com.aposs.box.spider.domain.stock.entity.StockInfo;
import com.aposs.box.spider.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 新股爬取
 *
 * @author Aaron
 * @date 2020/12/20
 */
@Component
public class NewStockSpider {
    private Logger logger = LoggerFactory.getLogger(NewStockSpider.class);

    @Resource
    private StockInfoMapper stockInfoMapper;

    @Resource
    private StockInfoDao stockInfoDao;

    public void crawNewStockInfo() {
        logger.info("------------ start crawNewStockInfo ... --------------");
        new Spider(new NewStockInfoProcessor()).addUrl(getUrl()).addPipeline(new NewStockInfoPipeline()).run();
//            logger.error("crawNewStockInfo Exception!msg:{}",e.getMessage());
        logger.info("------------ crawNewStockInfo finished --------------");
    }

    private String getUrl() {
        String url = "http://dcfm.eastmoney.com/em_mutisvcexpandinterface/api/js/get?type=XGSG_LB" +
                "&token=70f12f2f4f091e459a279469fe49eca5" +
                "&st=listingdate%2csecuritycode" +
                "&sr=-1" +
                "&p=1" +
                "&ps=10" +
                "&js=var+oTnjTNgZ%3d%7bpages%3a(tp)%2cdata%3a(x)%7d" +
                "&rt=53615166";
        return url;
    }

    private class NewStockInfoProcessor extends MyPageProcessor {
        @Override
        public void process(Page page) {
            String pageString = page.getRawText();
            int startIndex = pageString.indexOf("[");
            int endIndex = pageString.lastIndexOf("]");
            String jsonString = pageString.substring(startIndex, endIndex + 1);
            JSONArray dataList = JSONArray.parseArray(jsonString);
            List<StockInfo> stockInfoList = new ArrayList<>();
            Date today = new Date(System.currentTimeMillis());
            for (int i = 0; i < dataList.size(); i++) {
                JSONObject data = dataList.getJSONObject(i);
                Date listingdate = data.getDate("listingdate");
                if (DateUtil.isSameDate(today, listingdate)) {
                    StockInfo stockInfo = new StockInfo();
                    String code = data.getString("securitycode");
                    stockInfo.setCode(data.getString("companycode"));
                    stockInfo.setName(code);
                    stockInfo.setIndustry(data.getString("INDUSTRY"));
                    stockInfo.setMarket(getMarket(code));
                    stockInfoList.add(stockInfo);
                }
            }
            page.putField("dataList", stockInfoList);
        }
    }

    private class NewStockInfoPipeline implements Pipeline {
        @Override
        public void process(ResultItems resultItems, Task task) {
            List<StockInfo> stockInfoList = resultItems.get("dataList");
            if (!stockInfoList.isEmpty()) {
                stockInfoList.forEach(stockInfo -> {
                    if (stockInfoDao.getStockInfoByCode(stockInfo.getCode()) == null) {
                        stockInfoMapper.insert(stockInfo);
                        logger.info("insert NewStockInfo success! code:{}, name:{}",
                                stockInfo.getCode(), stockInfo.getName());
                    }
                });
            }
        }
    }

    private Integer getMarket(String code) {
        return code.charAt(0) <= 3 ? 0 : 1;
    }
}
