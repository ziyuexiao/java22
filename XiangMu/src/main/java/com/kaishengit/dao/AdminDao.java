package com.kaishengit.dao;

import com.kaishengit.entity.Admin;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by lenovo on 2016/12/28.
 */
public class AdminDao {
    public Admin findAdminByname(String adminname) {
        String sql = "select*from t_admin where adminname = ?";
        return DbHelp.query(sql,new BeanHandler<Admin>(Admin.class),adminname);
    }
}
