package com.aposs.box.spider;

import com.aposs.box.spider.constant.SpiderProperties;
import com.aposs.box.spider.service.NewsSpiderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Aaron
 * @date 2020/11/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TencentNewsSpiderTest {
    @Resource
    private Environment env;

    @Resource
    private NewsSpiderService newsSpiderService;

    private SpiderProperties tencentSpiderProperties;
    private SpiderProperties ifengSpiderProperties;

    @Test
    public void test() {
//        tencentSpiderProperties = PropertiesUtil.getProperties(env, "box.spider.tencentNews", SpiderProperties.class);
//        ifengSpiderProperties = PropertiesUtil.getProperties(env, "box.spider.ifengNews", SpiderProperties.class);
////        spiderService.runTencentNewsSpider(tencentSpiderProperties);
//        spiderService.runIfengNewsSpider(ifengSpiderProperties);
    }

}
