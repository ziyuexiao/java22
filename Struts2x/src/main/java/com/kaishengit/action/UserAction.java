package com.kaishengit.action;

import com.kaishengit.entity.User;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lenovo on 2017/3/17.
 */
public class UserAction {
   /* //不封装成一个实体类时,从前端获取表单值
    private String userName;
    private String address;*/

   //封装成一个实体类时,从前端获取表单值
   private User user;
   //向前端页面传值
    private List<String> names;

    public String execute(){
        names = Arrays.asList("aa","bb","cc");

        return "success";
    }
    public String save() {
        //System.out.println("userName:" + userName+ " address:" + address);
        System.out.println("userName:" + user.getUserName()+ " address:" + user.getAddress());
        return "success";
    }

    /*//get set 不封装成一个实体类时,从前端获取表单值
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }*/

    //get  set
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    //get  set
    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
