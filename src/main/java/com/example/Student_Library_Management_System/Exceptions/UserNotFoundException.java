package com.example.Student_Library_Management_System.Exceptions;

import java.util.function.Supplier;

public class UserNotFoundException extends Exception  {
    public UserNotFoundException(String message) {
        super(message);
    }
}
