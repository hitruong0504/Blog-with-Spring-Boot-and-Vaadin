package com.api.blog.Mapper;

import com.api.blog.Domain.DTO.UserAddRequestDTO;
import com.api.blog.Domain.DTO.UserResponseDTO;
import com.api.blog.Domain.DTO.UserUpdateRequestDTO;
import com.api.blog.Domain.Entity.User;
import org.mapstruct.*;

import java.util.List;

import static com.api.blog.constant.StorageConstant.PROFILE_STORAGE_PATH;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "profileImgUrl", ignore = true)
    User userAddRequestDTOToUser(UserAddRequestDTO dto);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "profileImgUrl", ignore = true)
    User updateUserFromDTO(UserUpdateRequestDTO dto, @MappingTarget User user);


    UserResponseDTO userToUserResponseDTO(User user, @MappingTarget UserResponseDTO dto);

    List<UserResponseDTO> usersToUserResponseDTOs(List<User> users);

    @AfterMapping
    default void customizeImage(User user, @MappingTarget UserResponseDTO dto) {
        String customizeImagePath = PROFILE_STORAGE_PATH + user.getProfileImgUrl();
        dto.setProfileImgUrl(customizeImagePath);
    }
}
