package com.kaishengit.entity;

import java.sql.Timestamp;

/**
 * Created by lenovo on 2016/12/15.
 */
public class LoginLog {

    private Integer id;
    private Timestamp logintime;
    private String loginIp;
    private Integer t_user_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getLogintime() {
        return logintime;
    }

    public void setLogintime(Timestamp logintime) {
        this.logintime = logintime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Integer getT_user_id() {
        return t_user_id;
    }

    public void setT_user_id(Integer t_user_id) {
        this.t_user_id = t_user_id;
    }
}
