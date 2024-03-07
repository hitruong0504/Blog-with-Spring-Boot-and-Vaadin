package com.api.blog.Controller;

import com.api.blog.Domain.DTO.UserAddRequestDTO;
import com.api.blog.Domain.DTO.UserResponseDTO;
import com.api.blog.Domain.DTO.UserUpdateRequestDTO;
import com.api.blog.Domain.Entity.User;
import com.api.blog.Mapper.UserMapper;
import com.api.blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = {"/", "/user"})
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<User> addUser(UserAddRequestDTO userAddRequestDTO) throws IOException {
        User newUser = userService.createUser(userAddRequestDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable Long userId, UserUpdateRequestDTO userUpdateRequestDTO) throws IOException {
        User updatedUser = userService.updateUser(userId, userUpdateRequestDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable Long userId) {
        User deletedUser = userService.removeUser(userId);
        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        List<UserResponseDTO> userDtoList = userMapper.usersToUserResponseDTOs(userList);
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }
}