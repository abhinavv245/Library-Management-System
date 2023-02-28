package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.DTOs.IssueBookRequestDTO;
import com.example.Student_Library_Management_System.DTOs.ReturnBookDTO;
import com.example.Student_Library_Management_System.Exceptions.IssueBookException;
import com.example.Student_Library_Management_System.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issueBook")
    public ResponseEntity<String> issueBook(@RequestBody @Valid IssueBookRequestDTO issueBookRequestDTO) throws IssueBookException {
        String res =transactionService.issueBook(issueBookRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/returnBook")
    public ResponseEntity<ReturnBookDTO> returnBook(@RequestBody IssueBookRequestDTO returnBook){
        ReturnBookDTO res=transactionService.returnBook(returnBook);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @GetMapping("/getTxnInfo")
    public ResponseEntity<String> getTransactionEntry(@RequestParam Integer bookId,@RequestParam Integer cardId){
        String res=transactionService.getTransactionEntry(bookId,cardId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
