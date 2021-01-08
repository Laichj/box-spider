package com.aposs.box.spider.utils.net;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/**
 * @author Aaron
 * @date 2020/12/20
 */

public class HttpUtil {

//    public static String

    public static String sendRequest(String requestUrl, String requestType, String proxyHost, Integer proxyPort) {
        HttpURLConnection con;
        StringBuilder resultBuffer;
        InputStream respInputStream;
        InputStreamReader respInputStreamReader;
        BufferedReader respBufferedReader;
        try {
            URL url = new URL(requestUrl);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
            //得到连接对象
            con = (HttpURLConnection) url.openConnection(proxy);
            //设置请求类型
            con.setRequestMethod(requestType);
            //设置请求需要返回的数据类型和字符集类型
            con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            //得到响应码
            int responseCode = con.getResponseCode();
            System.out.println(con.getResponseCode());
            System.out.println(con.getResponseMessage());

            if (responseCode == HttpURLConnection.HTTP_OK) {
                //得到响应流
                respInputStream = con.getInputStream();
                //将响应流转换成字符串
                resultBuffer = new StringBuilder();
                String line;
                respInputStreamReader = new InputStreamReader(respInputStream, "UTF-8");
                respBufferedReader = new BufferedReader(respInputStreamReader);
                while ((line = respBufferedReader.readLine()) != null) {
                    resultBuffer.append(line);
                }
                respBufferedReader.close();
                respInputStreamReader.close();
                respInputStream.close();
                return resultBuffer.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        String url = "http://aposs.cn:8083/network/v1/ip";
//        String url = "https://www.baidu.com";
        System.out.println(sendRequest(url, "GET", "47.98.112.7", 80));
    }
}
