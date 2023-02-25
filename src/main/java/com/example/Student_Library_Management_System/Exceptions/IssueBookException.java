package com.example.Student_Library_Management_System.Exceptions;

import com.example.Student_Library_Management_System.Models.Book;

import java.util.function.Supplier;

public class IssueBookException extends Exception {
    public IssueBookException(String message) {
        super(message);
    }


}
