package com.kaishengit.mapper;

import com.kaishengit.pojo.User;

import java.util.List;

/**
 * Created by lenovo on 2017/1/4.
 */
public interface UserMapper {
    User findByid(Integer id);
    List<User> findAll();
    void save(User user);
    void update(User user);
    void del(Integer id);


}
