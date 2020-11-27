-- mongoDB相关sql

-- 腾讯新闻集合 发布时间倒序 索引
db.tencentNewsJson.createIndex({'publish_time',-1})
db.ifengNewsJson.createIndex({'publish_time',-1})