package com.kaishengit.dao;

import com.kaishengit.pojo.User;

/**
 * Created by lenovo on 2017/2/28.
 */
public class UserDao {
    public void saveUser(User user){
        System.out.println(user.getName()+ " saved!");
    }
}
