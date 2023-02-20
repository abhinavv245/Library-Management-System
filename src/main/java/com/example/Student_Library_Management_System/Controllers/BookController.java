package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.DTOs.BookRequestDTO;
import com.example.Student_Library_Management_System.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    public ResponseEntity<String> addBook(@RequestBody BookRequestDTO bookRequestDTO){
        String res= bookService.addBook(bookRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

}
