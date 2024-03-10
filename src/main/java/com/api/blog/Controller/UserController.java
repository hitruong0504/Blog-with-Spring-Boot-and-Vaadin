package com.api.blog.Controller;

import com.api.blog.Domain.DTO.*;
import com.api.blog.Domain.Entity.User;
import com.api.blog.Exception.CustomApiException;
import com.api.blog.Mapper.UserMapper;
import com.api.blog.Service.UserService;
import com.api.blog.Utils.ResponseUtils;
import com.api.blog.enums.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = {"/", "/user"})
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<User>> addUser(UserAddRequestDTO userAddRequestDTO) throws IOException, CustomApiException {
        User newUser = userService.createUser(userAddRequestDTO);
        return ResponseUtils.createResponse(newUser, ResponseCode.CREATE_SUCCESS);
    }

    @PutMapping(value = "/update/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable Long userId, UserUpdateRequestDTO userUpdateRequestDTO) throws IOException, CustomApiException {
        User updatedUser = userService.updateUser(userId, userUpdateRequestDTO);
        return ResponseUtils.createResponse(updatedUser, ResponseCode.UPDATE_SUCCESS);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse<User>> deleteUser(@PathVariable Long userId) throws CustomApiException {
        User deletedUser = userService.removeUser(userId);
        return ResponseUtils.createResponse(deletedUser, ResponseCode.DELETE_SUCCESS);
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<PaginationResponseDTO<UserResponseDTO>>> getAllUsers(@ModelAttribute UserSearchRequestDTO userSearchRequestDTO) {
        userSearchRequestDTO.updatePageable();
        PaginationResponseDTO<UserResponseDTO> users = userService.getAllUsers(userSearchRequestDTO);
        return ResponseUtils.createResponse(users, ResponseCode.GET_SUCCESS);
    }
}
