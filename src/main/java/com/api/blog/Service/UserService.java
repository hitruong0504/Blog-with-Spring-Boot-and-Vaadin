package com.api.blog.Service;


import com.api.blog.Domain.DTO.*;
import com.api.blog.Domain.Entity.User;
import com.api.blog.Exception.CustomApiException;
import com.api.blog.Exception.UsernameExistException;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User createUser(UserAddRequestDTO user) throws IOException, CustomApiException;

    User updateUser(Long userId, UserUpdateRequestDTO user) throws IOException, CustomApiException;

    User removeUser(Long userId);

    PaginationResponseDTO<UserResponseDTO> getAllUsers(UserSearchRequestDTO userSearchRequestDTO);

}
