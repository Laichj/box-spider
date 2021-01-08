package com.aposs.box.spider.domain.stock;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aposs.box.spider.domain.stock.entity.Kline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Aaron
 * @date 2020/12/19
 */
@Component
public class KlineProcessor implements PageProcessor {
    private static Logger logger = LoggerFactory.getLogger(KlineProcessor.class);

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {
        String pageString = page.getRawText();
        int startIndex = pageString.indexOf("(");
        int endIndex = pageString.lastIndexOf(")");
        String jsonString = pageString.substring(startIndex + 1, endIndex);
        JSONObject pageJson = JSONObject.parseObject(jsonString);
        JSONObject dataJson = pageJson.getJSONObject("data");
        String code = dataJson.getString("code");
        String name = dataJson.getString("name");
        JSONArray klinesArray = dataJson.getJSONArray("klines");
        List<Kline> klineList = new ArrayList<>();
        try {
            if (klinesArray != null && !klinesArray.isEmpty()) {
                int size = klinesArray.size();
                for (int i = 0; i < size; i++) {
                    klineList.add(toObject(code, name, klinesArray.getString(i)));
                }
            }
        } catch (ParseException e) {
            logger.error("ParseException msg: {}", e.getMessage());
        }

        page.putField("dataList", klineList);
        page.putField("code", code);
        page.putField("name", name);
    }

    @Override
    public Site getSite() {
        return this.site;
    }

    /**
     * 将字符串转换为Kline对象
     *
     * @param data 示例数据：2000-11-27,7.38,7.61,7.73,7.28,49762,37562000.00,6.06,2.56,0.19,0.00
     * @return
     */
    private Kline toObject(String code, String name, String data) throws ParseException {
        String[] split = data.split(",");
        Kline kline = new Kline();
        kline.setCode(code);
        kline.setName(name);
        kline.setTradingDate(string2LocalDate(split[0]));
        kline.setOpeningPrice(new BigDecimal(split[1]));
        kline.setClosingPrice(new BigDecimal(split[2]));
        kline.setPeakPrice(new BigDecimal(split[3]));
        kline.setBottomPrice(new BigDecimal(split[4]));
        kline.setTradingVolume(new Long(split[5]));
        kline.setTradingAmount(new BigDecimal(split[6]));
        kline.setAmplitudeRate(new BigDecimal(split[7]));
        kline.setChangeRate(new BigDecimal(split[8]));
        kline.setChangeAmount(new BigDecimal(split[9]));
        kline.setTurnoverRate(new BigDecimal(split[10]));
        return kline;
    }

    private Date string2Date(String dateString) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.parse(dateString);
    }

    private LocalDate string2LocalDate(String dateString) throws ParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.from(dateTimeFormatter.parse(dateString));
    }
}
