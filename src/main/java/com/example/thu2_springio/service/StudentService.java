package com.example.thu2_springio.service;

import com.example.thu2_springio.dto.StudentDTO;
import com.example.thu2_springio.model.Student;
import com.example.thu2_springio.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService implements ServiceBasic{

    @Autowired
    StudentRepository repository;
    @Override
    public List<Student> getallStudent() {
        return repository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Student saveStudent(StudentDTO studentDTO) {
        Student student = Student
                .builder()
                .ten(studentDTO.getTen())
                .ngaySinh(studentDTO.getNgaySinh())
                .xepLoai(studentDTO.getXepLoai())
                .thanhPho(studentDTO.getThanhPho())
                .build();
        return repository.save(student);
    }

    @Override
    public Student updateStudent(Long id, StudentDTO studentDTO) {
            Student student = getStudentById(id);
        if (student != null) {
            student.setTen(studentDTO.getTen());
            student.setThanhPho(studentDTO.getThanhPho());
            student.setNgaySinh(studentDTO.getNgaySinh());
            student.setXepLoai(studentDTO.getXepLoai());
            return repository.save(student);
        }
        return null;
    }

    @Override
    public void removeStudent(Long id) {
    repository.deleteById(id);
    }
}
