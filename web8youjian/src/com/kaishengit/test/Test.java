package com.kaishengit.test;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Test {

	public static void main(String[] args) {
		
		SimpleEmail email = new SimpleEmail();
		
		email.setHostName("smtp.126.com");//�ʼ���������ַ
		email.setAuthentication("ccs6125362648", "6125362648");//�������û���,����������
		email.setCharset("UTF-8");
		email.setStartTLSEnabled(true);//���뼯������  ,//����������ú��;ͻ�ʧ�� 
		
		try {
			email.setFrom("kaishengit@126.com"); // �����˵�����  
			email.setSubject("���Ϻã����ǽ�����ı��ʼ�");
			email.setMsg("�Է�˯��");  // Ҫ���͵���Ϣ  
			email.addTo("960529814@qq.com"); // �ռ��˵�����  
			
			email.send();  // ����  
		} catch (EmailException e) {
			
			e.printStackTrace();
		}
		
	}
}
