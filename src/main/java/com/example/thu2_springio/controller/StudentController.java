package com.example.thu2_springio.controller;

import com.example.thu2_springio.dto.StudentDTO;
import com.example.thu2_springio.model.Student;
import com.example.thu2_springio.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("{api-prefix}/student")
public class StudentController {
    @Autowired
    StudentService service;
    @GetMapping
    public List<Student> studentList(){
        return service.getallStudent();
    }

    @PostMapping("/save")
    public Student saveStudent(@RequestBody StudentDTO studentDTO){
        return service.saveStudent(studentDTO);
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable("id") Long id,@RequestBody StudentDTO studentDTO){
        return service.updateStudent(id,studentDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id){
        service.removeStudent(id);
        return "delete student with id: " + id;
    }
}
