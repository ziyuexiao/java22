package com.kaishengit.web;

import com.kaishengit.entity.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class HomeServlet extends HttpServlet {

    public HomeServlet () {
        //System.out.println("Create HomeServlet....");
    }

   /* @Override
    public void init() throws ServletException {
        ServletConfig servletConfig = getServletConfig();
        System.out.println(servletConfig.getInitParameter("userName"));
    }*/

    // @Override
   /* public void init(ServletConfig config) throws ServletException {
        //读取web.xml中<servlet>节点中<init-param>的配置

        Enumeration<String> enumeration = config.getInitParameterNames();

        while(enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = config.getInitParameter(key);

            System.out.println(key +  " -> " + value);
        }


        String name = config.getInitParameter("userName");
        String password = config.getInitParameter("password");
        System.out.println("初始化.... " + name + " " + password);
    }*/

   /* @Override
    public void destroy() {
        System.out.println("销毁.....");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("Hello,Servlet");

        //ServletContext servletContext = getServletContext();
        //ServletContext servletContext = req.getServletContext();

        User user = new User();
        user.setId(100);
        user.setFirstName("Tom");
        user.setLastName("Hanks");

        HttpSession session = req.getSession();
        session.setAttribute("address","北京四合院");

        ServletContext servletContext = session.getServletContext();

        List<String> names = new ArrayList<>();
        names.add("A1");
        names.add("A2");
        names.add("A3");



        servletContext.setAttribute("version","X1");
        req.setAttribute("name","jack");
        req.setAttribute("user",user);
        req.setAttribute("names",names);
        req.getRequestDispatcher("home.jsp").forward(req,resp);

    }*/
}

