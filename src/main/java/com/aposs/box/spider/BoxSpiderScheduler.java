package com.aposs.box.spider;

import com.aposs.box.spider.pipeline.NewsPipeline;
import com.aposs.box.spider.processor.TencentNewsProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;

/**
 * @author Aaron
 * @date 2020/11/26
 * <p>
 * 定时器
 */
@Component
public class BoxSpiderScheduler implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private NewsPipeline newsPipeline;

    @Resource
    private TencentNewsProcessor tencentNewsProcessor;

    @Value("${box.spider.tencentNews.cron}")
    public String tencentNewsCorn;

    @Value("${box.spider.tencentNews.limit}")
    public Integer limit;


    /**
     * 定时执行器，程序启动后立刻执行一次
     */
    @Scheduled(cron = "0 */30 * * * ?")
    public void schedule() {
        runTencentNewsSpider();
    }


    private void runTencentNewsSpider() {
        logger.info("------------ start runTencentNewsSpider ... --------------");
        String url = "https://i.news.qq.com/trpc.qqnews_web.kv_srv.kv_srv_http_proxy/list?sub_srv_id=sh&srv_id=pc&offset=0&limit=" + limit + "&strategy=1&ext=%7b%22pool%22%3a%5b%22top%22%5d%2c%22is_filter%22%3a10%2c%22check_type%22%3atrue%7d";
        Spider.create(tencentNewsProcessor).addUrl(url).addPipeline(newsPipeline).run();
        logger.info("----------- runTencentNewsSpider finished! ---------------");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        runTencentNewsSpider();
    }
}
