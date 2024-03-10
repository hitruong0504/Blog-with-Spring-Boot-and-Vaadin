package com.api.blog.Exception;

import com.api.blog.Domain.DTO.ApiExceptionResponse;
import com.api.blog.Utils.ResponseUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class ApiExceptionHandler implements ErrorController {

    @ExceptionHandler({UsernameExistException.class})
    public ResponseEntity<ApiExceptionResponse> usernameExistException(UsernameExistException e){
        return ResponseUtils.createResponse(HttpStatus.BAD_REQUEST, Collections.singletonList(e.getMessage()));
    }

    @ExceptionHandler({EmailExistException.class})
    public ResponseEntity<ApiExceptionResponse> emailExistException(EmailExistException e){
        return ResponseUtils.createResponse(HttpStatus.BAD_REQUEST, Collections.singletonList(e.getMessage()));
    }

    @ExceptionHandler({CustomApiException.class})
    public ResponseEntity<ApiExceptionResponse> customApiException(CustomApiException e){
        return ResponseUtils.createResponse(e.getErrorCode(), e.getErrors());
    }
}
