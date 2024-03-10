package com.api.blog.Utils;

import com.api.blog.Domain.DTO.ApiExceptionResponse;
import com.api.blog.enums.ErrorCode;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public class ResponseUtils {
    public static ResponseEntity<ApiExceptionResponse> createResponse(HttpStatus httpStatus, List<String> messages){
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                new Date(),
                httpStatus.value(),
                httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(),
                messages
        );
        return new ResponseEntity<>(apiExceptionResponse, httpStatus);
    }

    public static ResponseEntity<ApiExceptionResponse> createResponse(ErrorCode errorCode, List<String> messages){
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                new Date(),
                errorCode.getHttpStatus().value(),
                errorCode.getHttpStatus(),
                errorCode.getCode(),
                messages
        );
        return new ResponseEntity<>(apiExceptionResponse, errorCode.getHttpStatus());
    }
}
