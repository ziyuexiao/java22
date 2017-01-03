package com.kaishengit.web.user;

import com.google.common.collect.Maps;
import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.UserService;
import com.kaishengit.util.Config;
import com.kaishengit.web.BaseServlet;
import com.qiniu.util.Auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by lenovo on 2016/12/19.
 */
@WebServlet("/setting")
public class SettingServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //计算七牛中的token
        Auth auth = Auth.create(Config.get("qiniu.ak"),Config.get("qiniu.sk"));
        String token = auth.uploadToken(Config.get("qiniu.storage"));
        req.setAttribute("token",token);


        forword("user/setting.jsp",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println(action);

        if("profile".equals(action)){
            System.out.println(action);
            updateProfile(req,resp);
        }else if("password".equals(action)){
            System.out.println(action);
            updatePassword(req,resp);
        }else if ("avatar".equals(action)){
            updateAvatar(req,resp);
        }
    }

    private void updateAvatar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fileKey = req.getParameter("fileKey");
        User user = getCurrentUser(req);

        UserService userService = new UserService();
        userService.updateAvatar(user,fileKey);

        JsonResult result = new JsonResult();
        result.setState(JsonResult.SUCCESS);
        renderJson(result,resp);

    }

    private void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String oldPassword = req.getParameter("oldpassword");
        String newPassword = req.getParameter("newpassword");

        User user = getCurrentUser(req);

        UserService userService = new UserService();
        try {
            userService.updatePassword(user, oldPassword, newPassword);

           /* JsonResult result = new JsonResult();
            //result.setState(JsonResult.SUCCESS);

            result.setState(JsonResult.SUCCESS);*/

            Map<String,Object> result = Maps.newHashMap();

            result.put("state","success");
            renderJson(result,resp);


            renderJson(result,resp);
        } catch (ServiceException ex) {
            JsonResult result = new JsonResult(ex.getMessage());
            renderJson(result,resp);
        }




    }

    private void updateProfile(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String email = req.getParameter("email");
        System.out.println(email);

        User user = getCurrentUser(req);
        UserService userService = new UserService();
        userService.updateEmail(user,email);

        Map<String,Object> result = Maps.newHashMap();

        result.put("state","success");
        renderJson(result,resp);
    }


}
