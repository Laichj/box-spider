package com.aposs.box.spider.converter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aposs.box.spider.model.dto.SpiderMateDataDto;
import com.aposs.box.spider.model.entity.PipeLineProperty;
import com.aposs.box.spider.model.entity.ProcessProperty;
import com.aposs.box.spider.model.entity.SpiderMateData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 转换器
 *
 * @Date 2021/6/20
 * @Created by Aaron
 */
public class SpiderMateDataConverter {
    /**
     * entity convert to dto
     *
     * @param entity
     * @return
     */
    public static SpiderMateDataDto convertDto(SpiderMateData entity) {
        PipeLineProperty pipeLineProperty = JSONObject.parseObject(entity.getPipeLineProperty(), PipeLineProperty.class);
        ProcessProperty processProperty = JSONObject.parseObject(entity.getProcessProperty(), ProcessProperty.class);

        SpiderMateDataDto spiderMateDataDto = SpiderMateDataDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .spiderType(entity.getSpiderType())
                .spiderName(entity.getSpiderName())
                .spiderUrl(entity.getSpiderUrl())
                .spiderStatus(entity.getSpiderStatus())
                .schedulerId(entity.getSchedulerId())
                .pipeLineProperty(pipeLineProperty)
                .processProperty(processProperty)
                .build();

        return spiderMateDataDto;
    }



}
