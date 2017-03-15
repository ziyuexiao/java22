package com.kaishengit.mapper;

import com.kaishengit.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/1/4.
 */
public interface UserMapper {
    User findByid(Integer id);
    List<User> findAll();
    void save(User user);
    void update(User user);
    void del(Integer id);
    /*方法一*//*
    User findByusernameAndaddress(String username,String address);*/
   /* *//*方法二*//*
    User findByusernameAndaddress2(@Param("name") String username,@Param("add") String address);*/
   /* //方法三
    User findByusernameAndaddress3(Map<String,Object> param);*/

   /*动态sql*/
   User findByParam(Map<String,Object> param);
   List<User> findByids(List<Integer> idList);
   void batchSave(List<User> userList);


}
