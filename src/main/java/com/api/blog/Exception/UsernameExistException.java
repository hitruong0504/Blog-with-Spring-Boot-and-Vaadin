package com.api.blog.Exception;

public class UsernameExistException extends Exception{
    public UsernameExistException(String message){
        super(message);
    }
}
