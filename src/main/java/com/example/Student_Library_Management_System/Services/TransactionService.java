package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.IssueBookRequestDTO;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Transaction;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import com.example.Student_Library_Management_System.Repositories.CardRepository;
import com.example.Student_Library_Management_System.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    public String issueBook(IssueBookRequestDTO issueBookRequestDTO) throws Exception {

       int bookId=issueBookRequestDTO.getBookId();
       int cardId=issueBookRequestDTO.getCardId();

        Book book=bookRepository.findById(bookId).get();
        Card card=cardRepository.findById(cardId).get();

      //Final goal is to make a transaction Entity, set its attribute
        //and save it.
        Transaction transaction= new Transaction();

        //setting the attributes
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setIssueOperation(true);
        transaction.setTransactionStatus(TransactionStatus.PENDING);

        //attribute left is success/Failure
        //Check for validations
        if(book==null||book.isIssued()==true){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book is not available");
        }
        if(card==null||card.getCardStatus()!= CardStatus.ACTIVATED){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Card is not valid");
        }

        //We have reached a success case now.

        //set attributes of a book
        book.setIssued(true);
        List<Transaction> transactionList=book.getTransactionList();
        transactionList.add(transaction);
        book.setTransactionList(transactionList);

        //making changes in the card
        List<Book> issuedBooksForCard=card.getBooksIssued();
        issuedBooksForCard.add(book);
        card.setBooksIssued(issuedBooksForCard);

        //transaction list for card
        List<Transaction> transactionListForCard=card.getTransactionList();
        transactionListForCard.add(transaction);
        card.setTransactionList(transactionListForCard);

        //save the parent
        cardRepository.save(card);
        //automatically by cascading : book and transaction will be saved.

        return "Book issued successfully";
    }

    public String getTransactionEntry(Integer bookId, Integer cardId) {
        List<Transaction> transactionList=transactionRepository.getTransactionForBookAndCard(bookId,cardId);
        String transactionId=transactionList.get(0).getTransactionId();
        return transactionId;
    }
}
