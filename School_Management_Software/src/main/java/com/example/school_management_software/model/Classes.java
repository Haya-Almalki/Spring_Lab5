package com.example.school_management_software.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor @Data
public class Classes {
    @NotEmpty(message = "id must be not empty")
    private String id;
    @NotEmpty(message = "name must be not empty")
    private String name;

}
