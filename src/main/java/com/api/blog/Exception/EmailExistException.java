package com.api.blog.Exception;

public class EmailExistException extends Exception{
    public EmailExistException(String message){
        super(message);
    }
}
