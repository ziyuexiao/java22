package com.kaishengit.dao;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lenovo on 2016/12/13.
 */
public class Log4jTestCase {

    @Test
    public void testLog(){
        Logger logger= LoggerFactory.getLogger(Log4jTestCase.class);
        //日志级别
        logger.trace("{}—{}，trace message","Mac","你好");
        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");
       // logger.fatal("fatal message");用了slf4j之后最高等级只有error


    }

}
