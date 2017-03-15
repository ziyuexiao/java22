package com.kaishengit.dto.weixin;

import java.util.List;

/**
 * Created by lenovo on 2017/2/25.
 */
public class User {
    /**
     * userid : zhangsan
     * name : 张三
     * department : [1,2]
     * mobile : 15913215421
     */

    private String userid;
    private String name;
    private String mobile;
    private List<Integer> department;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<Integer> getDepartment() {
        return department;
    }

    public void setDepartment(List<Integer> department) {
        this.department = department;
    }
}
