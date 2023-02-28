package com.example.Student_Library_Management_System.Services;


import com.example.Student_Library_Management_System.DTOs.AuthorEntryDTO;
import com.example.Student_Library_Management_System.DTOs.AuthorResponseDTO;
import com.example.Student_Library_Management_System.DTOs.BookResponseDTO;
import com.example.Student_Library_Management_System.Exceptions.UserNotFoundException;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String addAuthor(AuthorEntryDTO authorEntryDTO) {
        //Important step is : in the params : the object i
        //of type DTO but the repository interacts only with entities

        //Solution : Convert authorEntryDto ---> Author
        //Created an object of type Author

        Author author= new Author();
        //we are setting its attribute so that we can save
        //correct values in the db.

        author.setName(authorEntryDTO.getName());
        author.setAge(authorEntryDTO.getAge());
        author.setCountry(authorEntryDTO.getCountry());
        author.setRating(authorEntryDTO.getRating());

        authorRepository.save(author);
        return "Author added successfully";
    }

    public AuthorResponseDTO getAuthor(Integer id) throws UserNotFoundException {
     Optional<Author> result=authorRepository.findById(id);
     if(result.isEmpty()) throw new UserNotFoundException("Author not found");
     Author author=result.get();
     AuthorResponseDTO authorResponseDTO= new AuthorResponseDTO();

        //Set its attributes.
        //List<Book> --> List<BookResponseDto>
        List<Book> bookList=author.getBooksWritten();
        List<BookResponseDTO> booksWrittenDTO=new ArrayList<>();

        for(Book book:bookList){
            BookResponseDTO bookResponseDTO= new BookResponseDTO();
            bookResponseDTO.setName(book.getName());
            bookResponseDTO.setPages(book.getPages());
            bookResponseDTO.setGenre(book.getGenre());

            booksWrittenDTO.add(bookResponseDTO);
        }

        //Set attributes for authorResponse Dto
        authorResponseDTO.setName(author.getName());
        authorResponseDTO.setAge(author.getAge());
        authorResponseDTO.setCountry(author.getCountry());
        authorResponseDTO.setRating(author.getRating());
        authorResponseDTO.setBooksWritten(booksWrittenDTO);
        return authorResponseDTO;
    }
}
