package com.example.thu2_springio.dto;

import com.example.thu2_springio.model.XepLoai;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class StudentDTO {
    @NotBlank(message = "Không được để tên trống")
    private String ten;
    @NotBlank(message = "Không được để trống thành phố")
    private String thanhPho;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate ngaySinh;
    @NotNull(message = "Không được để trống xep loại")
    private XepLoai xepLoai;
}
