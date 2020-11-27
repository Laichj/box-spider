package com.aposs.box.spider.dao;

import com.alibaba.fastjson.JSONObject;
import com.aposs.box.spider.constant.NewsConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Aaron
 * @date 2020/11/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsDaoTest {

    @Value("${box.spider.tencentNews.collectionName}")
    private String tencentNewsCollectionName;

    @Resource
    private NewsDao newsDao;

    @Test
    public void getDataTest() {
        List<JSONObject> newsList = newsDao.findNews(NewsConstant.COLLECTION_TENCENT_NEWS_JSON, 0, 10);
        newsList.forEach(news -> {
            System.out.println("_id:" + news.getString("_id") + "  publish_time:" + news.getString("publish_time"));
        });
    }


}
