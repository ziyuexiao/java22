package com.kaishengit.service;

import java.util.List;

import com.kaishengit.dao.BookDao;
import com.kaishengit.entity.Book;
import com.kaishengit.entity.Page;
import com.kaishengit.exception.ServiceException;

public class BookService {

	 BookDao bookDao = new BookDao();
	    /*
		 * 保存新书籍
		 * @throws ServiceException 发生业务异常时抛出，例如ISBN重复，书籍名称重复...
		 */
	 public void saveBook(String bookname,String author,Integer total,String isbn) throws ServiceException{
		 
		//1. 根据ISBN查找是否有重复的书籍
			Book book = bookDao.findByIsbn(isbn);
			if(book==null){
				book = new Book();
				book.setBookname(bookname);
				book.setAuthor(author);
				book.setIsbn(isbn);
				book.setTotal(Integer.valueOf(total));
				book.setNownumber(Integer.valueOf(total));
				
				bookDao.save(book);
			}else{
				throw new ServiceException("ISBN重复，保存失败");
			}
		 
	 }
	 /**
		 * 查询数据库中所有的图书
		 * @return
		 */
	 public List<Book> findAllBook(){
		return bookDao.findAll();
		 
	 }
	 
	 
	 /**
		 * 根据ID删除对应的书籍
		 * @param id
		 */
		public void delBookById(Integer id) {
			//TODO 以后填坑
			bookDao.del(id);
		}
		
		
		/**
		 * 根据ID查找对应的书籍
		 * @param valueOf
		 * @return
		 */
		public Book findBookById(Integer id) {
			return bookDao.findById(id);
		}
		
		/**
		 * 修改书籍
		 * @param book
		 */
		public void updateBook(Book book) {
			bookDao.update(book);
		}
		/**
		 * 根据当前页码查询书籍
		 */
		/*public List<Book> findBookByPageNo(int pageNo) {
			int pageSize = 5; //每页显示的数量
			int start = (pageNo-1)*5;//当前页起始行号
			
			return bookDao.findByPage(start,pageSize);
		}*/
		public Page<Book> findBookByPage(int pageNo) {
			//获取总数量
			int totals = bookDao.count().intValue();
			Page<Book> page = new Page<>(totals,pageNo);
			//当前页面的数据
			List<Book> booklist=bookDao.findByPage(page.getStart(), page.getPageSize());
			page.setItems(booklist);
			return page;
		}
		
	
		
		
}
