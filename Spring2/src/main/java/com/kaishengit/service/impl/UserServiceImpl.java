package com.kaishengit.service.impl;

import com.kaishengit.dao.UserDao;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by lenovo on 2017/1/7.
 */
@Service

public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;
    /*@Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }*/


    @Override
    @Transactional
    public void save(User user) throws Exception {
        userDao.save(user);
        if (1==1){
            /*回滚设置，默认发生运行时异常事务才会回滚，非运行时异常事务不回滚*/
            /*throw new RuntimeException("故意事务异常");//会回滚*/
            throw new Exception("guyi");//不会回滚
        }
        userDao.save(user);
    }

    @Override
    //@Transactional(readOnly = true)
    public User findById(Integer id) {
        return userDao.findById(id);
    }


}
