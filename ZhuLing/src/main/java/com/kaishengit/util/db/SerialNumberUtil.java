package com.kaishengit.util.db;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;

/**
 * Created by lenovo on 2017/2/17.
 */
public class SerialNumberUtil {
    public static String getSerialNumber() {
        DateTime now = new DateTime();
        String result = now.toString("YYYYMMDDHHmmss");
        result += RandomStringUtils.randomNumeric(4);
        return result;
    }

}
