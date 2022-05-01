package com.example.restapicrud.service;

import com.example.restapicrud.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents();
    Student saveStudent(Student student);
    Student getStudentsById(Long id);
    String deleteStudentsById(Long id);
    Student updateStudentsById(Student student);
}
