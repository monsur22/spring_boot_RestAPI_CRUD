package com.example.restapicrud.service.impl;

import com.example.restapicrud.model.FileData;
import com.example.restapicrud.model.Student;
import com.example.restapicrud.repository.FileRepository;
import com.example.restapicrud.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public class FileServiceImpl implements FileService {
    @Autowired
    FileRepository fileRepository;
    @Override
    public FileData saveFile(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());



            FileData dbFile = new FileData(fileName);
            return fileRepository.save(dbFile);

    }
}
