package com.example.thu2_springio.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentImageDTO {
    @JsonProperty("student_id")
    @Min(value = 1, message = "Id của student phải lớn hơn 0")
    private Long id;

    @Size(min = 5, max = 200, message = "tên ký tự từ 5 đến 200")
    @JsonProperty("image_url")
    private String imageUrl;

}
