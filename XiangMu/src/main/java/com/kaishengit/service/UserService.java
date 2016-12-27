package com.kaishengit.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.kaishengit.dao.LoginLogDao;
import com.kaishengit.dao.NotifyDao;
import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.LoginLog;
import com.kaishengit.entity.Notify;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.EmailUtil;
import com.kaishengit.util.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
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
    private NotifyDao notifyDao = new NotifyDao();
    //发送激活邮件的Token缓存
    private static Cache<String,String> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build();
    //发送找回密码邮件的Token缓存
    private static Cache<String,String> foundpasswordcache = CacheBuilder.newBuilder()
            .expireAfterWrite(40,TimeUnit.MINUTES)
            .build();
    //限制操作频率的Token缓存
    private static Cache<String,String> activecache = CacheBuilder.newBuilder()
            .expireAfterWrite(60,TimeUnit.SECONDS)
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
     */

    public User login(String username, String password, String ip) {
        User user = userDao.findByUserName(username);
        if(user!=null && DigestUtils.md5Hex(Config.get("user.password.salt")+password).equals(user.getPassword())){

            if (user.getState().equals(User.USERSTATE_ACTIVED)){
                //记录登录日志,存到数据库
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

    /**
     * 用户找回密码
     * @param sessionId 客户端的sessionID,限制客户端的操作频率
     * @param type 找回密码方式 email | phone
     * @param value 电子邮件 | 手机号码
     */
    public void foundPassword(String sessionId, String type, String value) {
        if(activecache.getIfPresent(sessionId)==null){
            if("phone".equals(type)){
                //手机操作业务
            }else {
                User user = userDao.findByEmail(value);
                if(user!=null){
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String uuid = UUID.randomUUID().toString();
                            String url = "http://localhost/foundpassword/newpassword?token=" + uuid;

                            foundpasswordcache.put(uuid,user.getUsername());
                            String html = "<h3>你好，"+user.getUsername()+":</h3>请点击：<a href='"+url+"'>此链接</a>去找回密码，40分钟有效";
                            EmailUtil.sendHtmlEmail(value,"密码找回邮件",html);
                        }
                    });
                    thread.start();
                }
            }
            activecache.put(sessionId,"username");
        }else {
            throw new ServiceException("操作太频繁,稍后再试");
        }
    }

    /**
     * 根据找回密码的链接获取找回密码的用户
     * @param token
     */
    public User foundpasswordGetuserByToken(String token){
        String username = foundpasswordcache.getIfPresent(token);

        if (StringUtils.isEmpty(username)){
            throw new ServiceException("token过期或者错误");
        }else {
            User user = userDao.findByUserName(username);
            if (user==null){
                throw new ServiceException("未找到该用户");
            }else {
                return user;
            }
        }
    }

    /**
     * 重置用户的密码
     * @param id 用户ID
     * @param token 找回密码的TOken
     * @param password 新密码
     */
    public void resetPassword(String id,String token,String password){
        if (foundpasswordcache.getIfPresent(token)==null){
            throw new ServiceException("token过期或者错误");
        }else {
            User user = userDao.findById(Integer.valueOf(id));

            user.setPassword(DigestUtils.md5Hex(Config.get("user.password.salt")+password));
            userDao.update(user);

            //删除token
            foundpasswordcache.invalidate(token);

            logger.info("{}重设了密码",user.getUsername());
        }
    }
    /**
     * 修改用户电子邮件
     */

    public void updateEmail(User user,String email){
        user.setEmail(email);
        userDao.update(user);
    }
    /**
     * 修改用户户密码
     */
    public void updatePassword(User user,String oldPassword,String newPassword){
        if(DigestUtils.md5Hex(Config.get("user.password.salt")+oldPassword).equals(user.getPassword())){
            newPassword = DigestUtils.md5Hex(Config.get("user.password.salt")+newPassword);
            user.setPassword(newPassword);
            userDao.update(user);
        }else {
            throw new ServiceException("原始密码错误");
        }
    }

    /**
     * 修改用户户头像
     * @param user
     * @param fileKey
     */
    public void updateAvatar(User user, String fileKey) {
        user.setAvatar(fileKey);
        userDao.update(user);
    }

    /**
     * 获取消息列表
     * @param user
     * @return
     */
    public List<Notify> findNotifyListByUser(User user) {
        return notifyDao.findNotifyByUserid(user.getId());
    }
    /**
     * 获取状态为1时的数量
     */

   public int findcountBystateanduser(User user){
       return notifyDao.count1(user.getId());
   }
    public int findcountByuser(User user){
        return notifyDao.count2(user.getId());
    }
    /**
     * 更改消息状态
     * @param ids
     */

    public void updateNotifyStateByIds(String ids) {
        String idArray[] = ids.split(",");
        for(int i=0;i<idArray.length;i++){
            Notify notify = notifyDao.findNotifyByid(idArray[i]);
            notify.setState(Notify.STATE_READED);
            notify.setReadtime(new Timestamp(DateTime.now().getMillis()));
            notifyDao.update(notify);
        }
    }
}
