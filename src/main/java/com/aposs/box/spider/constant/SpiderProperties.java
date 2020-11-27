package com.aposs.box.spider.constant;

/**
 * @author Aaron
 * @date 2020/11/27
 * 爬虫相关参数
 */
public class SpiderProperties {
    private String url;
    private String collectionName;
    private Integer limit;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
