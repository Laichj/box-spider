package com.aposs.box.spider.dao.mongo;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * MongoDB 通用 增删查改
 * @author Aaron
 * @date 2020/11/26
 * mongoDB DAO
 */
@Component
public class CommonDao {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 保存数据（可覆盖）
     *
     * @param dataJsonObject 数据JSON对象
     * @param collectionName 指定集合名称
     * @param idColumn       指定ID字段
     */
    public void saveObject(JSONObject dataJsonObject, String collectionName, String idColumn) {
        if (idColumn != null) {
            dataJsonObject.put("_id", dataJsonObject.getString(idColumn));
        }
        if (StringUtils.isEmpty(collectionName)) {
            mongoTemplate.save(dataJsonObject);
        } else {
            mongoTemplate.save(dataJsonObject, collectionName);
        }
    }

    /**
     * 获取数据
     *
     * @param collectionName 指定集合名称
     * @param limitNum       获取记录数量
     * @param skipNum        跳过记录数量
     * @return
     */
    public List<JSONObject> findObject(String collectionName, Integer skipNum, Integer limitNum, Sort sort) {
        Query query = new Query();
        if (sort != null) {
            query.with(sort);
        }
        query.limit(limitNum);
        query.skip(skipNum);
        return mongoTemplate.find(query, JSONObject.class, collectionName);
    }


}
