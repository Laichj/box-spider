package com.aposs.box.spider.domain.stock.pipeline;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aposs.box.spider.domain.stock.dao.StockInfoMapper;
import com.aposs.box.spider.domain.stock.entity.StockInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;

/**
 * @author Aaron
 * @date 2020/12/19
 */
@Component
public class StockInfoPipeline implements Pipeline {
    private Logger logger = LoggerFactory.getLogger(StockInfoPipeline.class);

    @Resource
    private StockInfoMapper stockInfoMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        if (resultItems.isSkip()) {
            return;
        }
        JSONArray dataList = resultItems.get("dataList");
        if (dataList != null && !dataList.isEmpty()) {
            int size = dataList.size();
            for (int i = 0; i < size; i++) {
                JSONObject stockInfoJson = dataList.getJSONObject(i);
                String code = stockInfoJson.getString("f12");
                String name = stockInfoJson.getString("f14");
                StockInfo stockInfo = new StockInfo();
                stockInfo.setCode(code);
                stockInfo.setName(name);
                stockInfoMapper.insert(stockInfo);
            }
            logger.info("save StockInfoPipeline to Mysql success! size:{}", size);
        }

    }
}
