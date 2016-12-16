package com.kaishengit.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Created by lenovo on 2016/12/15.
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static Logger logger = LoggerFactory.getLogger(StringUtils.class);

    /**
     * 将ISO8859-1的字符串转换为UTF-8的字符串
     */
    public static String toUtf8(String str){
        try {
            return new String(str.getBytes("ISO8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
           logger.error("字符串{}转换异常",e);
            throw new RuntimeException("字符串"+str+"转换异常",e);
        }

    }
}
