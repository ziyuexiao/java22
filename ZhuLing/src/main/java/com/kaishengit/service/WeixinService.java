package com.kaishengit.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.Gson;
import com.kaishengit.dto.weixin.User;
import com.kaishengit.exception.ServiceException;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by lenovo on 2017/2/25.
 */
@Service
public class WeixinService {
    private static Logger logger = LoggerFactory.getLogger(WeixinService.class);

    private static final String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={0}&corpsecret={1}";
    //创建用户
    private static final String CREATE_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token={0}";
    //更改用户
    private static final String EDIT_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token={0}";


    @Value("${weixin.token}")
    private String token;
    @Value("${weixin.encodingAESKey}")
    private String aesKey;
    @Value("${weixin.corpid}")
    private String corpid;
    @Value("${weixin.secret}")
    private String secret;

    /**
     * 缓存
     */
    private LoadingCache<String,String> cache = CacheBuilder.newBuilder()
            .maximumSize(10)
            .expireAfterWrite(7200, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    String url = MessageFormat.format(ACCESS_TOKEN_URL,corpid,secret);//MessageFormat帮助将占位符中的值进行代替

                    /**
                     * 发出一个get请求
                     */
                    OkHttpClient httpClient = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    Response response = httpClient.newCall(request).execute();
                    String result = response.body().string();//string()将内容转换为字符串
                    response.close();
                    /**
                     * 解析字符串
                     */
                    Map<String,Object> map = new Gson().fromJson(result, HashMap.class);
                    if(map.containsKey("errcode")) {
                        logger.error("获取微信AccessToken异常:{}",map.get("errcode"));
                        throw new ServiceException("获取AccessToken异常:" + map.get("errcode"));
                    } else {
                        return map.get("access_token").toString();
                    }
                }
            });

    /**
     * 微信企业号初始化方法
     * @return
     */
    public String init(String msg_signature,String timestamp,String nonce,String echostr) {
        try {
            WXBizMsgCrypt crypt = new WXBizMsgCrypt(token,aesKey,corpid);
            return crypt.VerifyURL(msg_signature,timestamp,nonce,echostr);
        } catch (AesException e) {
            throw new ServiceException("微信初始化异常",e);
        }
    }

    /**
     * 获取微信的AccessToken
     * @return
     */
    public String getAccessToken() {
        try {
            return cache.get("");
        } catch (ExecutionException e) {
            throw new ServiceException("获取AccessToken异常",e);
        }
    }

    /**
     * 微信创建用户,发出post请求
     * @param user
     */
    public void saveUser(User user) {
        String url = MessageFormat.format(CREATE_USER_URL,getAccessToken());

        String json = new Gson().toJson(user);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),json);
        Request request = new Request.Builder().post(requestBody).url(url).build();//发出post请求
        try {
            Response response = new OkHttpClient().newCall(request).execute();
            String resultJson = response.body().string();
            response.close();

            Map<String,Object> result = new Gson().fromJson(resultJson,HashMap.class);
            Object errorCode = result.get("errcode");
            if(!"0".equals(errorCode.toString())) {
                logger.error("微信创建用户异常:{}",resultJson);
                throw new ServiceException("微信创建用户异常:"+resultJson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 微信修改用户
     */
    public void editUser(User user) {
        String url = MessageFormat.format(EDIT_USER_URL,getAccessToken());

        String json = new Gson().toJson(user);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),json);
        Request request = new Request.Builder().post(requestBody).url(url).build();//发出post请求
        try {
            Response response = new OkHttpClient().newCall(request).execute();
            String resultJson = response.body().string();
            response.close();

            Map<String,Object> result = new Gson().fromJson(resultJson,HashMap.class);
            Object errorCode = result.get("errcode");
            if(!"0".equals(errorCode.toString())) {
                logger.error("微信修改用户异常:{}",resultJson);
                throw new ServiceException("微信修改用户异常:"+resultJson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
