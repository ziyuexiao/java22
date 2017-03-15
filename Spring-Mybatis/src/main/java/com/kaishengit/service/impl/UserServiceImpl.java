package com.kaishengit.service.impl;


import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Created by lenovo on 2017/1/7.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public void save(User user) throws Exception {

        userMapper.save(user);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }








}
