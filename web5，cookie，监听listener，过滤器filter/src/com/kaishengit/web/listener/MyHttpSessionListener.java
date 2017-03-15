package com.kaishengit.web.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		
		System.out.println("session create...");
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		
		System.out.println("session destory...");
	}

}
