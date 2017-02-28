package com.kaishengit.pojo;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2017/1/12.
 */
public class User implements Serializable {
    private Integer id;
    private String name;
    private String password;
    private String mobile;
    private List<Role> roleList;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleNames(){
        List<String> viewNames = Lists.newArrayList(Collections2.transform(getRoleList(), new Function<Role, String>() {
            @Override
            public String apply(Role role) {
                return role.getViewname();
            }
        }));

        StringBuilder sb = new StringBuilder();
        for(String viewname : viewNames) {
            sb.append(viewname).append(" ");
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
