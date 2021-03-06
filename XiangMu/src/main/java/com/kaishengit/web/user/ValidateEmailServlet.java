package com.kaishengit.web.user;

import com.kaishengit.entity.User;
import com.kaishengit.service.UserService;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lenovo on 2016/12/16.
 */
@WebServlet("/validate/email")
public class ValidateEmailServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String type = req.getParameter("type");

        if(StringUtils.isNotEmpty(type)&&"1".equals(type)){
            User currentuser = getCurrentUser(req);

            if(currentuser!=null){
                if(currentuser.getEmail().equals(email)){
                    renderTest("true",resp);
                    return;
                }

            }
        }

        UserService userService = new UserService();
        User user = userService.findByEmail(email);
        if (user == null){
            renderTest("true",resp);
        }else {
            renderTest("false",resp);
        }
    }


}
