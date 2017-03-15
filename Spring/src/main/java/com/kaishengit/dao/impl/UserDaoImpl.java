package com.kaishengit.dao.impl;

import com.kaishengit.dao.UserDao;

/**
 * Created by lenovo on 2017/1/7.
 */
public class UserDaoImpl implements UserDao {

    public UserDaoImpl() {
        System.out.println("嘿嘿");
    }

    @Override
    public void save() {

        System.out.println("save。。。。");
    }

    @Override
    public void update() {
        System.out.println("update。。。");
    }
}
