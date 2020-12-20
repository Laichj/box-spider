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


    public static void main(String[] args) {
//        System.out.println(checkProxy("101.132.143.232", 80));
//        System.out.println(checkProxy("117.185.17.151", 80));
//        System.out.println(checkProxy("117.185.17.145", 80));
//        System.out.println(checkProxy("47.100.171.38", 8080));
//        System.out.println(checkProxy("120.221.86.164", 80, url));
//        System.out.println(checkProxy("117.185.17.144", 80, url));
//        System.out.println(checkProxy("120.232.150.110", 80, url));
//        System.out.println(checkProxy("183.47.237.251", 80, url));
//        System.out.println(checkProxy("222.89.28.218", 1080));

//        String url = "http://www.baidu.com";
        String url = "https://www.aposs.cn:8083/network/v1/ip";
//        String url = "http://www.qq.com/?fromdefault";

        List<Proxy> proxyList = new ArrayList<>();
//        proxyList.add(new Proxy("117.185.17.151", 80));
//        proxyList.add(new Proxy("117.185.17.145", 80));
//        proxyList.add(new Proxy("117.185.17.16", 80));
//        proxyList.add(new Proxy("120.221.86.164", 80));
        proxyList.add(new Proxy("117.185.17.144", 80));
        proxyList.add(new Proxy("120.232.150.110", 80));
        proxyList.add(new Proxy("183.47.237.251", 80));
//        proxyList.add(new Proxy("45.167.197.11", 80));

        checkProxyList(proxyList, url).forEach(result -> System.out.println(result));
    }

    /**
     * 验证代理服务器
     *
     * @return 请求失败返回-1，请求成功返回请求消耗时间
     */
    public static Integer checkProxy(String ip, Integer port) {
        Proxy proxy = new Proxy(ip, port);
        String url = "http://aposs.cn:8083/network/v1/ip";
//        String url = "http://www.baidu.com";
//        String url = "http://qq.com";
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
