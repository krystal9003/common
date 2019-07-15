////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by Fernflower decompiler)
////
//
//package com.tuandai.ms.util;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Iterator;
//import org.apache.http.Consts;
//import org.apache.http.HttpEntity;
//import org.apache.http.StatusLine;
//import org.apache.http.client.HttpResponseException;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.codehaus.jackson.JsonNode;
//
//public class HttpUtils {
//    public HttpUtils() {
//    }
//
//    public static String excuteGet(String url) throws Exception {
//        String responseContent = "";
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//
//        try {
//            HttpGet httpGet = new HttpGet(url);
//            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(8000).setConnectTimeout(8000).build();
//            httpGet.setConfig(requestConfig);
//            CloseableHttpResponse response = httpClient.execute(httpGet);
//            responseContent = handleResponse(response);
//        } finally {
//            if (httpClient != null) {
//                try {
//                    httpClient.close();
//                } catch (IOException var11) {
//                    var11.printStackTrace();
//                }
//            }
//
//        }
//
//        return responseContent;
//    }
//
//    public static String excutePost(String url) throws Exception {
//        String responseContent = "";
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//
//        try {
//            HttpPost httpPost = new HttpPost(url);
//            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(8000).setConnectTimeout(8000).build();
//            httpPost.setConfig(requestConfig);
//            httpPost.addHeader("Connection", "close");
//            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=GBK");
//            CloseableHttpResponse response = httpClient.execute(httpPost);
//            responseContent = handleResponse(response);
//        } finally {
//            if (httpClient != null) {
//                try {
//                    httpClient.close();
//                } catch (IOException var11) {
//                    var11.printStackTrace();
//                }
//            }
//
//        }
//
//        return responseContent;
//    }
//
//    private static String handleResponse(CloseableHttpResponse response) throws IOException {
//        StatusLine statusLine = response.getStatusLine();
//        HttpEntity entity = response.getEntity();
//        if (statusLine.getStatusCode() >= 300) {
//            EntityUtils.consume(entity);
//            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
//        } else {
//            return entity == null ? null : EntityUtils.toString(entity, Consts.UTF_8);
//        }
//    }
//
//    public static String excutePost(String url, HashMap<String, String> headers, JsonNode jsonParam) throws Exception {
//        String responseContent = "";
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//
//        try {
//            HttpPost httpPost = new HttpPost(url);
//            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(8000).setConnectTimeout(8000).build();
//            httpPost.setConfig(requestConfig);
//            if (null != headers) {
//                Iterator var7 = headers.keySet().iterator();
//
//                while(var7.hasNext()) {
//                    String key = (String)var7.next();
//                    httpPost.setHeader(key, (String)headers.get(key));
//                }
//            }
//
//            if (null != jsonParam) {
//                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
//                entity.setContentEncoding("UTF-8");
//                entity.setContentType("application/json");
//                httpPost.setEntity(entity);
//            }
//
//            CloseableHttpResponse response = httpClient.execute(httpPost);
//            responseContent = handleResponse(response);
//        } finally {
//            if (httpClient != null) {
//                try {
//                    httpClient.close();
//                } catch (IOException var14) {
//                    var14.printStackTrace();
//                }
//            }
//
//        }
//
//        return responseContent;
//    }
//
//    public static String excuteGet(String url, HashMap<String, String> params) throws Exception {
//        String responseContent = "";
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//
//        try {
//            HttpGet httpGet = new HttpGet(url);
//            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(8000).setConnectTimeout(8000).build();
//            httpGet.setConfig(requestConfig);
//            if (params != null && params.size() > 0) {
//                Iterator var6 = params.keySet().iterator();
//
//                while(var6.hasNext()) {
//                    String key = (String)var6.next();
//                    httpGet.setHeader(key, (String)params.get(key));
//                }
//            }
//
//            CloseableHttpResponse response = httpClient.execute(httpGet);
//            responseContent = handleResponse(response);
//        } finally {
//            if (httpClient != null) {
//                try {
//                    httpClient.close();
//                } catch (IOException var13) {
//                    var13.printStackTrace();
//                }
//            }
//
//        }
//
//        return responseContent;
//    }
//
//    public static void main(String[] args) throws Exception {
//        String url = "http://202.91.244.252/qd/SMSSendYD?usr=5658&pwd=xteyz5658&mobile=13510003820&sms=验证码为1234";
//        String resp = excutePost(url);
//        String[] str = resp.split(",");
//
//        for(int i = 0; i < str.length; ++i) {
//            System.out.println(str[i]);
//        }
//
//    }
//}
