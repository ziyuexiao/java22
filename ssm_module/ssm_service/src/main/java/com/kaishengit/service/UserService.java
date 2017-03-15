package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.pojo.User;

/**
 * Created by lenovo on 2017/2/28.
 */
public class UserService {

        private UserDao userDao = new UserDao();

        public void save(User user) {
            userDao.saveUser(user);
        }

}
