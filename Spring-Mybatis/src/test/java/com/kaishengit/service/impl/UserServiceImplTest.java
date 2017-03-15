package com.kaishengit.service.impl;

import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by lenovo on 2017/1/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Test
    public void save() throws Exception {
        User user=new User("tom","222");
        userService.save(user);
    }

    @Test
    public void findById() throws Exception {
        User user = userService.findById(3758);
        System.out.println(user);
    }

}