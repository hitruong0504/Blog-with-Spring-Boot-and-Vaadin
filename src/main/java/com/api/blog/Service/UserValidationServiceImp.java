package com.api.blog.Service;

import com.api.blog.Domain.Entity.User;
import com.api.blog.Exception.CustomApiException;
import com.api.blog.Exception.UsernameExistException;
import com.api.blog.Repository.UserRepository;
import com.api.blog.enums.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserValidationServiceImp implements UserValidationService{

    @Autowired
    UserRepository userRepository;
    @Override
    public void validateUsernameAndEmail(String newUsername, String newEmail, Long userId) throws CustomApiException {
        Optional<User> userWithUsername = findUserByUsername(newUsername);
        if(userWithUsername.isPresent()){
            User user = userWithUsername.get();
            if(userId == null || !user.getId().equals(userId)){
                throw new CustomApiException(ErrorCode.USERNAME_ALREADY_EXISTS, ErrorCode.USERNAME_ALREADY_EXISTS.getMessage() + ": " + newUsername);
            }
        }

        Optional<User> userWithEmail = findUserByEmail(newEmail);
        if(userWithEmail.isPresent()){
            User user = userWithEmail.get();
            if(userId == null || !user.getId().equals(userId)){
                throw new CustomApiException(ErrorCode.EMAIL_ALREADY_EXISTS, ErrorCode.EMAIL_ALREADY_EXISTS.getMessage() + ": " + newEmail);
            }
        }
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
