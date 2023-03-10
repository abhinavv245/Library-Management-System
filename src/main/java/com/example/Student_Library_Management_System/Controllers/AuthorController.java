package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.DTOs.AuthorEntryDTO;
import com.example.Student_Library_Management_System.DTOs.AuthorResponseDTO;
import com.example.Student_Library_Management_System.Exceptions.UserNotFoundException;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@RequestBody @Valid AuthorEntryDTO authorEntryDTO){
        String res=authorService.addAuthor(authorEntryDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/getAuthor")
    public ResponseEntity<AuthorResponseDTO> getAuthor(@RequestParam("id") Integer id) throws UserNotFoundException {
        AuthorResponseDTO authorResponseDTO=authorService.getAuthor(id);
        return new ResponseEntity<>(authorResponseDTO,HttpStatus.OK);
    }
}
