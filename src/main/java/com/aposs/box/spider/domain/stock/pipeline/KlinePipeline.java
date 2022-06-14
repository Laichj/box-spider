package com.aposs.box.spider.domain.stock.pipeline;

import com.aposs.box.spider.domain.stock.dao.KlineMapper;
import com.aposs.box.spider.domain.stock.entity.Kline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Aaron
 * @date 2020/12/19
 */
@Component
public class KlinePipeline implements Pipeline {
    private Logger logger = LoggerFactory.getLogger(KlinePipeline.class);

    @Resource
    private KlineMapper klineMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        if (resultItems.isSkip()) {
            return;
        }
        List<Kline> dataList = resultItems.get("dataList");
        String code = resultItems.get("code");
        String name = resultItems.get("name");
        if (dataList.isEmpty()) {
            logger.warn("KlinePipeline Warn!dateList is empty! code:{}, name:{}", code, name);
            return;
        }
        dataList.forEach(kline -> {
            Optional<Kline> optionalKline = selectKlineByCodeAndTradingDate(code, kline.getTradingDate());
            if (!optionalKline.isPresent()) {
                klineMapper.insertSelective(kline);
            }
        });
        logger.info("KlinePipeline success! code:{}, name:{}, size:{}", code, name, dataList.size());

    }

    private Optional<Kline> selectKlineByCodeAndTradingDate(String code, LocalDate tradingDate) {
        Kline kline = new Kline();
        kline.setCode(code);
        kline.setTradingDate(tradingDate);
        Kline record = klineMapper.selectOne(kline);
        return Optional.ofNullable(record);
    }
}
