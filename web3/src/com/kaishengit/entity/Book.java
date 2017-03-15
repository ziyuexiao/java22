package com.kaishengit.entity;

public class Book {

	private Integer id;
	private String bookname;
	private String author;
	private Integer total;
	private Integer nownumber;
	private String isbn;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getNownumber() {
		return nownumber;
	}
	public void setNownumber(Integer nownumber) {
		this.nownumber = nownumber;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	
}
