package com.example.school_management_software.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
@Data
public class Teacher {
    @NotEmpty(message = "id must be not empty")
    private String id;
    @NotEmpty(message = "name must be not empty")
    private String name;
    private ArrayList <Classes>classList;

    public Teacher(String id, String name, ArrayList<Classes> classList) {
        this.id = id;
        this.name = name;
        this.classList = new ArrayList<>();
    }
}
