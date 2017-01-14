package com.kaishengit.mapper;

import com.kaishengit.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lenovo on 2017/1/12.
 */
public interface UserMapper {
    void save(User user);

    List<User> findAll();

    void delUserByid(Integer id);

    User findUserByid(Integer id);

    void update(User user);


    Long count();

    List<User> findUserByPage(@Param("start") int start, @Param("pageSize") int pageSize);

    Long countByParam(@Param("queryName") String queryName,@Param("queryRole") String queryRole);

    List<User> findUserByPageAndParam(@Param("start") int start,
                                      @Param("pageSize") int pageSize,
                                      @Param("queryName") String queryName,
                                      @Param("queryRole") String queryRole);
}
