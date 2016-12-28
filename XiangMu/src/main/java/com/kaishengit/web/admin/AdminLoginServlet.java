package com.kaishengit.web.admin;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Admin;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.AdminService;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lenovo on 2016/12/28.
 */
@WebServlet("/admin/login")
public class AdminLoginServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断是否有管理员登录，并进行清除
        req.getSession().removeAttribute("curr_admin");

        forword("admin/login.jsp",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminname = req.getParameter("adminname");
        String password = req.getParameter("password");
        //获得登录者的ip
        String ip = req.getRemoteAddr();

        AdminService adminService = new AdminService();
        JsonResult result = new JsonResult();

       try {
           Admin admin = adminService.adminlogin(adminname,password,ip);
           req.getSession().setAttribute("curr_admin",admin);
           result.setState(JsonResult.SUCCESS);
       }catch (ServiceException e){
           result.setState(JsonResult.ERROR);
           result.setMessage(e.getMessage());

       }

       renderJson(result,resp);
    }
}
