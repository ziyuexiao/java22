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
			email.setSubject("���Ϻã����ǽ������Ƭ");
			email.setMsg("<span>��Ƭ����</span></br><img src='http://ww4.sinaimg.cn/mw690/69b3e715gw1el69a9iz37j20hs0qojw4.jpg'/>");
			email.addTo("960529814@qq.com");
			
			EmailAttachment attachment = new EmailAttachment();//������������
			attachment.setPath("D:/2.png");//������ַ
			
			email.attach(attachment);//��Ӹ�������
			
			email.send();
		} catch (EmailException e) {
			
			e.printStackTrace();
		}
	}
}
