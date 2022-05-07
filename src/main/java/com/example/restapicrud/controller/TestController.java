package com.example.restapicrud.controller;

import com.example.restapicrud.model.FileData;
import com.example.restapicrud.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class TestController {
    @Autowired
    FileRepository fileRepository;
//    @PostMapping("/upload")
//    public FileData uploadFile(@RequestParam("file") MultipartFile file) {
//        System.out.println(file.getOriginalFilename());
//        System.out.println(file.getSize());
//        System.out.println(file.getContentType());
//        System.out.println(file.getName());
//        return fileRepository;
//    }
public String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/image" ;
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException{
        System.out.println("hello");

        String fileData = file.getOriginalFilename();
        Path filenameAndPath = Paths.get(uploadDir,fileData);
        Files.write(filenameAndPath, fileData.getBytes());
        System.out.println(filenameAndPath);
        return  ResponseEntity.ok("working");
    }

}
