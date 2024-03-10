package com.api.blog.Service;

import com.api.blog.Domain.DTO.*;
import com.api.blog.Domain.Entity.User;
import com.api.blog.Exception.UsernameExistException;
import com.api.blog.Mapper.UserMapper;
import com.api.blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public User createUser(UserAddRequestDTO userDTO) throws IOException, UsernameExistException {
        userValidationService.validateUsernameAndEmail(userDTO.getUsername(), userDTO.getEmail(), null);
        String imageName= imageService.saveProfileImage(null, userDTO.getUsername(), userDTO.getProfileImgUrl());
        User user = userMapper.userAddRequestDTOToUser(userDTO);
        user.setProfileImgUrl(imageName);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, UserUpdateRequestDTO userDTO) throws IOException, UsernameExistException {
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
    public PaginationResponseDTO<UserResponseDTO> getAllUsers(UserSearchRequestDTO userSearchRequestDTO) {
        Page<User> users = userRepository.findAllUsers(
                userSearchRequestDTO.getStartDate(),
                userSearchRequestDTO.getEndDate(),
                userSearchRequestDTO.getSearchTerm(),
                userSearchRequestDTO.getPageable()
                );
        List<UserResponseDTO> dtos = userMapper.usersToUserResponseDTOs(users);
        return new PaginationResponseDTO<>(
                dtos,
                users.getTotalElements(),
                users.getNumber() + 1,
                users.getSize()
        );
    }
}

