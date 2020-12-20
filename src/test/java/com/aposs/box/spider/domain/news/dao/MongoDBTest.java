package com.aposs.box.spider.domain.news.dao;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Aaron
 * @date 2020/11/26
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDBTest {

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void saveTest() {
//        mongoTemplate.save(getJSONObject(), "newsJson");
    }


    private JSONObject getJSONObject() {
        return JSONObject.parseObject("{\n" +
                "cms_id: \"20201125A0H7HQ00\",\n" +
                "title: \"市民运动会射箭总决赛申城上演，为爱好者搭建自我展示平台\",\n" +
                "subtitle: \"\",\n" +
                "url: \"https://new.qq.com/omn/20201125/20201125A0H7HQ00.html\",\n" +
                "thumb_nail: \"http://inews.gtimg.com/newsapp_ls/0/12822170183_150120/0\",\n" +
                "thumb_nail_2x: \"http://inews.gtimg.com/newsapp_ls/0/12822170183_640330/0\",\n" +
                "top_big_img: [ ],\n" +
                "category_id: \"6\",\n" +
                "category_name: \"sports\",\n" +
                "category_cn: \"体育\",\n" +
                "sub_category_id: \"616\",\n" +
                "sub_category_name: \"sports_tennis\",\n" +
                "sub_category_cn: \"网球\",\n" +
                "status: 4,\n" +
                "tags: [\n" +
                "{\n" +
                "tag_id: \"22054332\",\n" +
                "tag_word: \"市民运动会\",\n" +
                "tag_score: \"0.283536\"\n" +
                "},\n" +
                "{\n" +
                "tag_id: \"8496438\",\n" +
                "tag_word: \"射箭\",\n" +
                "tag_score: \"0.255632\"\n" +
                "},\n" +
                "{\n" +
                "tag_id: \"20250174\",\n" +
                "tag_word: \"反曲弓\",\n" +
                "tag_score: \"0.160369\"\n" +
                "},\n" +
                "{\n" +
                "tag_id: \"9271635\",\n" +
                "tag_word: \"上海\",\n" +
                "tag_score: \"0.131997\"\n" +
                "}\n" +
                "],\n" +
                "media_id: \"5043863\",\n" +
                "media_name: \"文汇\",\n" +
                "point: \"3\",\n" +
                "article_type: 0,\n" +
                "pool_name: \"sh_pool\",\n" +
                "security_field: 1,\n" +
                "article_id: \"20201125A0H7HQ\",\n" +
                "source: \"om\",\n" +
                "comment_id: \"6227831489\",\n" +
                "comment_num: \"0\",\n" +
                "create_time: \"2020-11-25 22:13:30\",\n" +
                "update_time: \"2020-11-25 22:13:30\",\n" +
                "publish_time: \"2020-11-25 21:37:15\",\n" +
                "img_exp_type: \"1\",\n" +
                "img: \"http://inews.gtimg.com/newsapp_ls/0/12822170183_640330/0\"\n" +
                "}");

    }

}
