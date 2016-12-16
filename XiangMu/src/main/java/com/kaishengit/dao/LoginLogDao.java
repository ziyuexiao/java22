package com.kaishengit.dao;

import com.kaishengit.entity.LoginLog;
import com.kaishengit.util.DbHelp;

/**
 * Created by lenovo on 2016/12/16.
 */
public class LoginLogDao {

    public void save(LoginLog log) {
        String sql = "insert into t_login_log(loginip,t_user_id) values(?,?)";
        DbHelp.update(sql,log.getLoginIp(),log.getT_user_id());
    }
}
