package com.api.blog.Exception;

import com.api.blog.enums.ErrorCode;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class CustomApiException extends Exception{
    private final ErrorCode errorCode;

    private final List<String> errors;

    public CustomApiException(ErrorCode errorCode, String... messages){
        super(String.join(", ", messages));
        this.errorCode = errorCode;
        this.errors = Arrays.asList(messages);
    }
}
