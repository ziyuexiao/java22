package com.kaishengit.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by lenovo on 2017/1/4.
 */
public class SqlSessionFactoryUtil {
    private static SqlSessionFactory sessionFactory = buildSqlSessionFactory() ;
    private static SqlSessionFactory buildSqlSessionFactory(){


        try {
            //从classpath中读取mybatis.xml配置文件
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            //根据SqlSessionFactoryBuilder对象构建SqlSessionFactory
            return new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
           throw new RuntimeException("读取mybatis.xml文件异常",e);
        }

    }
    public static SqlSessionFactory getSessionFactory(){
        return sessionFactory;
    }
    public static SqlSession getSqlSession(){
        //根据SqlSessionFactory对象创建SqlSession对象
        return getSessionFactory().openSession();
    }
    public static SqlSession getSqlSession( boolean isAutoCommit){
        return getSessionFactory().openSession(isAutoCommit);//获取一个自动提交事务的SqlSession对象
    }


}
