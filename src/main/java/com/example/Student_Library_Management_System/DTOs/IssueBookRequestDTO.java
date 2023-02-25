package com.example.Student_Library_Management_System.DTOs;

import javax.validation.constraints.NotNull;

public class IssueBookRequestDTO {

    @NotNull(message = "Please enter the Book Id")
    private int bookId;
    @NotNull(message = "Please enter the Card Id")
    private int cardId;

    public IssueBookRequestDTO() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
}
