package com.ecommerce.project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        // File names of current/Original file name
        String originalFilename = file.getOriginalFilename();

        // Generate a unique file name so it cannot override other the same name (by UUID)
        String randomId = UUID.randomUUID().toString();

        // mat.jpeg -> 1234.jpeg (1234 UUID)
        String fileName = randomId.concat(originalFilename.substring(originalFilename.lastIndexOf('.')));
        String filePath = path + File.separator + fileName;

        // check if path and directory are created
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();  // Create the folder and all its parent directories
        }

        // Save the image file to the folder
        Files.copy(file.getInputStream(), Paths.get(filePath));  // Use full path including folder
        // upload to server
        return fileName;  // Return just the file name (not the full path)
    }
}
