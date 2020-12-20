package com.aposs.box.spider.domain.stock.dao;

import com.aposs.box.spider.config.MyMapper;
import com.aposs.box.spider.domain.stock.entity.StockInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Aaron
 * @date 2020/12/19
 */
@Mapper
public interface StockInfoMapper extends MyMapper<StockInfo> {

}
