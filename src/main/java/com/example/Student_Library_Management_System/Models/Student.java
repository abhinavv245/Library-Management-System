package com.example.Student_Library_Management_System.Models;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "student_info")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @NotNull(message = "Username cannot be null")
    private String name;
    @Email(message = "Invalid email address")
    @Column(unique = true)
    private String email;

    @Min(value = 17,message = "Age cannot be less than 17")
    @Max(value = 60,message = "Age cannot be more than 60")
    private int age;
    @NotNull(message = "Please enter the mobile number")
    @Pattern(regexp = "^\\d{10}$",message = "Mobile number should be of 10 digits")
    private String mobNo;
    @NotNull(message = "Please enter the country name")
    private String country;


    //Plain syntax for bidirectional mapping

    //Name of variable of the Parent Entity that we have written in child class foreign key attr.
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Card card;

    public Student() {
    }

    public int getId() {
        return userId;
    }

    public void setId(int id) {
        this.userId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
