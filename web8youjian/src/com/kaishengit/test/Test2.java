package com.kaishengit.test;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;


public class Test2 {

	public static void main(String[] args) {
		
		HtmlEmail email = new HtmlEmail();
		
		email.setHostName("smtp.126.com");//�ʼ���������ַ
		email.setAuthentication("ccs6125362648", "6125362648");//�������û���,����������
		email.setCharset("UTF-8");
		email.setStartTLSEnabled(true);//���뼯������  ,//����������ú��;ͻ�ʧ�� 
		
		try {
			email.setFrom("ccs6125362648@126.com"); // �����˵�����  
			email.setSubject("���Ϻã����ǽ������Ƭ");
			email.setHtmlMsg("<span>��Ƭ����</span></br><img src='http://ww4.sinaimg.cn/mw690/69b3e715gw1el69a9iz37j20hs0qojw4.jpg'/>");  // Ҫ���͵���Ϣ  
			email.addTo("960529814@qq.com"); // �ռ��˵�����  
			
			email.send();  // ����  
		} catch (EmailException e) {
			
			e.printStackTrace();
		}
	}
}
