package com.aposs.box.spider.domain.stock.entity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Aaron
 * @date 2020/12/19
 */
@Table(name = "tbl_stock_info")
public class StockInfo {
    @Id
    private Long id;
    private String code;
    private String name;
    /**
     * 市场（0:深证A股，1:上证A股）
     */
    private Integer market;
    private String industry;
    private String city;

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

    public Integer getMarket() {
        return market;
    }

    public void setMarket(Integer market) {
        this.market = market;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
