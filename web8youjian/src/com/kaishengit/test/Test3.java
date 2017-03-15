package com.kaishengit.test;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class Test3 {

	public static void main(String[] args) {
		MultiPartEmail email = new MultiPartEmail();
		
		email.setHostName("smtp.126.com");
		email.setAuthentication("ccs6125362648", "6125362648");
		email.setCharset("UTF-8");
		email.setStartTLSRequired(true);
		
		try {
			email.setFrom("ccs6125362648@126.com");
			email.setSubject("早上好，这是今天的照片");
			email.setMsg("<span>照片如下</span></br><img src='http://ww4.sinaimg.cn/mw690/69b3e715gw1el69a9iz37j20hs0qojw4.jpg'/>");
			email.addTo("960529814@qq.com");
			
			EmailAttachment attachment = new EmailAttachment();//创建附件对象
			attachment.setPath("D:/2.png");//附件地址
			
			email.attach(attachment);//添加附件对象
			
			email.send();
		} catch (EmailException e) {
			
			e.printStackTrace();
		}
	}
}
