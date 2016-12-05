package com.kaishengit.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ajax")
public class AjaxServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		name = new String(name.getBytes("ISO8859-1"),"UTF-8"); //URL�к������ģ���ֹ����
		
		 //������ֹ���������������Ӧͷ
        resp.setHeader("pragma","no-cache");
        resp.setHeader("cache-control","no-cache");
        resp.setHeader("expries","0");

		
		 System.out.println("Hello,Ajax!!! -> " + name);
		 PrintWriter out = resp.getWriter();
		 out.print("Hello,nihao");
		 out.flush();
		 out.close();
		 
		 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 //���ύ(POST)��������
        req.setCharacterEncoding("UTF-8");

        //���ͻ���������Ӧ����
        resp.setCharacterEncoding("UTF-8");
        //������Ӧ����(���ַ���)
        resp.setContentType("text/plain;charset=UTF-8");

        String name = req.getParameter("name");

        PrintWriter out = resp.getWriter();
        if("tom".equals(name)) {
            System.out.println("�˺ű�ռ��");
            out.print("������");
        } else {
            System.out.println("�˺ſ���ʹ��");
            out.print("����");
        }

        System.out.println("Hello,Ajax doPost -> " + name);

        out.flush();
        out.close();
	}
}
