package com.kaishengit.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.User;

@WebServlet("/users.xml")
public class UserServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User(1001,"Jack","北京");
        User user2 = new User(1003,"james","hot");
        User user3 = new User(1004,"kobe","luosanji");
		

        List<User> userlist = Arrays.asList(user,user2,user3);
	    
        //设置响应的字符编码
        resp.setCharacterEncoding("UTF-8");
	    
        //设置响应头MIME Type
        resp.setContentType("text/xml,charset=UTF-8");
	   
    
        PrintWriter out = resp.getWriter();
        
        out.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        out.print("<Users>");
        for(User U:userlist){
        	out.print("<user id=\""+U.getId()+"\">");
        	out.print("<name>"+U.getUsername()+"</name>");
        	out.print("<address>"+U.getAddress()+"</address>");
        	out.print("</user>");
        }
        out.print("</Users>");
        
        out.flush();
        out.close();
	}

}
