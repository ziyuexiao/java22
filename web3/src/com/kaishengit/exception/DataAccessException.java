package com.kaishengit.exception;


/**
 *���ݷ����쳣 
 */
public class DataAccessException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;
	public DataAccessException() {}
	public DataAccessException(String message) {
		super(message);
	}
	
	public DataAccessException(String message,Throwable th) {
		super(message,th);
	}
	
	public DataAccessException(Throwable th) {
		super(th);
	}
	

}
