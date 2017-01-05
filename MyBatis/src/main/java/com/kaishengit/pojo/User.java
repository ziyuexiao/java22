package com.kaishengit.pojo;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/1/4.
 */
public class User implements Serializable  {
    private Integer id;
    private String username;
    private String tel;
    private String address;

    public User() {
    }

    public User(String username, String address) {
        this.username = username;
        this.address = address;
    }

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
