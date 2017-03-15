package com.kaishengit.dao;

import com.kaishengit.entity.LoginLog;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by lenovo on 2016/12/16.
 */
public class LoginLogDao {

    public void save(LoginLog log) {
        String sql = "insert into t_login_log(loginip,t_user_id) values(?,?)";
        DbHelp.update(sql,log.getLoginIp(),log.getT_user_id());
    }
    public LoginLog findLastLogin(Integer userid) {
        String sql = "select * from t_login_log where t_user_id = ? order by logintime desc limit 0,1";
        return DbHelp.query(sql,new BeanHandler<LoginLog>(LoginLog.class),userid);
    }
}
