package com.kaishengit.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Admin;
import com.kaishengit.entity.Book;
import com.kaishengit.entity.Page;
import com.kaishengit.service.BookService;

public class ListBookServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
					String p = req.getParameter("p");
					int pageNo = 1;
					if(StringUtils.isNumeric(p)){
						pageNo = Integer.parseInt(p);
					}
					
					
		
					BookService bookService = new BookService();
					
					//添加成功，查询所有的书籍，并在页面上显示
					//List<Book> bookList = bookService.findAllBook();
					/*List<Book> bookList = bookService.findBookByPageNo(pageNo);
					req.setAttribute("bookList", bookList);*/
					
					Page<Book> page=bookService.findBookByPage(pageNo);
					 req.setAttribute("page",page);
					
					req.getRequestDispatcher("/WEB-INF/view/list.jsp").forward(req, resp);
				
	}
}
