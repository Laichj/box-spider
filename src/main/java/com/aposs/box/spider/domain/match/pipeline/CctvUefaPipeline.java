package com.aposs.box.spider.domain.match.pipeline;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aposs.box.spider.constant.Dictionary;
import com.aposs.box.spider.constant.NewsConstant;
import com.aposs.box.spider.domain.news.dao.NewsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;

/**
 * 欧洲杯2020
 * @author Aaron
 * @date 2020/11/25
 * 新闻类数据pipeline
 */

@Component
public class CctvUefaPipeline implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(CctvUefaPipeline.class);

    @Value("${box.spider.cctvUefa.collectionName}")
    private String cctvUefaCollectionName;


    @Resource
    private NewsDao newsDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        if (resultItems.isSkip()) {
            return;
        }
        JSONArray cctvUefaJsonArray = resultItems.get(Dictionary.CCTV_UEFA_KEY);
        if(cctvUefaJsonArray != null && !cctvUefaJsonArray.isEmpty()){
            int size = cctvUefaJsonArray.size();
            for(int i = 0;i<size;i++){
                JSONObject cctvUefaJsonObject = cctvUefaJsonArray.getJSONObject(i);
                newsDao.saveNews(cctvUefaJsonObject,cctvUefaCollectionName,"id");
            }
            logger.info("save cctvUefaJsonArray to MongoDB success! size:{}",size);
        }
    }
}
