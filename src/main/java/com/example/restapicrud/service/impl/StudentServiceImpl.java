package com.example.restapicrud.service.impl;

import com.example.restapicrud.model.Student;
import com.example.restapicrud.repository.StudentRepository;
import com.example.restapicrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }
    @Override
    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }
    @Override
    public Student getStudentsById(Long id){
        return studentRepository.findById(id).orElse(null);
    }
    @Override
    public String deleteStudentsById(Long id){
        studentRepository.deleteById(id);
        return "Student removed !! " + id;
    }
    @Override
    public Student updateStudentsById(Student student){
        return studentRepository.save(student);
    }

}
