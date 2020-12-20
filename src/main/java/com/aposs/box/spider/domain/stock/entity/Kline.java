package com.aposs.box.spider.domain.stock.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * k线数据实体
 *
 * @author Aaron
 * @date 2020/12/19
 */
@Table(name = "tbl_kline")
public class Kline {
    @Id
    private Long id;
    // 股票代码
    private String code;
    // 股票名称
    private String name;
    // 交易日期
    private Date tradingDate;
    // 开盘价格（单位：元）
    private BigDecimal openingPrice;
    // 收盘价格（单位：元）
    private BigDecimal closingPrice;
    // 当天最高价格（单位：元）
    private BigDecimal peakPrice;
    // 当天最底价格（单位：元）
    private BigDecimal bottomPrice;
    // 涨跌幅（百分比%）
    private BigDecimal changeRate;
    // 涨跌额（单位：元）
    private BigDecimal changeAmount;
    // 成交量（单位：手）
    private Long tradingVolume;
    // 成交额（单位：元）
    private BigDecimal tradingAmount;
    // 振幅（百分比%）
    private BigDecimal amplitudeRate;
    // 换手率（百分比%）
    private BigDecimal turnoverRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(Date tradingDate) {
        this.tradingDate = tradingDate;
    }

    public BigDecimal getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(BigDecimal openingPrice) {
        this.openingPrice = openingPrice;
    }

    public BigDecimal getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(BigDecimal closingPrice) {
        this.closingPrice = closingPrice;
    }

    public BigDecimal getPeakPrice() {
        return peakPrice;
    }

    public void setPeakPrice(BigDecimal peakPrice) {
        this.peakPrice = peakPrice;
    }

    public BigDecimal getBottomPrice() {
        return bottomPrice;
    }

    public void setBottomPrice(BigDecimal bottomPrice) {
        this.bottomPrice = bottomPrice;
    }

    public BigDecimal getChangeRate() {
        return changeRate;
    }

    public void setChangeRate(BigDecimal changeRate) {
        this.changeRate = changeRate;
    }

    public BigDecimal getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }

    public Long getTradingVolume() {
        return tradingVolume;
    }

    public void setTradingVolume(Long tradingVolume) {
        this.tradingVolume = tradingVolume;
    }

    public BigDecimal getTradingAmount() {
        return tradingAmount;
    }

    public void setTradingAmount(BigDecimal tradingAmount) {
        this.tradingAmount = tradingAmount;
    }

    public BigDecimal getAmplitudeRate() {
        return amplitudeRate;
    }

    public void setAmplitudeRate(BigDecimal amplitudeRate) {
        this.amplitudeRate = amplitudeRate;
    }

    public BigDecimal getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(BigDecimal turnoverRate) {
        this.turnoverRate = turnoverRate;
    }
}