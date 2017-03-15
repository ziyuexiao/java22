package com.kaishengit.web.cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DelCookieServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		
		if(cookies != null){
			for(Cookie cookie:cookies){
				cookie.setMaxAge(0);
				cookie.setDomain("localhost");
				cookie.setPath("/");
				resp.addCookie(cookie);
			}
			
		}
		System.out.println("Delete cookie success!");
	}

}
