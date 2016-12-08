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
        //�������Է�������Ŀͻ���
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //����һ��Get����ʽ
        HttpGet httpGet = new HttpGet(url);

        try {//�������󲢽��ܷ���˵���Ӧ
            HttpResponse response = httpClient.execute(httpGet);

            InputStream inputstream = null;
            if(response.getStatusLine().getStatusCode()==200){//��ȡHttp��Ӧ��״̬��
                //��ȡ��Ӧ������
                inputstream=response.getEntity().getContent();
                String result = IOUtils.toString(inputstream, "UTF-8");

                httpClient.close();
                return result;
            }else {
                throw new RuntimeException("����" + url + "�쳣 : " + response.getStatusLine().getStatusCode());
            }

        } catch (IOException e) {
            throw new RuntimeException("����" + url + "�쳣",e);
        }




    }
}
