package com.example.school_management_software.controller;

import com.example.school_management_software.model.ApiResponse;
import com.example.school_management_software.model.Classes;
import com.example.school_management_software.service.ClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/Classes")
@RequiredArgsConstructor
public class ClassesController {
    private final ClassesService classesService;
    @GetMapping
    public ResponseEntity getClasses(){
        ArrayList<Classes> classes=classesService.getClasses();
        return ResponseEntity.status(200).body(classes);
    }
    @PostMapping("/add")
    public ResponseEntity addClasses(@RequestBody @Valid Classes classes, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        classesService.addClass(classes);
        return ResponseEntity.status(201).body(new ApiResponse("Class added",201));
    }
    @PutMapping("/{index}")
    public ResponseEntity updateClasses(@PathVariable int index, @RequestBody @Valid Classes classes, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        if(classesService.updateClass(index, classes)) {
            return ResponseEntity.status(201).body(new ApiResponse("class updated", 201));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong index",400));
    }
    @DeleteMapping
    public ResponseEntity deleteClass(@RequestParam String id){
        if(classesService.deleteClass(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("class deleted", 200));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong id",400));
    }
}