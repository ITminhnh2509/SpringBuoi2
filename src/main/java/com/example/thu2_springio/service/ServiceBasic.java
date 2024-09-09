package com.example.thu2_springio.service;

import com.example.thu2_springio.dto.StudentDTO;
import com.example.thu2_springio.model.Student;

import java.util.List;

public interface ServiceBasic {
    List<Student> getallStudent();
    Student getStudentById(Long id);
    Student saveStudent(StudentDTO studentDTO);
    Student updateStudent(Long id, StudentDTO studentDTO);
    void removeStudent(Long id);


}
