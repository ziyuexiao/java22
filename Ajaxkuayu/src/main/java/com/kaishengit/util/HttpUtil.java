package com.kaishengit.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpUtil {

	public static String sendHttpGetRequestWithString(String url){
		//创建可以发出请求的客户端
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个Get请求方式
		HttpGet httpGet = new HttpGet(url);
		
		try {//发出请求并接受服务端的响应
			HttpResponse response = httpClient.execute(httpGet);
		
			InputStream inputstream = null;
			if(response.getStatusLine().getStatusCode()==200){//获取Http响应的状态码
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
