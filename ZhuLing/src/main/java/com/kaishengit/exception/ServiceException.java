package com.kaishengit.exception;

/**
 * Created by lenovo on 2017/2/20.
 */
public class ServiceException extends RuntimeException {
    public ServiceException(){

    }
    public ServiceException(String message,Throwable throwable){
        super(message,throwable);
    }
    public ServiceException(String messsage){
        super(messsage);
    }
    public ServiceException(Throwable throwable){
        super(throwable);
    }
}
