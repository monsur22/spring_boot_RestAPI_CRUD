package com.example.restapicrud.repository;

import com.example.restapicrud.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
//    Student findByName(String name);
}
