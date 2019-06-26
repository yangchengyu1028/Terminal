package com.ycy.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;


public class GetAndPost {

    /**
     * 向第三方接口post数据通用方法,传入JSONObject
     * @param jsonString
     * @param url
     * @return
     */
    public String post(JSONObject jsonString, String url) {
        HttpClient httpClient = null;
        HttpPost postMethod = null;
        HttpResponse response = null;
        String reponseContent = null;
        try {

            httpClient = HttpClients.createDefault();

            postMethod = new HttpPost(url);//传入URL地址
//            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60*1000).setConnectTimeout(60*1000).build();//设置请求和传输超时时间
//            postMethod.setConfig(requestConfig);
            //设置请求头
            postMethod.addHeader("Content-type", "application/x-www-form-urlencoded");
            //传入请求参数
            postMethod.setEntity(new StringEntity(jsonString.toString(), Charset.forName("UTF-8")));
            response = httpClient.execute(postMethod);//获取响应
//            int statusCode = response.getStatusLine().getStatusCode();
//            System.out.println("HTTP Status Code:" + statusCode);
//            if (statusCode != HttpStatus.SC_OK) {
//                System.out.println("HTTP请求未成功！HTTP Status Code:" + response.getStatusLine());
//            }
            HttpEntity httpEntity = response.getEntity();
            reponseContent = EntityUtils.toString(httpEntity);
            EntityUtils.consume(httpEntity);//释放资源
            //System.out.println("响应内容：" + reponseContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reponseContent;
    }

    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            //conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //1.获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //2.中文有乱码的需要将PrintWriter改为如下
            //out=new OutputStreamWriter(conn.getOutputStream(),"UTF-8")
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            //System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        //System.out.println("post推送结果："+result);
        return result;
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            //connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public  String postData(String uri, Map<String,Object> map) {
        //所有的参数都放在map中，然后转换成json字符串传递到接口中
        String str = JSONObject.toJSONString(map);
        //System.out.println("json参数："+str);
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setEntity(new StringEntity(str, Charset.forName("utf-8")));
        try {
            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            //System.out.println("http返回码："+statusCode);
            //如果返回状态是200，打印返回信息
            if (statusCode==200) {
                HttpEntity entity = response.getEntity();
                String entityStr = EntityUtils.toString(entity);
                //System.out.println("客户端接收内容:"+entityStr);
                return entityStr;
            }
        } catch (Exception e) {
        }
        return null;
    }

}
