package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.service.BookService;

@WebServlet("/del")
public class DelBookServlet  extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		if(StringUtils.isNumeric(id)) {
			BookService bookService = new BookService();
			bookService.delBookById(Integer.valueOf(id));
			
			response.sendRedirect("/list");
		} else {
			//手动引发404
			//response.sendError(404);
			response.sendError(404, "参数异常");
		}
		
	}
}
