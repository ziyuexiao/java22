package com.kaishengit.web;

import java.io.IOException;
import java.util.UUID;

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
		
		 //����Tokenֵ
        String token = UUID.randomUUID().toString();
        //�����
        req.setAttribute("token",token);
        //����session
        HttpSession session = req.getSession();
        session.setAttribute("token",token);
		
        req.getRequestDispatcher("/WEB-INF/views/pay.jsp").forward(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 //����ֵ
        String token = req.getParameter("token");
        String money = req.getParameter("money");
        
        
        
        //��֤Token
        HttpSession session = req.getSession();
        String sessionToken = (String)session.getAttribute("token");
        if(sessionToken != null && sessionToken.equals(token)) {
            System.out.println("�ɹ�֧��" + money + "Ԫ");
            session.removeAttribute("token");

            req.getRequestDispatcher("/WEB-INF/views/paysuccess.jsp").forward(req,resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/payerror.jsp").forward(req,resp);
        }
	}
}
