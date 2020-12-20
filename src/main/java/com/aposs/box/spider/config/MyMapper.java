package com.aposs.box.spider.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author Aaron
 * @date 2020/12/19
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
