package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.DTOs.StudentUpdateMobNoDTO;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
       String res=studentService.addStudent(student);
       return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    @GetMapping("/get_user")
    public ResponseEntity<String> getNameByEmail(@RequestParam("email") String email){

       String res = studentService.getNameByEmail(email);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateMobileNo(@RequestBody StudentUpdateMobNoDTO studentUpdateMobNoDTO){
        String res= studentService.updateMobileNo(studentUpdateMobNoDTO);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
