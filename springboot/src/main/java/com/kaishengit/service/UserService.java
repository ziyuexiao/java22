package com.kaishengit.service;

import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2017/3/23.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper usermapper;
    public List<User> findAll() {
        return usermapper.findAll();
    }
}
