package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.IssueBookRequestDTO;
import com.example.Student_Library_Management_System.DTOs.ReturnBookDTO;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.Exceptions.IssueBookException;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Transaction;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import com.example.Student_Library_Management_System.Repositories.CardRepository;
import com.example.Student_Library_Management_System.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    public String issueBook(IssueBookRequestDTO issueBookRequestDTO) throws IssueBookException {

        Transaction transaction= new Transaction();
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setIssueOperation(true);
        transaction.setTransactionStatus(TransactionStatus.PENDING);

       int bookId=issueBookRequestDTO.getBookId();
       int cardId=issueBookRequestDTO.getCardId();
        Book book= new Book();
        Card card= new Card();
       try {
           book=bookRepository.findById(bookId).orElseThrow(()->new IssueBookException("Book not found for book ID " +bookId));
            card=cardRepository.findById(cardId).orElseThrow(()->new IssueBookException("Card not found for card ID " +cardId));
           if(book.isIssued()==true){
               throw new IssueBookException("Book is not available");
           }
           if(card.getCardStatus()!= CardStatus.ACTIVATED){
               throw new IssueBookException("Card is not valid");
           }
       }catch(IssueBookException e) {
           transaction.setTransactionStatus(TransactionStatus.FAILED);
           transactionRepository.save(transaction);
           throw new IssueBookException(e.getMessage());
       }


      //Final goal is to make a transaction Entity, set its attribute
        //and save it.


        //setting the attributes
        transaction.setBook(book);
        transaction.setCard(card);


        //We have reached a success case now.
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
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

    public ReturnBookDTO returnBook(IssueBookRequestDTO returnBook) {
        ReturnBookDTO returnBookDTO= new ReturnBookDTO();
        //transaction attributes
         Transaction transaction= new Transaction();
         transaction.setTransactionId(UUID.randomUUID().toString());
         transaction.setIssueOperation(false);
         transaction.setTransactionStatus(TransactionStatus.PENDING);

         //find the book
        int bookId=returnBook.getBookId();

        //find card
        int cardId= returnBook.getCardId();
        //find the transaction
        String issuedBookTransactionId=null;
        long fine=0;
       List<Transaction> transactionList= transactionRepository.getTransactionForBookAndCard(bookId,cardId);
        Collections.sort(transactionList,(x,y)->{
            return y.getTransactionDate().compareTo(x.getTransactionDate());
        });
        for(Transaction transaction1:transactionList){
            if(transaction1.getTransactionStatus()==TransactionStatus.SUCCESS)
                issuedBookTransactionId=transaction1.getTransactionId();
            break;
        }
         Transaction t= transactionRepository.findByTransactionId(issuedBookTransactionId);
        //calculate the fine
        Date issueDate=t.getTransactionDate();
        LocalDate issueDateLocal= issueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate=LocalDate.now();
        long days=DAYS.between(issueDateLocal,currentDate);
        if(days>15) fine=((days-15)*10);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        //making changes in the book
        Book book=bookRepository.findById(bookId).get();
        book.setIssued(false);
        List<Transaction> transactionList1=book.getTransactionList();
        transactionList1.add(transaction);
        book.setTransactionList(transactionList1);

        //making changes in the card
        Card card= cardRepository.findById(cardId).get();
        List<Transaction> transactionListForCard=card.getTransactionList();
        transactionListForCard.add(transaction);
        card.setTransactionList(transactionListForCard);
        cardRepository.save(card);


        //set the returnBookDTO
        returnBookDTO.setBookId(bookId);
        returnBookDTO.setBookName(book.getName());
        returnBookDTO.setCardId(cardId);
        returnBookDTO.setStudentName(card.getStudent().getName());
        returnBookDTO.setFine(fine);
        returnBookDTO.setTransactionStatus(transaction.getTransactionStatus());
        returnBookDTO.setTransactionId(transaction.getId());

         return returnBookDTO;

    }
}
