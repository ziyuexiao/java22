package com.kaishengit.web.admin;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.UserService;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;
import com.kaishengit.vo.Uservo;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lenovo on 2016/12/29.
 */
@WebServlet("/admin/user")
public class AdminUserServlet extends BaseServlet {
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String p = req.getParameter("p");
        Integer pageNo = StringUtils.isNumeric(p)?Integer.parseInt(p):1;
        Page<Uservo> page =userService.findUserList(pageNo);
        req.setAttribute("page",page);
        forword("admin/user.jsp",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userid = req.getParameter("userid");
        String state = req.getParameter("userstate");
        Integer userstate = Integer.valueOf(state);
        userstate = userstate ==1?2:1;
        JsonResult jsonResult = new JsonResult();
        try {
            userService.updateUserstate(userid, userstate);
            jsonResult.setState(JsonResult.SUCCESS);
        }catch (ServiceException e){
            jsonResult.setState(JsonResult.ERROR);
            jsonResult.setMessage(e.getMessage());
        }
        renderJson(jsonResult,resp);
    }
}
