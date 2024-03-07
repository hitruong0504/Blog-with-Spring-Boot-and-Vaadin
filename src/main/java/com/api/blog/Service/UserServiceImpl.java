package com.api.blog.Service;

import com.api.blog.Domain.DTO.UserAddRequestDTO;
import com.api.blog.Domain.DTO.UserUpdateRequestDTO;
import com.api.blog.Domain.Entity.User;
import com.api.blog.Mapper.UserMapper;
import com.api.blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ImageService imageService;

    @Autowired
    UserValidationService userValidationService;

    @Override
    public User createUser(UserAddRequestDTO userDTO) throws IOException {
        userValidationService.validateUsernameAndEmail(userDTO.getUsername(), userDTO.getEmail(), null);
        String imageName= imageService.saveProfileImage(null, userDTO.getUsername(), userDTO.getProfileImgUrl());
        User user = userMapper.userAddRequestDTOToUser(userDTO);
        user.setProfileImgUrl(imageName);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, UserUpdateRequestDTO userDTO) throws IOException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found" + userId));
        userMapper.updateUserFromDTO(userDTO, existingUser);
        userValidationService.validateUsernameAndEmail(existingUser.getUsername(), existingUser.getEmail(), userId);
        String imageName = imageService.saveProfileImage(existingUser.getProfileImgUrl(), userDTO.getUsername(), userDTO.getProfileImgUrl());
        existingUser.setProfileImgUrl(imageName);
        return userRepository.save(existingUser);
    }


    @Override
    public User removeUser(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found" + userId));
        imageService.deleteUserProfileImage(existingUser.getProfileImgUrl());
        userRepository.delete(existingUser);
        return existingUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

