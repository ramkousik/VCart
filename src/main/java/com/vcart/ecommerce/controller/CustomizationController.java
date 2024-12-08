package com.vcart.ecommerce.controller;

import com.vcart.ecommerce.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/customization")
public class CustomizationController {
    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/upload")
    public String uploadDesign(@RequestParam MultipartFile file) throws IOException {
        return fileUploadService.uploadFile(file);
    }
}
