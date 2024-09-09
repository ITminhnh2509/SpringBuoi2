package com.example.thu2_springio.repository;

import com.example.thu2_springio.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
