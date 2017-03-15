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
		//ͨ��ServletContextEvent�����ȡServletContext����
		ServletContext servletContext = servletContextEvent.getServletContext();
		//ͨ��ServletContext�����getInitParameter�������Զ�ȡ��web.xml�����õ�<contextparam>�ڵ��ֵ
		String userName = servletContext.getInitParameter("userName");
		System.out.println(userName);
				
	}

}
