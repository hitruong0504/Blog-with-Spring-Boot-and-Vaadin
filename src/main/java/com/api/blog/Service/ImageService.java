package com.api.blog.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String saveProfileImage(String imageNameOld, String imageNameNew, MultipartFile profileImg) throws IOException;
    void deleteUserProfileImage(String profileImageUrl);
}
