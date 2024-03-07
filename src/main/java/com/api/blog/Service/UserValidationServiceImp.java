package com.api.blog.Service;

import com.api.blog.Domain.Entity.User;
import com.api.blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserValidationServiceImp implements UserValidationService{

    @Autowired
    UserRepository userRepository;
    @Override
    public void validateUsernameAndEmail(String newUsername, String newEmail, Long userId) {
        findUserByUsername(newUsername).ifPresent(user -> {
            if (userId == null || !user.getId().equals(userId)) {
                throw new RuntimeException("Username already exists");
            }
        });

        findUserByEmail(newEmail).ifPresent(user -> {
            if (userId == null || !user.getId().equals(userId)) {
                throw new RuntimeException("Email already exists");
            }
        });
    }

    @Transactional(readOnly = true)
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
