package com.vcart.ecommerce.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadService {

    @Value("${file.upload.dir}")
    private String uploadDir;

    public String uploadFile(MultipartFile file) {
        try {
            // Validate File
            if (file == null || file.isEmpty()) {
                throw new IllegalArgumentException("File is empty or not provided.");
            }

            if (!file.getContentType().equals("application/pdf")) {
                throw new IllegalArgumentException("Only PDF files are allowed.");
            }

            // Ensure the upload directory exists
            Path uploadPath = Paths.get(uploadDir);
            Files.createDirectories(uploadPath); // Create directory if it doesn't exist

            // Resolve the file path and save the file
            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            file.transferTo(filePath.toFile());

            // Return the uploaded file path
            return filePath.toString();
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
            throw new RuntimeException("An error occurred while saving the file. Please try again.", e);
        } catch (IllegalArgumentException e) {
            System.err.println("Validation error: " + e.getMessage());
            throw new IllegalArgumentException("File upload failed: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            throw new RuntimeException("An unexpected error occurred. Please try again later.", e);
        }
    }
}
