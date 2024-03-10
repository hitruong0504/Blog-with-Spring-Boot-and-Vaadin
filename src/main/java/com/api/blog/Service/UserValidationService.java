package com.api.blog.Service;

import com.api.blog.Domain.Entity.User;
import com.api.blog.Exception.CustomApiException;
import com.api.blog.Exception.UsernameExistException;

import java.util.Optional;

public interface UserValidationService {
    void validateUsernameAndEmail(String newUsername, String newEmail, Long userId) throws CustomApiException;
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
}
