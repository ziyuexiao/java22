package com.kaishengit.dao.impl;


import com.kaishengit.dao.UserDao;
import com.kaishengit.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by lenovo on 2017/1/7.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDaoImpl() {
        System.out.println("嘿嘿");
    }

    @Override
    public void save(User user) {
       String sql = "insert into tt_account(name,password) values(?,?)";
       jdbcTemplate.update(sql,user.getName(),user.getPassword());
    }

    @Override
    public void update(User user) {
        String sql = "update  tt_account set name=?,password=? WHERE id=?";
        jdbcTemplate.update(sql,user.getName(),user.getPassword(),user.getId());
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from tt_account where id=?";
        jdbcTemplate.update(sql,id);

    }

    @Override
    public User findById(Integer id) {
        String sql = "select*from tt_account where id =?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));

                return user;
            }
        },id);
    }

    @Override
    public List<User> findAll() {
        String sql = "select*from tt_account";

        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public User findByUserName(String name) {
        String sql = "select*from tt_account where name = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),name);
    }

    @Override
    public Long count() {
        String sql = "select count(*) from tt_account";
        return jdbcTemplate.queryForObject(sql,new SingleColumnRowMapper<Long>());
    }

}
