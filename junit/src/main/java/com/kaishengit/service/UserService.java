package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import com.kaishengit.util.EhcacheUtil;

import java.util.List;

/**
 * Created by lenovo on 2016/12/13.
 */
public class UserService {
    private static final String USER_CACHE_NAME = "user";
    private  UserDao userDao = new UserDao();
    private EhcacheUtil ehcacheUtil = new EhcacheUtil();

    public User findById(Integer id){
        User user = (User)ehcacheUtil.get(USER_CACHE_NAME,"user:"+id);
        if (user==null){
            user = userDao.findById(id);
            ehcacheUtil.set(USER_CACHE_NAME,"user:"+id,user);
        }
        return user;
    }

    public List<User> findAll(){
        List<User> listUser = (List<User>)ehcacheUtil.get(USER_CACHE_NAME,"userlist");
        if(listUser==null){
            listUser = userDao.findAll();
            ehcacheUtil.set(USER_CACHE_NAME,"userlist",listUser);
        }

        return listUser;
    }

    public void save(User user){
        userDao.save(user);
        ehcacheUtil.remove(USER_CACHE_NAME,"userlist");
    }

    public void del(Integer id){
        userDao.del(id);
        ehcacheUtil.removeAll(USER_CACHE_NAME);
 //       ehcacheUtil.remove(USER_CACHE_NAME,"userList");
//        cacheUtil.remove(USER_CACHE_NAME,"user:"+id);
    }
}
