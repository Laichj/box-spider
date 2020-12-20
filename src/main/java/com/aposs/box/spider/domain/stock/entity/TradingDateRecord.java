package com.aposs.box.spider.domain.stock.entity;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author Aaron
 * @date 2020/12/20
 */
@Table(name = "tbl_trading_date_record")
public class TradingDateRecord {
    private Long id;

    private Date tradingDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(Date tradingDate) {
        this.tradingDate = tradingDate;
    }
}
