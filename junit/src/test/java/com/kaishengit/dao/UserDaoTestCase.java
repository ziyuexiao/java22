package com.kaishengit.dao;

import com.kaishengit.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*; //静态导入


/**
 * Created by lenovo on 2016/12/12.
 */
public class UserDaoTestCase {

    private UserDao userDao;

    @Before
    public void before() {
        //....
        userDao = new UserDao();
    }
    @Test
    public void testSave(){

        User user = new User();
        user.setName("刘");
        user.setAge(4);
        user.setTel("000");
        user.setAddress("河北");

        userDao.save(user);
    }

    @Test
    public void testFindById() {
        User user = userDao.findById(10);

        assertNotNull(user);//断言类
    }

    @Test
    public  void testFindAll(){
        List<User> userList = userDao.findAll();
        assertEquals(7,userList.size());


    }


    @Test
    public void testDel() {
        userDao.del(1);
    }

    @Test
    public void testSystem(){
        String str = System.getProperty("java.io.tmpdir");//C:\Users\lenovo\AppData\Local\Temp\

        System.out.println(str);

        System.out.println(System.getProperty("java.version"));//JDK版本
        System.out.println(System.getProperty("java.home"));//C:\Program Files\Java\jdk1.8.0_101\jre
        System.out.println(System.getProperty("os.name"));//Windows 10
    }

}
