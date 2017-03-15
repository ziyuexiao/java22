package com.kaishengit.web.user;

import com.google.common.collect.Maps;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.UserService;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by lenovo on 2016/12/17.
 */
@WebServlet("/foundpassword/newpassword")
public class ResetPasswordServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        if (StringUtils.isEmpty(token)){
            resp.sendError(404);
        }else {
            UserService userService = new UserService();
           try {
               User user = userService.foundpasswordGetuserByToken(token);
               req.setAttribute("user",user);
               req.setAttribute("token",token);

               forword("user/resetpassword.jsp",req,resp);
           }catch (ServiceException e){
               req.setAttribute("message",e.getMessage());
               forword("user/reseterror.jsp",req,resp);
           }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String token = req.getParameter("token");
        String password = req.getParameter("password");

        Map<String,String> result = Maps.newHashMap();

        UserService userService = new UserService();
        try {
            userService.resetPassword(id,token,password);
            result.put("state","success");
        }catch (ServiceException e){
            result.put("state","error");
            result.put("message",e.getMessage());
        }
        renderJson(result,resp);
    }
}
