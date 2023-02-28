package com.example.Student_Library_Management_System.DTOs;

import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReturnBookDTO {
    private int bookId;
    private String bookName;
    private int cardId;
    private String studentName;
    private int transactionId;
    private TransactionStatus transactionStatus;
    private long fine;

}
