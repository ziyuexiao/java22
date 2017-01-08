package com.kaishengit.test;

import com.kaishengit.dao.UserDao;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lenovo on 2017/1/7.
 */
public class SpringTestCase {
    @Test
    public void save(){
        //获取Spring容器
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        //从Spring容器中获取Bean
        UserDao userDao =
                (UserDao) applicationContext.getBean("UserDaoImpl");

        UserDao userDao2 =
                (UserDao) applicationContext.getBean("UserDaoImpl");

        System.out.println(userDao==userDao2);

        userDao.save();
        userDao.update();

    }
    @Test
    public void load(){
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = applicationContext.getBean("userservice",UserService.class);
        userService.save();
    }

}
