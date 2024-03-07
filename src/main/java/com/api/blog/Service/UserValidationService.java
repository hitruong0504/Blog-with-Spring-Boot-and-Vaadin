package com.api.blog.Service;

import com.api.blog.Domain.Entity.User;

import java.util.Optional;

public interface UserValidationService {
    void validateUsernameAndEmail(String newUsername, String newEmail, Long userId);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
}
