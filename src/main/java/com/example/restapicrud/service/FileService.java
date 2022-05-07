package com.example.restapicrud.service;

import com.example.restapicrud.model.FileData;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    FileData saveFile( MultipartFile file);
}
