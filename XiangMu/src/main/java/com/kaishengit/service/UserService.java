package com.kaishengit.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.kaishengit.dao.LoginLogDao;
import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.LoginLog;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.EmailUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by lenovo on 2016/12/15.
 */
public class UserService {


    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserDao userDao = new UserDao();
    private LoginLogDao loginLogDao = new LoginLogDao();
    //发送激活邮件的Token缓存
    private static Cache<String,String> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build();
    /**
     * 检验用户是否被占用
     */
    public boolean validateUserName(String username){
        //注意不能用的一些特殊名字
        String name = Config.get("unregister.username");
        List<String> namelist = Arrays.asList(name.split(","));
        if (namelist.contains(username)){
            return false;
        }
        return userDao.findByUserName(username)==null;
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }


    /**
     * 新用户注册
     */
    public void saveNewUser(String username,String password,String email,String phone){
        User user = new User();
        user.setUsername(username);
        user.setPassword(DigestUtils.md5Hex(Config.get("user.password.salt")+password));
        user.setEmail(email);
        user.setPhone(phone);
        user.setState(User.USERSTATE_UNACTIVE);
        user.setAvatar(User.DEFAULT_AVATAR);

        userDao.save(user);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //给用户发送激活邮件
                String uuid = UUID.randomUUID().toString();
                //String url = "http://localhost/user/active?aa="+uuid;
                String url = "http://localhost/user/active?_="+uuid;
                //放入缓存等待一小时
                cache.put(uuid,username);

                String html = "<h3>你好，"+username+":</h3>请点击：<a href='"+url+"'>此链接</a>去激活你的账号";
               //String html ="<h3>Dear "+username+":</h3>请点击<a href='"+url+"'>该链接</a>去激活你的账号. <br> 凯盛软件";
                EmailUtil.sendHtmlEmail(email,"用户激活邮件",html);

            }
        });
        thread.start();
    }
    /**
     * 根据Token激活对应的用户
     * @param token
     */

    public void activeUser(String token) {
        String username = cache.getIfPresent(token);
        if (username == null){
            throw new ServiceException("token无效或已过期");
        }else {
            User user = userDao.findByUserName(username);
            if (user == null){
                throw new ServiceException("该用户不存在");
            }else {
                user.setState(User.USERSTATE_ACTIVED);
                userDao.update(user);

                //清除缓存中的键值对
                cache.invalidate(token);
            }
        }
    }
    /**
     * 用户登录
     * @param username
     * @param password
     * @param ip
     */

    public User login(String username, String password, String ip) {
        User user = userDao.findByUserName(username);
        if(user!=null && DigestUtils.md5Hex(Config.get("user.password.salt")+password).equals(user.getPassword())){

            if (user.getState().equals(User.USERSTATE_ACTIVED)){
                //记录登录日志
                LoginLog log = new LoginLog();
                log.setLoginIp(ip);
                log.setT_user_id(user.getId());

                loginLogDao.save(log);

                logger.info("{}登录了系统，IP：{}",username,ip);

                return user;
            }else if (user.getState().equals(User.USERSTATE_UNACTIVE)){
                throw new ServiceException("该账号未激活");
            } else {
                throw new ServiceException("该账号被禁用");
            }



        }else {
            throw new ServiceException("账号或密码错误");
        }
    }

}
