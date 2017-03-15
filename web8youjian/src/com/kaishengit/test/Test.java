package com.kaishengit.test;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Test {

	public static void main(String[] args) {
		
		SimpleEmail email = new SimpleEmail();
		
		email.setHostName("smtp.126.com");//邮件服务器地址
		email.setAuthentication("ccs6125362648", "6125362648");//发件人用户名,发件人密码
		email.setCharset("UTF-8");
		email.setStartTLSEnabled(true);//编码集的设置  ,//添加这条设置后发送就会失败 
		
		try {
			email.setFrom("kaishengit@126.com"); // 发送人的邮箱  
			email.setSubject("早上好，这是今天的文本邮件");
			email.setMsg("吃饭睡觉");  // 要发送的信息  
			email.addTo("960529814@qq.com"); // 收件人的邮箱  
			
			email.send();  // 发送  
		} catch (EmailException e) {
			
			e.printStackTrace();
		}
		
	}
}
