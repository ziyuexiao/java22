package com.kaishengit.entity;

import java.sql.Timestamp;

/**
 * Created by lenovo on 2016/12/28.
 */
public class Admin {
    private Integer id;
    private String admainname;
    private String password;
    private String phone;
    private String email;
    private Timestamp createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdmainname() {
        return admainname;
    }

    public void setAdmainname(String admainname) {
        this.admainname = admainname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }
}
