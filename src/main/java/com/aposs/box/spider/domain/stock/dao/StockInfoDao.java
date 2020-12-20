package com.aposs.box.spider.domain.stock.dao;

import com.aposs.box.spider.domain.stock.entity.StockInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Aaron
 * @date 2020/12/19
 */
@Repository
public class StockInfoDao {
    @Resource
    private StockInfoMapper stockInfoMapper;

    /**
     * 根据code范围获取股票信息
     *
     * @param startCode 开始编码
     * @param endCode   结束编码
     * @return
     */
    public List<StockInfo> getStockInfoRange(String startCode, String endCode) {
        Example example = new Example(StockInfo.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(startCode)) {
            criteria.andGreaterThanOrEqualTo("code", startCode);
        }
        if (StringUtils.isNotEmpty(endCode)) {
            criteria.andLessThanOrEqualTo("code", endCode);
        }
        return stockInfoMapper.selectByExample(example);
    }

    public StockInfo getStockInfoByCode(String code) {
        if (code == null) {
            return null;
        }
        StockInfo stockInfo = new StockInfo();
        stockInfo.setCode(code);
        return stockInfoMapper.selectOne(stockInfo);
    }


}
