package com.api.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    GET_SUCCESS(HttpStatus.OK, "GET_SUCCESS", "Data retrieved successfully"),
    CREATE_SUCCESS(HttpStatus.CREATED, "CREATE_SUCCESS", "Data created successfully"),
    UPDATE_SUCCESS(HttpStatus.OK, "UPDATE_SUCCESS", "Data updated successfully"),
    DELETE_SUCCESS(HttpStatus.OK, "DELETE_SUCCESS", "Data deleted successfully"),
    USERNAME_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "USERNAME_ALREADY_EXISTS", "Username already exists"),
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "EMAIL_ALREADY_EXISTS", "Email already exists"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "User not found");

    private final HttpStatus httpStatus;
    private final String responseCode;
    private final String message;

}
