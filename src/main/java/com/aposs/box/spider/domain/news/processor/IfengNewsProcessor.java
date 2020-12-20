package com.aposs.box.spider.domain.news.processor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aposs.box.spider.constant.NewsConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author Aaron
 * @date 2020/11/25
 * <p>
 * 腾讯新闻爬虫processor
 */
@Component
public class IfengNewsProcessor implements PageProcessor {

    private static Logger logger = LoggerFactory.getLogger(IfengNewsProcessor.class);

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {
        String pageString = page.getRawText();
        JSONObject pageJson = JSONObject.parseObject(pageString);
        if (pageJson.getInteger("code") != 0) {
            logger.error(pageJson.toJSONString());
            return;
        }
        JSONArray dataList = pageJson.getJSONObject("data").getJSONArray("newsstream");
        page.putField(NewsConstant.FIELD_IFENG_NEWS_JSON_ARRAY, dataList);

    }

    @Override
    public Site getSite() {
        return this.site;
    }

}
