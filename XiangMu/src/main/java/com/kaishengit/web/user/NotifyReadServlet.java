package com.kaishengit.web.user;

import com.kaishengit.service.UserService;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lenovo on 2016/12/26.
 */
@WebServlet("/notifyRead")
public class NotifyReadServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("ids");
        UserService userService = new UserService();
        userService.updateNotifyStateByIds(ids);
        renderTest("success",resp);
    }
}
