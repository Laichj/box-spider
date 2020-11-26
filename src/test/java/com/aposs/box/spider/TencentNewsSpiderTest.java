package com.aposs.box.spider;

import com.aposs.box.spider.pipeline.NewsPipeline;
import com.aposs.box.spider.processor.TencentNewsProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;

/**
 * @author Aaron
 * @date 2020/11/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TencentNewsSpiderTest {

    @Resource
    private NewsPipeline newsPipeline;

    @Resource
    private TencentNewsProcessor tencentNewsProcessor;

    @Test
    public void test(){
        String url = "https://i.news.qq.com/trpc.qqnews_web.kv_srv.kv_srv_http_proxy/list?sub_srv_id=sh&srv_id=pc&offset=0&limit=150&strategy=1&ext=%7b%22pool%22%3a%5b%22top%22%5d%2c%22is_filter%22%3a10%2c%22check_type%22%3atrue%7d";
        Spider.create(tencentNewsProcessor).addUrl(url).addPipeline(newsPipeline).run();
    }

}
