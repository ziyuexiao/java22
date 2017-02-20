package com.kaishengit.service;

import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import com.kaishengit.util.db.Page;

import java.util.List;

/**
 * Created by lenovo on 2017/1/12.
 */
public interface UserService {

    List<User> findAll();

    void saveNewUser(User user);

    void delUser(Integer id);

    User findUserById(Integer id);

    void editUser(User user, Integer[] roleIds);

    List<Role> findAllRole();
    void save(User user, Integer[] roleIds);

    Page<User> findByPageNu(Integer p);

    Page<User> findByPageNuAndParam(Integer p, String queryName, String queryRole);
}
