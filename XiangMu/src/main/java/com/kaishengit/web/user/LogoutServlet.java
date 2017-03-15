package com.kaishengit.web.user;

import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by lenovo on 2016/12/17.
 */
@WebServlet("/logout")
public class LogoutServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        httpSession.invalidate();

        resp.sendRedirect("/login?state=logout");
        //req.setAttribute("message","");
    }
}
