package com.kaishengit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lenovo on 2016/12/12.
 */
@WebServlet("/send")
public class SendServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String message = req.getParameter("message");
        System.out.println(message);
        //存到数据库


        //显示出来
        req.setAttribute("message",message);
        req.getRequestDispatcher("show.jsp").forward(req,resp);

    }
}
