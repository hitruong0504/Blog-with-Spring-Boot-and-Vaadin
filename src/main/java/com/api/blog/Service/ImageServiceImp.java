package com.api.blog.Service;

import com.api.blog.Utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.api.blog.constant.StorageConstant.DEFAULT_IMAGE_NAME;
import static com.api.blog.constant.StorageConstant.PROFILE_STORAGE_PATH;
@Service
public class ImageServiceImp implements ImageService{
    @Override
    public String saveProfileImage(String imageNameOld, String imageNameNew, MultipartFile profileImg) throws IOException {
        if(profileImg.getSize() == 0)
            return DEFAULT_IMAGE_NAME;

        Path userFolderPath = Paths.get(PROFILE_STORAGE_PATH).toAbsolutePath().normalize();
        if(imageNameOld != null){
            FileUtils.deleteFileIfExists(userFolderPath.resolve(imageNameOld));
        }
        FileUtils.ensureDirectoryExists(userFolderPath);
        String originalName = profileImg.getOriginalFilename();
        String extension = originalName.substring(originalName.lastIndexOf(".") + 1);
        String imageName = imageNameNew + "." + extension;
        FileUtils.saveFile(profileImg.getInputStream(), userFolderPath.resolve(imageName), StandardCopyOption.REPLACE_EXISTING);
        return imageName;
    }

    @Override
    public void deleteUserProfileImage(String profileImageUrl) {
        try{
            Path userFolderPath = Paths.get(PROFILE_STORAGE_PATH).toAbsolutePath().normalize();
            FileUtils.deleteFileIfExists(userFolderPath.resolve(profileImageUrl));
        }catch (IOException e){
            throw new RuntimeException("Could not delete profile image. Please try again!", e);
        }
    }
}
