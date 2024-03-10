package com.api.blog.Service;


import com.api.blog.Domain.DTO.*;
import com.api.blog.Domain.Entity.User;
import com.api.blog.Exception.CustomApiException;

import java.io.IOException;

public interface UserService {
    User createUser(UserAddRequestDTO user) throws IOException, CustomApiException;

    User updateUser(Long userId, UserUpdateRequestDTO user) throws IOException, CustomApiException;

    User removeUser(Long userId) throws CustomApiException;

    PaginationResponseDTO<UserResponseDTO> getAllUsers(UserSearchRequestDTO userSearchRequestDTO);

}
