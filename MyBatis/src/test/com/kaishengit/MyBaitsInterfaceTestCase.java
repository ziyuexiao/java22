package com.kaishengit;

import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.User;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * Created by lenovo on 2017/1/4.
 */

/**
 * 使用Mapper接口，动态代理模式
 */
public class MyBaitsInterfaceTestCase {
    @Test
    public void findByid(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        //自动产生一个UserMapper接口的实现类（代理对象）
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findByid(1);
        System.out.println(user);

        sqlSession.close();

    }
    @Test
    public void save(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("老吴");
        user.setAddress("郑州");

        userMapper.save(user);

        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void update(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findByid(4);
        user.setUsername("老陈");
        userMapper.update(user);

        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void delete(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.del(2);
        sqlSession.commit();
        sqlSession.close();
    }
}
