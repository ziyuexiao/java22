package com.kaishengit.pojo;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/1/12.
 */
public class Role implements Serializable{
    private Integer id;
    private String rolename;
    private String viewname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getViewname() {
        return viewname;
    }

    public void setViewname(String viewname) {
        this.viewname = viewname;
    }
}
