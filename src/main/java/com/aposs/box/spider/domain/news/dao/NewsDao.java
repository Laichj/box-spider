package com.aposs.box.spider.domain.news.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Aaron
 * @date 2020/11/26
 * 新闻数据DAO
 */
@Component
public class NewsDao {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 保存新闻数据（可覆盖）
     *
     * @param newsJsonObject 新闻对象
     * @param collectionName 指定集合名称
     * @param idColumn       指定ID字段
     */
    public void saveNews(JSONObject newsJsonObject, String collectionName, String idColumn) {
        if (idColumn != null) {
            newsJsonObject.put("_id", newsJsonObject.getString(idColumn));
        }
        if (StringUtils.isEmpty(collectionName)) {
            mongoTemplate.save(newsJsonObject);
        } else {
            mongoTemplate.save(newsJsonObject, collectionName);
        }
    }

    /**
     * 获取新闻数据（发布时间倒序）
     *
     * @param collectionName 指定集合名称
     * @param limitNum       获取记录数量
     * @param skipNum        跳过记录数量
     * @return
     */
    public List<JSONObject> findNews(String collectionName, Integer skipNum, Integer limitNum) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "publish_time"));
        query.limit(limitNum);
        query.skip(skipNum);
        return mongoTemplate.find(query, JSONObject.class, collectionName);
    }


}
