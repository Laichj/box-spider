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
public class IfengNewsPipeline implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(IfengNewsPipeline.class);

    @Value("${box.spider.ifengNews.collectionName}")
    private String ifengNewsCollectionName;


    @Resource
    private NewsDao newsDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        if (resultItems.isSkip()) {
            return;
        }
        JSONArray ifengNewsJsonArray = resultItems.get(NewsConstant.FIELD_IFENG_NEWS_JSON_ARRAY);
        if(ifengNewsJsonArray != null && !ifengNewsJsonArray.isEmpty()){
            int size = ifengNewsJsonArray.size();
            for(int i = 0;i<size;i++){
                JSONObject ifengNewsJsonObject = ifengNewsJsonArray.getJSONObject(i);
                newsDao.saveNews(ifengNewsJsonObject,ifengNewsCollectionName,"id");
            }
            logger.info("save ifengNewsJsonArray to MongoDB success! size:{}",size);
        }
    }
}
