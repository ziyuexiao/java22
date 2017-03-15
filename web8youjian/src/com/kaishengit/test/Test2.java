package com.kaishengit.test;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;


public class Test2 {

	public static void main(String[] args) {
		
		HtmlEmail email = new HtmlEmail();
		
		email.setHostName("smtp.126.com");//邮件服务器地址
		email.setAuthentication("ccs6125362648", "6125362648");//发件人用户名,发件人密码
		email.setCharset("UTF-8");
		email.setStartTLSEnabled(true);//编码集的设置  ,//添加这条设置后发送就会失败 
		
		try {
			email.setFrom("ccs6125362648@126.com"); // 发送人的邮箱  
			email.setSubject("早上好，这是今天的照片");
			email.setHtmlMsg("<span>照片如下</span></br><img src='http://ww4.sinaimg.cn/mw690/69b3e715gw1el69a9iz37j20hs0qojw4.jpg'/>");  // 要发送的信息  
			email.addTo("960529814@qq.com"); // 收件人的邮箱  
			
			email.send();  // 发送  
		} catch (EmailException e) {
			
			e.printStackTrace();
		}
	}
}
