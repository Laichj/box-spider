package com.aposs.box.spider.config;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author Aaron
 * @date 2020/12/20
 */
public abstract class MyPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public abstract void process(Page page);

    @Override
    public Site getSite() {
        return this.site;
    }
}
