package com.kaishengit.service.impl;

import com.kaishengit.dao.UserDao;
import com.kaishengit.dao.impl.UserDaoImpl;
import com.kaishengit.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by lenovo on 2017/1/7.
 */
public class UserServiceImpl implements UserService {


    private UserDao userDao;

    private String name;
    private Integer age;
    private List<String> list;
    private Set<Double> set;
    private Map<String,Object> map;
    private Properties properties;

   /* public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setSet(Set<Double> set) {
        this.set = set;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
*/
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /*public UserServiceImpl(UserDao userDao) {
        this.userDao=userDao;
    }*/

    @Override
    public void save() {
        userDao.save();
    }

    @Override
    public void update() {
        userDao.update();
    }

    @Override
    public int getNum() {
        System.out.println("getNum method...");
       /* if (1==1){
            throw new RuntimeException("故意引发异常通知");
        }*/
        return 100;
    }
}
