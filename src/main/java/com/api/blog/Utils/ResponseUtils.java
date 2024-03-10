package com.api.blog.Utils;

import com.api.blog.Domain.DTO.ApiExceptionResponse;
import com.api.blog.Domain.DTO.ApiResponse;
import com.api.blog.enums.ResponseCode;
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

    public static ResponseEntity<ApiExceptionResponse> createResponse(ResponseCode errorCode, List<String> messages){
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                new Date(),
                errorCode.getHttpStatus().value(),
                errorCode.getHttpStatus(),
                errorCode.getResponseCode(),
                messages
        );
        return new ResponseEntity<>(apiExceptionResponse, errorCode.getHttpStatus());
    }

    public static <T> ResponseEntity<ApiResponse<T>> createResponse(T data, ResponseCode errorCode){
        ApiResponse apiResponse = new ApiResponse(
                new Date(),
                errorCode.getHttpStatus().value(),
                errorCode.getHttpStatus(),
                errorCode.getResponseCode(),
                errorCode.getMessage(),
                data
        );
        return new ResponseEntity<>(apiResponse, errorCode.getHttpStatus());
    }
}
