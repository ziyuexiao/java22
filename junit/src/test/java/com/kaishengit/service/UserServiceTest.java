package com.kaishengit.service;

import com.kaishengit.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lenovo on 2016/12/13.
 */
public class UserServiceTest {
    private UserService userService = new UserService();
    @Test
    public void findById() throws Exception {

        User user = userService.findById(3);
        user=userService.findById(3);
        System.out.println(user);

    }

    @Test
    public void findAll() throws Exception {

        List<User> userList = userService.findAll();
        userList = userService.findAll();

        System.out.println(userList);
    }

    @Test
    public void save() throws Exception {

        List<User> userList = userService.findAll();
        int beforeSize = userList.size();
        System.out.println("SIZE:" + userList.size());

        User user = new User();
        user.setName("Ehcache");
        user.setAge(23);
        user.setAddress("英国");
        user.setTel("666");
        userService.save(user);

        userList = userService.findAll();
        int afterSize = userList.size();
        System.out.println("SIZE:" + userList.size());

       assertEquals(beforeSize+1,afterSize);

    }

    @Test
    public void del() throws Exception {

    }

}