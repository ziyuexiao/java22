package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Book;
import com.kaishengit.service.BookService;
@WebServlet("/edit")
public class EditBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		if(StringUtils.isNumeric(id)) {
			BookService bookService = new BookService();
			Book book = bookService.findBookById(Integer.valueOf(id));
			
			if(book == null) {
				response.sendError(404);
			} else {
				request.setAttribute("book", book);
				request.getRequestDispatcher("/WEB-INF/view/update.jsp").forward(request, response);
			}
		} else {
			response.sendError(404);
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
		String id = req.getParameter("id");
		String bookname = req.getParameter("bookname");
		String author = req.getParameter("author");
		String total = req.getParameter("total");
		String nowNumber = req.getParameter("nownumber");
		String isbn = req.getParameter("isbn");
		
		Book book = new Book();
		book.setId(Integer.valueOf(id));
		book.setBookname(bookname);
		book.setAuthor(author);
		book.setIsbn(isbn);
		book.setTotal(Integer.valueOf(total));
		book.setNownumber(Integer.valueOf(nowNumber));
		
		BookService bookService = new BookService();
		bookService.updateBook(book);
		
		resp.sendRedirect("/list");
		
	}
}

