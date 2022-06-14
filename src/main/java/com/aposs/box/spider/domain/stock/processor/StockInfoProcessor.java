package com.aposs.box.spider.domain.stock.processor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author Aaron
 * @date 2020/12/19
 */
@Component
public class StockInfoProcessor implements PageProcessor {
    private static Logger logger = LoggerFactory.getLogger(StockInfoProcessor.class);

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {
        String pageString = page.getRawText();
        int startIndex = pageString.indexOf("(");
        int endIndex = pageString.lastIndexOf(")");
        String jsonString = pageString.substring(startIndex + 1, endIndex);
        JSONObject pageJson = JSONObject.parseObject(jsonString);
        JSONArray dataList = pageJson.getJSONObject("data").getJSONArray("diff");
        page.putField("dataList", dataList);
    }

    @Override
    public Site getSite() {
        return this.site;
    }
}
