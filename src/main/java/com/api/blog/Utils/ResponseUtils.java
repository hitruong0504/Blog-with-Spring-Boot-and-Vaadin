package com.api.blog.Utils;

import com.api.blog.Domain.DTO.ApiExceptionResponse;
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
}
