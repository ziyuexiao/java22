package com.kaishengit.service;

import java.util.List;

import com.kaishengit.dao.BookDao;
import com.kaishengit.entity.Book;
import com.kaishengit.entity.Page;
import com.kaishengit.exception.ServiceException;

public class BookService {

	 BookDao bookDao = new BookDao();
	    /*
		 * �������鼮
		 * @throws ServiceException ����ҵ���쳣ʱ�׳�������ISBN�ظ����鼮�����ظ�...
		 */
	 public void saveBook(String bookname,String author,Integer total,String isbn) throws ServiceException{
		 
		//1. ����ISBN�����Ƿ����ظ����鼮
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
				throw new ServiceException("ISBN�ظ�������ʧ��");
			}
		 
	 }
	 /**
		 * ��ѯ���ݿ������е�ͼ��
		 * @return
		 */
	 public List<Book> findAllBook(){
		return bookDao.findAll();
		 
	 }
	 
	 
	 /**
		 * ����IDɾ����Ӧ���鼮
		 * @param id
		 */
		public void delBookById(Integer id) {
			//TODO �Ժ����
			bookDao.del(id);
		}
		
		
		/**
		 * ����ID���Ҷ�Ӧ���鼮
		 * @param valueOf
		 * @return
		 */
		public Book findBookById(Integer id) {
			return bookDao.findById(id);
		}
		
		/**
		 * �޸��鼮
		 * @param book
		 */
		public void updateBook(Book book) {
			bookDao.update(book);
		}
		/**
		 * ���ݵ�ǰҳ���ѯ�鼮
		 */
		/*public List<Book> findBookByPageNo(int pageNo) {
			int pageSize = 5; //ÿҳ��ʾ������
			int start = (pageNo-1)*5;//��ǰҳ��ʼ�к�
			
			return bookDao.findByPage(start,pageSize);
		}*/
		public Page<Book> findBookByPage(int pageNo) {
			//��ȡ������
			int totals = bookDao.count().intValue();
			Page<Book> page = new Page<>(totals,pageNo);
			//��ǰҳ�������
			List<Book> booklist=bookDao.findByPage(page.getStart(), page.getPageSize());
			page.setItems(booklist);
			return page;
		}
		
	
		
		
}
