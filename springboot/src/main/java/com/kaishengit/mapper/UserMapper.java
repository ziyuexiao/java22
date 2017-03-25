package com.kaishengit.mapper;

import com.kaishengit.pojo.User;
import com.kaishengit.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from t_user")
    List<User> findAll();

    @Select("SELECT * FROM t_user where user_name = #{userName}")
    User findByUserName(String userName);
}
