package com.kaishengit.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		System.out.println("ServletContextListener destroy....");
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("ServletContextListener init....");
		//通过ServletContextEvent对象获取ServletContext对象
		ServletContext servletContext = servletContextEvent.getServletContext();
		//通过ServletContext对象的getInitParameter方法可以读取在web.xml中配置的<contextparam>节点的值
		String userName = servletContext.getInitParameter("userName");
		System.out.println(userName);
				
	}

}
