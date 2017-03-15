package com.kaishengit.entity;

import java.sql.Timestamp;

/**
 * Created by lenovo on 2016/12/15.
 */
public class User {


    //用户状态：未激活
    public static final Integer USERSTATE_UNACTIVE=0;
    //用户状态：已激活
    public static final Integer USERSTATE_ACTIVED=1;
    //用户状态：禁用
    public static final Integer USERSTATE_DISABLE=2;

    //新用户注册默认头像的key
    public static final String DEFAULT_AVATAR="untitled.png";

    private Integer id;
    private String username;
    private String password;
    private Timestamp createtime;
    private String email;
    private String phone;
    private Integer state;
    private String avatar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
