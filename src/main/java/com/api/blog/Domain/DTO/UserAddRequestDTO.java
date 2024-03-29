package com.api.blog.Domain.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserAddRequestDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private MultipartFile profileImgUrl;
}
