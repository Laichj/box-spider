package com.aposs.box.spider.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.downloader.HttpClientGenerator;
import us.codecraft.webmagic.downloader.HttpClientRequestContext;
import us.codecraft.webmagic.downloader.HttpUriRequestConverter;
import us.codecraft.webmagic.proxy.Proxy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aaron
 * @date 2020/12/7
 */
public class ProxyCheckUtil {

    private static CloseableHttpClient httpClient = new HttpClientGenerator().getClient(Site.me());
    private static String checkHttpUrl = "http://aposs.cn:8083/network/v1/ip";
    private static String checkHttpsUrl = "http://aposs.cn:8083/network/v1/ip";


    public static void main(String[] args) {
//        System.out.println(checkProxy("120.221.86.164", 80, checkHttpUrl));
//        System.out.println(checkProxy("117.185.17.144", 80, checkHttpUrl));
//        System.out.println(checkProxy("120.232.150.110", 80, checkHttpUrl));
//        System.out.println(checkProxy("183.47.237.251", 80, checkHttpUrl));

        List<Proxy> proxyList = new ArrayList<>();
//        proxyList.add(new Proxy("118.194.242.40", 80));
        proxyList.add(new Proxy("47.98.112.7", 80));
//        proxyList.add(new Proxy("113.195.157.137", 9999));
//        proxyList.add(new Proxy("171.35.142.122", 9999));
//
        checkProxyList(proxyList, checkHttpUrl).forEach(result -> System.out.println(result));
    }

    /**
     * 验证代理服务器
     *
     * @return 请求失败返回-1，请求成功返回请求消耗时间
     */
    public static Integer checkProxy(String ip, Integer port, String url) {
        Proxy proxy = new Proxy(ip, port);
        return proxyDownload(proxy, url);
    }


    public static List<String> checkProxyList(List<Proxy> proxyList, String url) {
        List<String> results = new ArrayList<>(proxyList.size());
        proxyList.forEach(proxy -> {
            Integer code = proxyDownload(proxy, url);
            results.add(String.format("ip:%s,port:%d,speed:%dms", proxy.getHost(), proxy.getPort(), code));
        });
        return results;
    }

    /**
     * 验证代理服务器
     *
     * @param proxy
     * @param url
     * @return 请求失败返回-1，请求成功返回请求消耗时间
     */
    private static Integer proxyDownload(Proxy proxy, String url) {
        HttpUriRequestConverter httpUriRequestConverter = new HttpUriRequestConverter();
        HttpClientRequestContext requestContext = httpUriRequestConverter.convert(new Request(url), Site.me(), proxy);
        CloseableHttpResponse httpResponse;
        ClockUtil clockUtil = new ClockUtil();
        try {
            httpResponse = httpClient.execute(requestContext.getHttpUriRequest(), requestContext.getHttpClientContext());
            Long elapsedTime = clockUtil.stopClock();
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                return elapsedTime.intValue();
            }
        } catch (ConnectTimeoutException e) {
            // connect timeout
            return -2;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }


}
