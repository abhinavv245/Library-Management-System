package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.BookRequestDTO;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;
    public String addBook(BookRequestDTO bookRequestDTO) {
        //get the author
        Author author= authorRepository.findById(bookRequestDTO.getAuthorId()).get();


        //Convertor
        //We have created this Entity so that we can save it into the DB.
        Book book= new Book();

        //Basic attributes are being from Dto to the Entity Layer
        book.setName(bookRequestDTO.getName());
        book.setGenre(bookRequestDTO.getGenre());
        book.setPages(bookRequestDTO.getPages());
        book.setIssued(false);

        //Setting the foreign key attr in the child class :
        book.setAuthor(author);

        //We need to update the listOfBooks written in the parent class
        List<Book> bookList= author.getBooksWritten();
        bookList.add(book);
        author.setBooksWritten(bookList);


        //Now the book is to be saved, but also author is also to be saved.

        //Why do we need to again save (updating) the author ?? bcz
        //because the author Entity has been updated....we need to save it again.
        authorRepository.save(author);

        //.save function works both as save function and as update function


        //bookRepo.save is not required : bcz it will be autocalled by cascading
        //effect

        return "Book added successfully";


    }
}
