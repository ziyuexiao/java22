package com.kaishengit.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by lenovo on 2016/12/15.
 */
public class Config {

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("读取config文件异常",e);
        }
    }


    public static String get(String key){
        return properties.getProperty(key);
    }

}
