package com.api.blog.Exception;

import com.api.blog.enums.ResponseCode;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class CustomApiException extends Exception{
    private final ResponseCode responseCode;

    private final List<String> errors;

    public CustomApiException(ResponseCode responseCode, String... messages){
        super(String.join(", ", messages));
        this.responseCode = responseCode;
        this.errors = Arrays.asList(messages);
    }
}
