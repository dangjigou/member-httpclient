package com.member.test.utils.httputils;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.*;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * httpClient，提供发送post请求的方法
 *
 * Created by jackson on 15/11/23.
 *
 */
public class HttpClient {

    public static DefaultHttpClient httpclient = getHttpClient();


    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
    private static final String APPLICATION_JSON = "application/json;charset=utf-8";


    private static Logger log = Logger.getLogger(HttpClient.class);


    /**
     * 适合多线程的HttpClient,用httpClient4.2.1实现
     *
     * @return DefaultHttpClient
     */
    public static DefaultHttpClient getHttpClient() {
        // 设置组件参数, HTTP协议的版本,1.1/1.0/0.9
        HttpParams params = new BasicHttpParams();

        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
        HttpProtocolParams.setUseExpectContinue(params, true);

        // 设置连接超时时间
        final int REQUEST_TIMEOUT = 15 * 1000; // 设置请求超时2秒钟
        final int SO_TIMEOUT = 15 * 1000; // 设置等待数据超时时间2秒钟
        final long CONN_MANAGER_TIMEOUT = 500L; //
        // 该值就是连接不够用的时候等待超时时间，一定要设置，而且不能太大
        //

        HttpConnectionParams.setConnectionTimeout(params, REQUEST_TIMEOUT);
        HttpConnectionParams.setSoTimeout(params, SO_TIMEOUT);
        params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, REQUEST_TIMEOUT);
        params.setParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);
        // params.setLongParameter(ClientPNames.CONN_MANAGER_TIMEOUT,CONN_MANAGER_TIMEOUT);
        params.setBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, true);// 在提交请求之前
        // 测试连接是否可用
        // 设置访问协议
        SchemeRegistry schreg = new SchemeRegistry();
        schreg.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        schreg.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));

        // 多连接的线程安全的管理器
        PoolingClientConnectionManager pccm = new PoolingClientConnectionManager(schreg);
        pccm.setMaxTotal(200); // 客户端总并行链接最大数 MaxtTotal是整个池子的大小；
        pccm.setDefaultMaxPerRoute(200); // 每个主机的最大并行链接数

        DefaultHttpClient httpClient = new DefaultHttpClient(pccm, params);
        httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));// 另外设置http
        // client的重试次数，默认是3次；当前是禁用掉（如果项目量不到，这个默认即可）
        return httpClient;
    }



    /**
     * 将入参转化为json，发送http post请求
     * @param url 请求地址
     * @param params 入參
     * @return
     */
    public static String post(String url, Map<String, String> params) {

        httpclient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BEST_MATCH);
        String postType = HttpPostUrlEnum.getTypeByUrl(url);
        String body = null;

        log.info("create httppost:" + url);
        HttpPost post = new HttpPost();
        if ("JSON".equals(postType)){
            post = postJson(url, params);
        }else {
            post = postForm(url,params);
        }

        //调用httpclient方法发送post请求
        body = invoke(httpclient, post);

//        System.out.print("cookies = " + httpclient.getCookieStore().getCookies() + "\n\r");
        return body;
    }

    public static String get(String url) {
//        httpclient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BEST_MATCH);
        String body = null;
        URL tmpurl = null;
        String result = null;
        //httpClient
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            //url入參转换
            tmpurl = new URL(url);
            URI uri = new URI(tmpurl.getProtocol(), tmpurl.getHost() + ":" + tmpurl.getPort(), tmpurl.getPath(), tmpurl.getQuery(), null);

//            HttpGet method = new HttpGet(uri);
//            HttpResponse response = httpClient.execute(method);

            HttpGet get = new HttpGet(uri);
            body = invoke(httpclient, get);

            // 获取状态Code
//            int statusCode = response.getStatusLine().getStatusCode();
//            log.info("statusCode:" + statusCode);
//            if (statusCode != HttpStatus.SC_OK) {
//                log.error("Method failed:" + response.getStatusLine());
//            } else {
//                //返回请求结果
//                String resData = EntityUtils.toString(response.getEntity());
//                resJson = JSONObject.parseObject(resData);
//                result = resJson.toString();
//
//                log.info(resData);
//            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
//        }finally {
//            httpclient.close();
        }
//        System.out.print("cookies = " + httpclient.getCookieStore().getCookies() + "\n\r");
//        return result;
        return body;
    }

    /*
	 * 数据内容采用multipart/form-data方式上传
	 */
    public static String getHttpResponseMultipartFormDataByPost(String url,String fileName,String filePath) throws ClientProtocolException, IOException{
        MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE,"--VANE", Charset.defaultCharset());
        multipartEntity.addPart("name",new StringBody("dp_data", Charset.forName("UTF-8")));
        multipartEntity.addPart("filename",new StringBody(fileName,Charset.forName("UTF-8")));
        multipartEntity.addPart("file",new FileBody(new File(filePath),"text/plain"));
        HttpPost request = new HttpPost(url);
        request.setEntity(multipartEntity);
        request.addHeader("Content-Type","multipart/form-data; boundary=--VANE");

        HttpResponse response =httpclient.execute(request);

        InputStream is = response.getEntity().getContent();
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }

        return buffer.toString();

    }

    /**
     * 关闭http连接
     */
    public void shutDownHttp(){
        httpclient.getConnectionManager().shutdown();

    }

    /**
     * http 调用
     * @param httpclient
     * @param httpost
     * @return
     */
    private static String invoke(DefaultHttpClient httpclient,
                                 HttpUriRequest httpost) {

        HttpResponse response = sendRequest(httpclient, httpost);

        String body = paseResponse(response);

        return body;
    }

    //response转化为字符串
    private static String paseResponse(HttpResponse response) {
        log.info("get response from http server..");
        HttpEntity entity = response.getEntity();


        log.info("response status: " + response.getStatusLine());
        String charset = EntityUtils.getContentCharSet(entity);
        log.info(charset);

        String body = null;
        try {

            body = EntityUtils.toString(entity);
            log.info(body);
            log.info(httpclient.getCookieStore().getCookies());

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return body;
    }

    /**
     * 发送http请求
     * @param httpclient
     * @param httpost
     * @return
     */
    private static HttpResponse sendRequest(DefaultHttpClient httpclient,
                                            HttpUriRequest httpost) {
        log.info("execute post...");
        HttpResponse response = null;

        try {

            response = httpclient.execute(httpost);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 格式化表单形式的post入参
     * @param url
     * @param params
     * @return
     */
    private static HttpPost postForm(String url, Map<String, String> params){

        HttpPost httpost = new HttpPost(url);

        List<NameValuePair> nvps = new ArrayList <NameValuePair>();

        Set<String> keySet = params.keySet();

        for(String key : keySet) {
            nvps.add(new BasicNameValuePair(key, (String) params.get(key)));
        }

        try {
            log.info("set utf-8 form entity to httppost");
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return httpost;
    }

    /**
     * 格式化json形式的post入参
     * @return
     */
    private static HttpPost postJson(String url, Map<String, String> params){
        HttpPost httpost = new HttpPost(url);
        //将map入参转化为json
        org.json.JSONObject jsonObject = new org.json.JSONObject(params);
        // 将JSON进行UTF-8编码,以便传输中文
        String jsonString = jsonObject.toString();
//      String jsonString = URLEncoder.encode(jsonObject.toString(), HTTP.UTF_8);
//      StringEntity se = new StringEntity(jsonString);
        StringEntity se = new StringEntity(jsonString, "UTF-8");
        se.setContentType(APPLICATION_JSON);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));

        log.info("set utf-8 string entity to httppost");
        httpost.setEntity(se);
        return httpost;

    }

}