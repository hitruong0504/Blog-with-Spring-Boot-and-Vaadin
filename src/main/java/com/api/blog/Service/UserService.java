package com.api.blog.Service;


import com.api.blog.Domain.DTO.UserAddRequestDTO;
import com.api.blog.Domain.DTO.UserUpdateRequestDTO;
import com.api.blog.Domain.Entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User createUser(UserAddRequestDTO user) throws IOException;

    User updateUser(Long userId, UserUpdateRequestDTO user) throws IOException;

    User removeUser(Long userId);

    List<User> getAllUsers();

}
