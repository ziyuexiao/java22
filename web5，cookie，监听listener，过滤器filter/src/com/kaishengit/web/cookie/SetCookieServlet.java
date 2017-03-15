package com.kaishengit.web.cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetCookieServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Cookie cookie = new Cookie("playId","1001");
		cookie.setDomain("localhost");
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24*1);
		cookie.setHttpOnly(true);
		resp.addCookie(cookie);
		
		
		 System.out.println("set cookie success!");
		
	}
	

}
