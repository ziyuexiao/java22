package com.kaishengit;

import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.User;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.Array;
import java.util.*;

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
   /* *//*参数列表不唯一的方法一*//*
    @Test
    public void findByusernameAndaddress(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findByusernameAndaddress("李四","焦作");
        System.out.println(user);
        sqlSession.close();
    }*/

   /* /*//*参数列表不唯一的方法2*//*/
    @Test
    public void findByusernameAndaddress(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findByusernameAndaddress2("李四","焦作");
        System.out.println(user);
        sqlSession.close();
    }*/

   /* /*//*参数列表不唯一的方法3*//*/
    @Test
    public void findByusernameAndaddress(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        Map<String,Object> param = new HashMap<>();
        param.put("name","李四");
        param.put("add","焦作");

        User user = userMapper.findByusernameAndaddress3(param);
        System.out.println(user);
        sqlSession.close();

    }*/
    @Test
    public void findByParam(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Map<String,Object> param = new HashMap<>();
       // param.put("username","李四");
        param.put("address","焦作");
        User user = userMapper.findByParam(param);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void findByids(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
       // List<User> userList = userMapper.findByids(Arrays.asList(1,3,5));
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(3);
        idList.add(5);
        List<User> userList = userMapper.findByids(idList);
        for (User user:userList){
            System.out.println(user);
        }


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
    public void batchSave(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user1 = new User("周","北京");
        User user2 = new User("刘","济宁");
        User user3 = new User("吴","上海");
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userMapper.batchSave(userList);

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
    @Test
    public void findBycache1(){
        //一级缓存:同一个Sqlsession中，默认开启
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findByid(1);
        user = userMapper.findByid(1);
        user = userMapper.findByid(1);
        //只执行了一次sql
        System.out.println(user);

    }
    @Test
    public void findBycache2(){
        //二级缓存（默认关闭）同一个SqlSessionFactory中
        SqlSession sqlSession1 = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        User user = userMapper1.findByid(1);
        System.out.println(user);

        SqlSession sqlSession2 = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        user = userMapper2.findByid(1);
        System.out.println(user);
    }

}
