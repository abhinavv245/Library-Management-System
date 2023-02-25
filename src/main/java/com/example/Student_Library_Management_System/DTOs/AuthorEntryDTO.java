package com.example.Student_Library_Management_System.DTOs;

import javax.validation.constraints.NotNull;

public class AuthorEntryDTO {

    @NotNull(message = "Please enter the name of the author")
    private String name;


    private int age;

    private String country;

    @NotNull(message = "Please enter the author rating")
    private double rating;

    public AuthorEntryDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
