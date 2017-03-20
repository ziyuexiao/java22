package com.kaishengit.action;

public class GsonAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        User user = new User();
        user.setAddress("中国");
        user.setUserName("李斯");

        renderJson(user);

        return NONE;//没有页面
    }
}
