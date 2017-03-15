package com.kaishengit.service;

import com.kaishengit.entity.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lenovo on 2016/12/13.
 */
public class UserServiceWithGuavaTest {
    private UserService userService = new UserService();

    @Test
    public void findById() throws Exception {
        User user = userService.findById(7);
        user = userService.findById(7);

        System.out.println(user);
    }

}