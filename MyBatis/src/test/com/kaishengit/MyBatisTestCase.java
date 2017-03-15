package com.kaishengit;

import com.kaishengit.pojo.User;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by lenovo on 2017/1/4.
 */
public class MyBatisTestCase {
    @Test
    public void readXml(){


        try {
            //1. 读取classpath中的配置文件
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            //2. 构建SqlSessionFactory对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            //3创建Sqlsession对象
            SqlSession sqlSession = sqlSessionFactory.openSession();

            //需要执行增删改查的操作

            //4释放资源
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findById() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();

        User user = sqlSession.selectOne("com.kaishengit.mapper.UserMapper.findByid",1);
        System.out.println(user);

        sqlSession.close();
    }

    @Test
    public void findAll(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();

        List<User> userList = sqlSession.selectList("com.kaishengit.mapper.UserMapper.findAll");
        for (User user:userList){
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void save() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();

        User user = new User();
        user.setUsername("王五");
        user.setAddress("河南");

        sqlSession.insert("com.kaishengit.mapper.UserMapper.save",user);

        System.out.println(user.getId());

        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void update() {
        SqlSession session = SqlSessionFactoryUtil.getSqlSession(true);

        User user = session.selectOne("com.kaishengit.mapper.UserMapper.findByid",1);
        user.setUsername("李四");

        session.update("com.kaishengit.mapper.UserMapper.update",user);

        session.close();
    }

    @Test
    public void del() {
        SqlSession session = SqlSessionFactoryUtil.getSqlSession(true);
        session.delete("com.kaishengit.mapper.UserMapper.del",2);
        session.close();
    }
}
