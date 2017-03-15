package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.dao.BookDao;
import com.kaishengit.entity.Book;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.BookService;
@WebServlet("/add")
public class BookServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		String bookname = req.getParameter("bookname");
		String author = req.getParameter("author");
		String total = req.getParameter("total");
		String isbn = req.getParameter("isbn");
		
		/*Book book = new Book();
		book.setAuthor(author);
		book.setBookname(bookname);
		book.setIsbn(isbn);
		book.setTotal(Integer.valueOf(total));
		book.setNownumber(Integer.valueOf(total));
		
		
		BookDao bookDao = new BookDao();
		bookDao.save(book);*/
		
		
		BookService bookService = new BookService();
		try {
			bookService.saveBook( bookname,author,Integer.valueOf(total), isbn);
			//添加成功，重定向到/list
			resp.sendRedirect("/list");
			
		} catch (ServiceException e) {
			req.setAttribute("bookname", bookname);
			req.setAttribute("author", author);
			req.setAttribute("total", total);
			req.setAttribute("isbn", isbn);
			
			req.setAttribute("message", e.getMessage());
			
			req.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(req, resp);
		}
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(req, resp);
	}
	
	
}
