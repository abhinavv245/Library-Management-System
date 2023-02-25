package com.example.Student_Library_Management_System.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponseDTO {
    private String name;
    private String email;
    private int age;
    private String mobNo;
    private String country;
}
