-- 股票爬取数据相关数据表

-- k线数据表
drop table if exists tbl_kline;
create table tbl_kline(
id int(8) AUTO_INCREMENT COMMENT '自增主键',
`code` varchar(8) COMMENT '股票代码',
`name` varchar(128) COMMENT '股票名称',
trading_date date COMMENT '交易日期（yyyy-MM-dd）',
opening_price decimal(10,2) COMMENT '开盘价格（单位：元）',
closing_price decimal(10,2) COMMENT '收盘价格（单位：元）',
peak_price decimal(10,2) COMMENT '当天最高价格（单位：元）',
bottom_price decimal(10,2) COMMENT '当天最底价格（单位：元）',
change_rate decimal(8,2) COMMENT '涨跌幅%',
change_amount decimal(8,2) COMMENT '涨跌额（单位：元）',
trading_volume int(8) COMMENT '成交量（单位：手）',
trading_amount decimal(16,2) COMMENT '成交额（单位：元）',
amplitude_rate decimal(8,2) COMMENT '振幅%',
turnover_rate decimal(8,2) COMMENT '换手率%',
gmt_create datetime DEFAULT CURRENT_TIMESTAMP,
gmt_modified datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (id)
) comment='k线数据表';
create unique index index_tbl_klines_code_date on tbl_kline(code,trading_date);


-- 股票信息表
drop table if exists tbl_stock_info ;
create table tbl_stock_info(
id int(8) AUTO_INCREMENT COMMENT '自增主键',
`code` varchar(8) COMMENT '股票代码',
`name` varchar(128) COMMENT '股票名称',
market int(2) COMMENT '市场（0:深证A股，1:上证A股）',
industry varchar(128) COMMENT '行业（多个行业中间使用逗号隔开）',
city varchar(64) COMMENT '城市',
open_status int(2) COMMENT '股票状态（0:正常交易，1:）',
`type` int(2) COMMENT '股票类型（0:正常，1:ST）',
listing_date date COMMENT '上市日期（yyyy-MM-dd）',
gmt_create datetime DEFAULT CURRENT_TIMESTAMP,
gmt_modified datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (id)
) comment='股票信息表';
create unique index index_tbl_stock_info_code on tbl_stock_info(code);

-- 交易日记录表
drop table if exists tbl_trading_date_record ;
create table tbl_trading_date_record(
id int(8) AUTO_INCREMENT COMMENT '自增主键',
trading_date date COMMENT '交易日期（yyyy-MM-dd）',
gmt_create datetime DEFAULT CURRENT_TIMESTAMP,
gmt_modified datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (id)
) comment='交易日记录表';
create unique index index_tbl_trading_date_record_trading_date on tbl_trading_date_record(trading_date);
