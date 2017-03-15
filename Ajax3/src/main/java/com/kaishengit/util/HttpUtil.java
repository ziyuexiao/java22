package com.kaishengit.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lenovo on 2016/12/8.
 */
public class HttpUtil {
    public static String sendHttpGetRequestWithString(String url){
        //创建了一个可以发出请求的客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个get请求方式
        HttpGet httpGet = new HttpGet(url);

        try {//发出请求并接受服务端的响应
            HttpResponse response = httpClient.execute(httpGet);

            InputStream inputstream = null;
            if(response.getStatusLine().getStatusCode()==200){//获取HTTP响应的状态码
                //获取响应输入流
                inputstream=response.getEntity().getContent();
                String result = IOUtils.toString(inputstream, "UTF-8");

                httpClient.close();
                return result;
            }else {
                throw new RuntimeException("请求" + url + "异常 : " + response.getStatusLine().getStatusCode());
            }

        } catch (IOException e) {
            throw new RuntimeException("请求" + url + "异常",e);
        }




    }
}
