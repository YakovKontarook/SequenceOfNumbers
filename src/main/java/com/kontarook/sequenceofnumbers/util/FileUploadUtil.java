package com.kontarook.sequenceofnumbers.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {
    public static String saveFile(String fileName, MultipartFile multipartFile) {
        Path uploadDirectory = Paths.get("files-upload");

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadDirectory.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

            return uploadDirectory + "/" + fileName;
        } catch (IOException e) {
            System.out.println("Error saving uploaded file");
        }
        return null;
    }
}
