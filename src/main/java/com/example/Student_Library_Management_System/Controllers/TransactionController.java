package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.DTOs.IssueBookRequestDTO;
import com.example.Student_Library_Management_System.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issueBook")
    public ResponseEntity<String> issueBook(@RequestBody IssueBookRequestDTO issueBookRequestDTO){
        String res= null;
        try {
            res = transactionService.issueBook(issueBookRequestDTO);
        } catch (Exception e) {
           e.getMessage();
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/getTxnInfo")
    public ResponseEntity<String> getTransactionEntry(@RequestParam Integer bookId,@RequestParam Integer cardId){
        String res=transactionService.getTransactionEntry(bookId,cardId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
