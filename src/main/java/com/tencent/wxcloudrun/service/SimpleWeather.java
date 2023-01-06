package com.tencent.wxcloudrun.service;




import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.model.Future;
import com.tencent.wxcloudrun.model.OBJ;
import com.tencent.wxcloudrun.model.Weather;
import com.tencent.wxcloudrun.model.YmlProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component

public class SimpleWeather {
    @Resource
    YmlProperties ymlProperties;

    /**
     * 根据城市名查询天气情况
     */
    public OBJ queryWeather() {
        Map<String, Object> params = new HashMap<String, Object>();//组合参数
        params.put("city", ymlProperties.getCITY_NAME());
        params.put("key", ymlProperties.getAPI_KEY());
        String queryParams = urlencode(params);

        String response = doGet(ymlProperties.getAPI_URL(), queryParams);
        try {
            JSONObject jsonObject = JSONObject.parseObject(response);
            int error_code = jsonObject.getInteger("error_code");
            if (error_code == 0) {
                System.out.println("调用接口成功");
                JSONObject result = jsonObject.getJSONObject("result");
                JSONObject req =  result.getJSONObject("realtime");
                JSONArray future = result.getJSONArray("future");
                //当前天气
                Weather weather = req.parseObject(String.valueOf(req), Weather.class);
                //未来五天天气
                List<Future> futures = future.toJavaList(Future.class);
                OBJ obj = new OBJ();
                obj.setWeather(weather);
                obj.setFutures(futures);
                return obj;
            } else {
                System.out.println("调用接口失败：" + jsonObject.getString("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get方式的http请求
     *
     * @param httpUrl 请求地址
     * @return 返回结果
     */
    public static String doGet(String httpUrl, String queryParams) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(new StringBuffer(httpUrl).append("?").append(queryParams).toString());
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(5000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(6000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                // 封装输入流，并指定字符集
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                // 存放数据
                StringBuilder sbf = new StringBuilder();
                String temp;
                while ((temp = bufferedReader.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append(System.getProperty("line.separator"));
                }
                result = sbf.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();// 关闭远程连接
            }
        }
        return result;
    }

    /**
     * 将map型转为请求参数型
     *
     * @param data
     * @return
     */
    public static String urlencode(Map<String, ?> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, ?> i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        String result = sb.toString();
        result = result.substring(0, result.lastIndexOf("&"));
        return result;
    }
}