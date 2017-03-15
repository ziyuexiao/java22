package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.kaishengit.entity.Book;
import com.kaishengit.until.DbHelp;

public class BookDao {
	public void save (Book book){
		String sql = "insert into t_book(bookname,author,total,nownumber,isbn) values(?,?,?,?,?)";
		DbHelp.update(sql, book.getBookname(),book.getAuthor(),book.getTotal(),book.getNownumber(),book.getIsbn());
	}
	public Book findByIsbn(String isbn){
		String sql = "select*from t_book where isbn = ?";
		return DbHelp.query(sql, new BeanHandler<>(Book.class), isbn);
	}
	
	public List<Book> findAll(){
		String sql = "select * from t_book";
		return DbHelp.query(sql, new BeanListHandler<>(Book.class));
	}
	
	public void del(Integer id) {
		String sql = "delete from t_book where id = ?";
		DbHelp.update(sql, id);
	}
	
	
	public Book findById(Integer id) {
		String sql = "select * from t_book where id = ?";
		return DbHelp.query(sql, new BeanHandler<>(Book.class), id);
	}
	
	
	public void update(Book book) {
		String sql = "update t_book set name = ?,author=?,total=?,nownumber=?,isbn=? where id = ?";
		DbHelp.update(sql, book.getBookname(),book.getAuthor(),book.getTotal(),book.getNownumber(),book.getIsbn(),book.getId());
	}
	
	public List<Book> findByPage(int start,int pageSize) {
		String sql = "select * from t_book limit ?,?";
		return DbHelp.query(sql, new BeanListHandler<>(Book.class), start,pageSize);
		 
	}
	public Long count() {
		String sql = "select count(*) from t_book ";
		return DbHelp.query(sql, new ScalarHandler<Long>() );
	}

}
