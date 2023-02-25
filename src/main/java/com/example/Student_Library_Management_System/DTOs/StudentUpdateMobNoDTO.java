package com.example.Student_Library_Management_System.DTOs;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class StudentUpdateMobNoDTO {

    @NotNull
    private int id;
    @NotNull
    @Pattern(regexp = "^\\d{10}$",message = "Mobile number should be of 10 digits")
    private String mobNo;

    public StudentUpdateMobNoDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }
}
