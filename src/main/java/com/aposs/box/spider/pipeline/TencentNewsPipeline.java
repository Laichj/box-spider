package com.aposs.box.spider.pipeline;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aposs.box.spider.constant.NewsConstant;
import com.aposs.box.spider.dao.NewsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;

/**
 * @author Aaron
 * @date 2020/11/25
 * 新闻类数据pipeline
 */

@Component
public class TencentNewsPipeline implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(TencentNewsPipeline.class);

    @Value("${box.spider.tencentNews.collectionName}")
    private String tencentNewsCollectionName;


    @Resource
    private NewsDao newsDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        if (resultItems.isSkip()) {
            return;
        }
        JSONArray tencentNewsJsonArray = resultItems.get(NewsConstant.FIELD_TENCENT_NEWS_JSON_ARRAY);
        if (tencentNewsJsonArray != null && !tencentNewsJsonArray.isEmpty()) {
            int size = tencentNewsJsonArray.size();
            for (int i = 0; i < size; i++) {
                JSONObject tencentNewsJsonObject = tencentNewsJsonArray.getJSONObject(i);
                newsDao.saveNews(tencentNewsJsonObject, tencentNewsCollectionName, "cms_id");
            }
            logger.info("save tencentNewsJsonArray to MongoDB success! size:{}", size);
        }
    }
}
