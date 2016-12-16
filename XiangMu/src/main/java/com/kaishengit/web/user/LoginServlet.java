package com.kaishengit.web.user;

import com.google.common.collect.Maps;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.UserService;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by lenovo on 2016/12/15.
 */
@WebServlet("/login")
public class LoginServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forword("user/login.jsp",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //获取客户端IP
        String ip = req.getRemoteAddr();

        Map<String,Object> result = Maps.newHashMap();
        UserService userService = new UserService();

        try {
            User user = userService.login(username, password, ip);

            //将登录成功的用户放入Session
            HttpSession session = req.getSession();
            session.setAttribute("curr_user",user);

            result.put("state","success");
        } catch (ServiceException ex) {
            result.put("state","error");
            result.put("message",ex.getMessage());
        }

        renderJson(result,resp);
    }
}
