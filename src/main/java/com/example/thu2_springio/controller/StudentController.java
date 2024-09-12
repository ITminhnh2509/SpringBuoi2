package com.example.thu2_springio.controller;

import com.example.thu2_springio.dto.StudentDTO;
import com.example.thu2_springio.model.Student;
import com.example.thu2_springio.model.XepLoai;
import com.example.thu2_springio.response.ApiResponse;
import com.example.thu2_springio.response.StudentListResponse;
import com.example.thu2_springio.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class StudentController {

    private final StudentService service;
    @GetMapping
    public ResponseEntity<?> studentList(){
        List<Student> students = service.getallStudent();
        if(students.size() == 0){
            ApiResponse apiResponse = ApiResponse
                    .builder()
                    .data(null)
                    .message("No data found")
                    .status(HttpStatus.NOT_FOUND.value())
                    .build();
            return ResponseEntity.badRequest().body(apiResponse);
        }
        return ResponseEntity.ok().body(students);
    }


    @GetMapping("/list")
    public ResponseEntity<?> getListStudentPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = service.getStudents(pageable);
        int totalPages = studentPage.getTotalPages();

        StudentListResponse studentListResponse = StudentListResponse
                .builder()
                .totalPages(totalPages)
                .studentList(studentPage.getContent())
                .build();
        ApiResponse apiResponse = ApiResponse
                .builder()
                .status(HttpStatus.OK.value())
                .message("Get list student success")
                .data(studentListResponse)
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveStudent(@Valid @RequestBody StudentDTO studentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> listErro = bindingResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            ApiResponse apiResponse = ApiResponse
                    .builder()
                    .data(listErro)
                    .message("Validation failed")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .build();
            return ResponseEntity.badRequest().body(apiResponse);
        }
        Student student = service.saveStudent(studentDTO);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(student)
                .message("Insert success")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") Long id,@Valid @RequestBody StudentDTO studentDTO
            , BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<String> listErro = bindingResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            ApiResponse apiResponse = ApiResponse
                    .builder()
                    .data(listErro)
                    .message("Validation failed")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .build();
            return ResponseEntity.badRequest().body(apiResponse);
        }
        Student student = service.updateStudent(id, studentDTO);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(student)
                .message("Update Successful")
                .status(HttpStatus.OK.value())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id){
        Student student = service.getStudentById(id);
        if(student == null){
            ApiResponse apiResponse = ApiResponse
                    .builder()
                    .data(id)
                    .message("Student not found")
                    .status(HttpStatus.NOT_FOUND.value())
                    .build();
            return ResponseEntity.badRequest().body(apiResponse);
        }
        service.removeStudent(id);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(id)
                .message("delete successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getStudentByName(@RequestParam("ten") String ten){
        List<Student> students = service.fingByThanhPhoOrTen(ten);
        if(students.size() == 0){
            ApiResponse apiResponse = ApiResponse
                    .builder()
                    .data(null)
                    .message("No data found")
                    .status(HttpStatus.NOT_FOUND.value())
                    .build();
            return ResponseEntity.badRequest().body(apiResponse);
        }

        return ResponseEntity.ok().body(students);
    }

    @GetMapping("/getbynamsinh")
    public ResponseEntity<?> getStudentByNamSinh(@RequestParam("namSinh1") int namSinh1, @RequestParam("namsinh2") int namsinh2) {
        List<Student> students = service.findByNgaySinhBetween(namSinh1, namsinh2);
        if (students.isEmpty()) {
            ApiResponse apiResponse = ApiResponse.builder()
                    .data(null)
                    .message("No data found")
                    .status(HttpStatus.NOT_FOUND.value())
                    .build();
            return ResponseEntity.badRequest().body(apiResponse);
        }
        ApiResponse apiResponse = ApiResponse.builder()
                .data(students)
                .message("Data found")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
    @GetMapping("/getbyxeploai")
    public ResponseEntity<?> getStudentByXepLoai(@RequestParam("xepLoai") XepLoai xepLoai) {
        List<Student> students = service.findByXepLoai(xepLoai);
        if (students.isEmpty()) {
            ApiResponse apiResponse = ApiResponse.builder()
                    .data(null)
                    .message("No data found")
                    .status(HttpStatus.NOT_FOUND.value())
                    .build();
            return ResponseEntity.badRequest().body(apiResponse);
        }
        ApiResponse apiResponse = ApiResponse.builder()
                .data(students)
                .message("Data found")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }


}
