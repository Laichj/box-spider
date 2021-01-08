package com.aposs.box.spider.domain.stock.dao;

import com.aposs.box.spider.config.MyMapper;
import com.aposs.box.spider.domain.stock.entity.TradingDateRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author Aaron
 * @date 2020/12/20
 */
@Mapper
public interface TradingDateRecordMapper extends MyMapper<TradingDateRecord> {
    LocalDate selectMaxDateRecord();

    TradingDateRecord selectByTradingDate(@Param("tradingDate") LocalDate tradingDate);

}
