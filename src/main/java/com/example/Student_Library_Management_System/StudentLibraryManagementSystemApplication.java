package com.example.Student_Library_Management_System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan()
public class StudentLibraryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentLibraryManagementSystemApplication.class, args);
	}

}
