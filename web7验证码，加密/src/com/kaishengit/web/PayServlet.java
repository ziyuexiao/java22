package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/pay")
public class PayServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("WEB-INF/views/pay.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String money = req.getParameter("money");
		String code = req.getParameter("code");
		
		//��֤��֤���Ƿ���ȷ
		HttpSession session = req.getSession();
		String sessionCode =(String)session.getAttribute("yanzheng");
		
		if(code!=null && code.equals(sessionCode)){
			session.removeAttribute("yanzheng");
			System.out.println("֧��:" + money + "Ԫ");
		}else{
			req.setAttribute("message", "��֤�����");
			req.setAttribute("money", money);
			req.getRequestDispatcher("WEB-INF/views/pay.jsp").forward(req, resp);
			
		}
	}
}
