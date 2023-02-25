package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.DTOs.StudentResponseDTO;
import com.example.Student_Library_Management_System.DTOs.StudentUpdateMobNoDTO;
import com.example.Student_Library_Management_System.Exceptions.UserNotFoundException;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;


    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestBody @Valid Student student){
       String res=studentService.addStudent(student);
       return new ResponseEntity<>(res,HttpStatus.CREATED);
    }
    @GetMapping("/get_user")
    public ResponseEntity<String> getNameByEmail(@RequestParam("email") String email) throws UserNotFoundException {

       String res = studentService.getNameByEmail(email);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/getStudent/{id}")
    public ResponseEntity<StudentResponseDTO> getStudent(@PathVariable("id") int id) throws UserNotFoundException {

        StudentResponseDTO res = studentService.getStudent(id);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<String> updateMobileNo(@RequestBody @Valid StudentUpdateMobNoDTO studentUpdateMobNoDTO) throws UserNotFoundException {
        String res= studentService.updateMobileNo(studentUpdateMobNoDTO);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
