package com.aposs.box.spider.simple;

import com.alibaba.fastjson.JSONObject;
import com.aposs.box.spider.model.entity.PipeLineProperty;

/**
 * @Date 2021/6/21
 * @Created by Aaron
 */
public class JsonTest {
    public static void main(String[] args) {
        PipeLineProperty pipeLinePropertyO;
        JSONObject pipeLineProperty = new JSONObject();
        pipeLineProperty.put("idColumn","group");
        pipeLineProperty.put("storeType","mongo");
        System.out.println(pipeLineProperty);

        System.out.println(Integer.getInteger("1"));
        System.out.println(Integer.valueOf("1"));


    }
}
