package com.example.school_management_software.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
@Data
public class Student {
    @NotEmpty(message = "id must be not empty")
    private String id;
    @NotEmpty(message = "name must be not empty")
    private String name;
    @NotNull(message = "age must be not empty")
    private int age;
    private ArrayList <Classes>classList;
    @NotEmpty(message = "advisor name must be not empty")
    private String advisorName;
    @NotEmpty(message = "major must be not empty")
    private String major;

    public Student(String id, String name, int age, ArrayList<Classes> classList, String advisorName, String major) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.classList = new ArrayList<>();
        this.advisorName = advisorName;
        this.major = major;
    }
}
