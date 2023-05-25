package com.pml.cloud.utils.http;


import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/5/4
 * @description： HttpClient工具类
 * @modifiedBy：
 * @version: 1.0
 */
public class HttpClientUtil {

    private static CloseableHttpClient httpClient = null;

    static {

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        // 总连接池连接数量
        connectionManager.setMaxTotal(1500);
        // socket config 可自定义
        // httpclient 现在的默认socket超时时间是3分钟，如果需要调整请自己定义socket config进行定义
        // private static final Timeout DEFAULT_SOCKET_TIMEOUT = Timeout.ofMinutes(3);
        connectionManager.setDefaultSocketConfig(SocketConfig.DEFAULT);

        // 可为每个域名设置最大的并行连接数量
        // connectionManager.setMaxPerRoute(new HttpRoute(new HttpHost("xx.xx.xx.xx")), 80);
        connectionManager.setDefaultMaxPerRoute(100);


        // setConnectTimeout：设置建立连接的超时时间
        // setConnectionRequestTimeout：从连接池中拿连接的等待超时时间
        // setSocketTimeout：发出请求后等待对端应答的超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                // 保持长连接
                .setConnectionKeepAlive(TimeValue.of(600, TimeUnit.SECONDS))
                // 连接超时时间
                .setConnectTimeout(Timeout.of(1000, TimeUnit.SECONDS))
                // 请求超时时间
                .setConnectionRequestTimeout(Timeout.of(1000, TimeUnit.SECONDS))
                // 和上面二选一
                // .setDefaultKeepAlive(1000, TimeUnit.SECONDS)
                .build();

        // 重试处理器，StandardHttpRequestRetryHandler
        // HttpRequestRetryHandler retryHandler = new StandardHttpRequestRetryHandler();

        // BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        // credentialsProvider.setCredentials(new AuthScope());

        httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                // .setDefaultCredentialsProvider(credentialsProvider)
                // .setRetryHandler(retryHandler)
                .build();
    }


    /**
     * 发送post请求
     * @param url 请求地址
     * @param data  请求参数
     * @return
     */
    public static String doPost(String url,String data) {

        if(url == null) {
            return null;
        }
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
        httpPost.setHeader("Accept", "application/json");
        if(data != null) {
            StringEntity stringEntity = new StringEntity(data, ContentType.parse("UTF-8"));
            httpPost.setEntity(stringEntity);
        }
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            if(httpResponse != null) {
                HttpEntity httpEntity = httpResponse.getEntity();
                if(httpEntity != null) {
                    String result = EntityUtils.toString(httpEntity);
                    return result;
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 发送get请求
     * @param url 请求地址
     * @return
     */
    public static String doGet(String url){

        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse  response = httpClient.execute(httpGet);
            if(response.getCode() == 200) {

                String jsonResult = EntityUtils.toString(response.getEntity());
                return jsonResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
