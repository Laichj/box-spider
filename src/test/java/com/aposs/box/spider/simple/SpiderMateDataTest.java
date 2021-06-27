package com.aposs.box.spider.simple;

import com.alibaba.fastjson.JSONObject;
import com.aposs.box.spider.constant.enums.SpiderTypeEnum;
import com.aposs.box.spider.converter.SpiderMateDataConverter;
import com.aposs.box.spider.dao.SpiderMateDataDao;
import com.aposs.box.spider.model.dto.SpiderMateDataDto;
import com.aposs.box.spider.model.entity.SpiderMateData;
import com.aposs.box.spider.service.SimpleSpiderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Date 2021/6/20
 * @Created by Aaron
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderMateDataTest {

    @Resource
    private SpiderMateDataDao spiderMateDataDao;

    @Resource
    private SimpleSpiderService simpleSpiderService;

    @Test
    public void test(){
        List<SpiderMateData> spiderMateDataList = spiderMateDataDao.querySpiderMateData(SpiderTypeEnum.SIMPLE);
        List<SpiderMateDataDto> spiderMateDataDtoList = spiderMateDataList.stream().map(SpiderMateDataConverter::convertDto).collect(Collectors.toList());
        spiderMateDataDtoList.forEach(i->System.out.println(JSONObject.toJSONString(i)));

    }

    @Test
    public void simpleSpiderServiceTest(){
        simpleSpiderService.runAllSimpleSpider();
    }



    public void jsonTest(){
        JSONObject pipeLineProperty = new JSONObject();
        JSONObject processProperty = new JSONObject();

        JSONObject position3 = new JSONObject();
        position3.put("key","rankings");
        position3.put("type","array");

        JSONObject position2 = new JSONObject();
        position2.put("key","0");
        position2.put("type","object");
        position2.put("next",position3);


        JSONObject position = new JSONObject();
        position.put("key","results");
        position.put("type","array");
        position.put("next",position2);


        processProperty.put("position",position);

        System.out.println(position.toJSONString());
        System.out.println(processProperty);
    }


}
