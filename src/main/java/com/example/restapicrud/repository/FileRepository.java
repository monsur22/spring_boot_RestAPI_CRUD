package com.example.restapicrud.repository;

import com.example.restapicrud.model.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository <FileData, Long> {
}
