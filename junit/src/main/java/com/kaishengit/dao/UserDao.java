package com.kaishengit.dao;

import com.kaishengit.entity.User;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class UserDao {

    public void save(User user) {
        String sql = "insert into user1 (name, age, address, tel) values(?,?,?,?)";
        DbHelp.update(sql,user.getName(),user.getAge(),user.getAddress(),user.getTel());
    }

    public User findById(Integer id) {
        String sql = "select * from user1 where id = ?";
        return DbHelp.query(sql,new BeanHandler<>(User.class),id);
    }

    public List<User> findAll() {
        String sql = "select * from user1";
        return DbHelp.query(sql,new BeanListHandler<>(User.class));
    }

    public void del(int id) {
        String sql = "delete from user1 where id = ?";
        DbHelp.update(sql,id);
    }
}
