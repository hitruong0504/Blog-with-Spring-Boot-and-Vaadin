package com.api.blog.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    public static void ensureDirectoryExists(Path directory) throws IOException {
        if(Files.notExists(directory)){
            Files.createDirectories(directory);
        }
    }

    public static void deleteFileIfExists(Path file) throws IOException {
        try{
            Files.deleteIfExists(file);
        }catch(IOException e){
            throw new IOException("Could not delete file: " + e.getMessage());
        }
    }

    public static void saveFile(InputStream inputStream, Path target, CopyOption... options) throws IOException {
        Files.copy(inputStream, target, options);
    }
}
