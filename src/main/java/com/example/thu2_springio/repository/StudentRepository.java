package com.example.thu2_springio.repository;

import com.example.thu2_springio.model.Student;
import com.example.thu2_springio.model.XepLoai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findAll(Pageable pageable);

    List<Student> findByTenContainingIgnoreCase(String ten);

    @Query("SELECT s FROM Student s WHERE LOWER(s.thanhPho) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<Student> findByThanhPho(String name);


    @Query("SELECT s FROM Student s WHERE LOWER(s.thanhPho) LIKE LOWER(CONCAT('%',:name,'%')) OR LOWER(s.ten) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<Student> findByThanhPhoAndTen(String name);

    @Query("SELECT s FROM Student s WHERE YEAR(s.ngaySinh) BETWEEN :nam1 AND :nam2")
    List<Student> findByNgaySinhBetween(int nam1, int nam2);

    @Query("SELECT s FROM Student s WHERE s.xepLoai LIKE CONCAT('%',:xepLoai,'%')")
    List<Student> findByXepLoai(XepLoai xepLoai);


}
