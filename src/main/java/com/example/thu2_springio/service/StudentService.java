package com.example.thu2_springio.service;

import com.example.thu2_springio.dto.StudentDTO;
import com.example.thu2_springio.dto.StudentImageDTO;
import com.example.thu2_springio.model.Student;
import com.example.thu2_springio.model.StudentImage;
import com.example.thu2_springio.model.XepLoai;
import com.example.thu2_springio.repository.StudentImageRepository;
import com.example.thu2_springio.repository.StudentRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentService implements ServiceBasic{
    private final StudentImageRepository studentImageRepository;
    private final StudentRepository repository;

    @Override
    public Page<Student> getStudents(Pageable pageable) {
        return repository.findAll(pageable);
    }

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

    @Override
    public List<Student> fingByThanhPho(String ThanhPho) {
        return repository.findByThanhPho(ThanhPho);
    }

    @Override
    public List<Student> fingByThanhPhoOrTen(String ThanhPhoOrTen) {
        return repository.findByThanhPhoAndTen(ThanhPhoOrTen);
    }



    @Override
    public List<Student> findByNgaySinhBetween(int year1, int year2) {
        return repository.findByNgaySinhBetween(year1, year2);
    }

    @Override
    public List<Student> findByXepLoai(XepLoai XepLoai) {
        return repository.findByXepLoai(XepLoai);
    }

    @Override
    public List<Student> searchStudents(XepLoai xepLoai, String ten, String thanhPho, int startYear, int endYear) {
        return repository.search(xepLoai, ten, thanhPho, startYear, endYear);
    }

    @Override
    public List<StudentImage> getAllStudentImages(Long id) {
        return studentImageRepository.findByStudentId(id);

    }
    @Override
    public StudentImage saveStudentImage(Long id, StudentImageDTO studentImageDTO) {
        Student student = getStudentById(id);
        StudentImage studentImage = StudentImage
                .builder()
                .student(student)
                .imageUrl(studentImageDTO.getImageUrl())
                .build();
        int size = studentImageRepository.findByStudentId(id).size();
        if (size >= 4){
            throw new InvalidParameterException("Mỗi sinh viên chỉ được 4 hnh");
        }
        return studentImageRepository.save(studentImage);
    }


}
