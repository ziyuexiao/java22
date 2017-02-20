package com.kaishengit.mapper;

import com.kaishengit.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lenovo on 2017/1/12.
 */
public interface RoleMapper {
    List<Role> findAll();

    Role findById(Integer roleId);

    void saveNewUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    void delRoleByUserid(Integer userid);
    List<Role> findByUserId(Integer userId);
}
