package com.example.Student_Library_Management_System.DTOs;

public class IssueBookRequestDTO {

    private int bookId;
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
