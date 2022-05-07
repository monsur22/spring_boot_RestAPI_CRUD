package com.example.restapicrud.controller;

import com.example.restapicrud.model.FileData;
import com.example.restapicrud.repository.FileRepository;
import com.example.restapicrud.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;

@RestController
public class FileUploadController {
    @Autowired
    FileService fileService;
    @PostMapping("/fileupload")
    public void uploadFile(@RequestParam("file") MultipartFile file){
//        System.out.println("upload");
        FileData fileData = fileService.saveFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/image/")
                .path(fileData.getName())
                .toUriString();



        System.out.println(fileDownloadUri);
    }

}
