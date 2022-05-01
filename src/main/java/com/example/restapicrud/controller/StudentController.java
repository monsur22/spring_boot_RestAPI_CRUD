package com.example.restapicrud.controller;

import com.example.restapicrud.model.Student;
import com.example.restapicrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public List<Student> getStudents(){
        return studentService.getStudents();

    }
    @PostMapping("/students")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping("/students/{id}")
    public Student getStudentsById( @PathVariable Long id){
        return studentService.getStudentsById(id);
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudentsById(@PathVariable Long id){
        return studentService.deleteStudentsById(id);
    }


    @PostMapping ("/update/students")
    public Student updateStudentsById(@RequestBody  Student student){
        return studentService.updateStudentsById(student);
    }
}
