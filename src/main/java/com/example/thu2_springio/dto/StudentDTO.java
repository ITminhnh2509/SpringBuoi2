package com.example.thu2_springio.dto;

import com.example.thu2_springio.model.XepLoai;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class StudentDTO {
    @NotBlank(message = "Không được để tên trống")
    @Size(min = 2, max =50, message = "Tên phải từ 2 đến 50 ký tự")
    private String ten;
    @NotBlank(message = "Không được để trống thành phố")
    private String thanhPho;
    @DateTimeFormat(pattern = "dd/MM/yyyy")

    private LocalDate ngaySinh;

    @NotNull(message = "Xếp loại không được để trống")
    @Enumerated(EnumType.STRING)
    private XepLoai xepLoai;
}
