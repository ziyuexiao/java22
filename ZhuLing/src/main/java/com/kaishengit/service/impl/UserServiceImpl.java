package com.kaishengit.service.impl;

import com.kaishengit.mapper.RoleMapper;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;

import com.kaishengit.service.WeixinService;
import com.kaishengit.util.db.Page;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


/**
 * Created by lenovo on 2017/1/12.
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private WeixinService weixinService;
    @Value("${password.salt}")
    private String salt;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public void saveNewUser(User user) {

        user.setPassword(DigestUtils.md5Hex(user.getPassword()+salt));
        userMapper.save(user);
    }

    @Override
    @Transactional
    public void delUser(Integer id) {
        //删除用户角色
        roleMapper.delRoleByUserid(id);
        //删除用户
        userMapper.delUserByid(id);
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserByid(id);
    }

    @Override
    @Transactional
    public void editUser(User user, Integer[] roleIds) {
        //删除原有角色
        roleMapper.delRoleByUserid(user.getId());
        //添加新角色
        addUserRole(user,roleIds);
        //更新用户
        if(StringUtils.isNotEmpty(user.getPassword())) {
            user.setPassword(DigestUtils.md5Hex(user.getPassword()+salt));
        }
        userMapper.update(user);
    }

    private void addUserRole(User user, Integer[] roleIds) {
        if(roleIds!=null){
            for (Integer roleId : roleIds){
                Role role = roleMapper.findById(roleId);
                if(role != null){
                    roleMapper.saveNewUserRole(user.getId(),roleId);

                }
            }
        }
    }

    @Override
    public List<Role> findAllRole() {
        return roleMapper.findAll();
    }

    @Override
    @Transactional
    public void save(User user, Integer[] roleIds) {
        //1,保存用户
        userMapper.save(user);
        //2,保存用户与角色之间的关系
        if(roleIds!=null){
            for (Integer roleId : roleIds){
                Role role = roleMapper.findById(roleId);
                if(role != null){
                    //创建关系表
                    roleMapper.saveNewUserRole(user.getId(),roleId);

                }
            }
        }
        //3,保存到微信
        com.kaishengit.dto.weixin.User wxUser = new com.kaishengit.dto.weixin.User();
        wxUser.setUserid(user.getId().toString());
        wxUser.setName(user.getName());
        wxUser.setMobile(user.getMobile());
        System.out.println(user.getMobile());
        wxUser.setDepartment(Arrays.asList(roleIds));
        weixinService.saveUser(wxUser);

    }

    @Override
    public Page<User> findByPageNu(Integer p) {

        int total = userMapper.count().intValue();
        Page<User> page = new Page<>(total,p);
        List<User> userList = userMapper.findUserByPage(page.getStart(),page.getPageSize());
        page.setItems(userList);


        return page;
    }

    @Override
    public Page<User> findByPageNuAndParam(Integer p, String queryName, String queryRole) {
        int total = userMapper.countByParam(queryName,queryRole).intValue();
        Page<User> page = new Page<>(total,p);
        List<User> userList = userMapper.findUserByPageAndParam(page.getStart(),page.getPageSize(),queryName,queryRole);
        page.setItems(userList);
        return page;
    }
}
