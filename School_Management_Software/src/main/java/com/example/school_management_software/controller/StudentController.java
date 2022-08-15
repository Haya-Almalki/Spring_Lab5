package com.example.school_management_software.controller;

import com.example.school_management_software.model.ApiResponse;
import com.example.school_management_software.model.Student;
import com.example.school_management_software.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping
    public ResponseEntity getStudents(){
        ArrayList <Student> students=studentService.getStudents();
        return ResponseEntity.status(200).body(students);
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        studentService.addStudent(student);
        return ResponseEntity.status(201).body(new ApiResponse("student added",201));
    }

    @PutMapping("/{index}")
    public ResponseEntity updateStudent(@PathVariable int index,@RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        if(studentService.updateStudent(index, student)) {
            return ResponseEntity.status(201).body(new ApiResponse("student updated", 201));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong index",400));
    }
    @DeleteMapping
    public ResponseEntity deleteStudent(@RequestParam String id){
        if(studentService.deleteStudent(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("student deleted", 200));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong id",400));
    }
    @PostMapping("/addClass")
    public ResponseEntity addClass(@RequestParam String studentId, @RequestParam String classId){
        int addClassCase =studentService.addClass(studentId,classId);
        if(addClassCase==0){
            return ResponseEntity.status(201).body(new ApiResponse("Class added to student",201));
        } else if (addClassCase==-1){
            return ResponseEntity.status(400).body(new ApiResponse("invalid student id",400));
        } else if (addClassCase==1) {
            return ResponseEntity.status(400).body(new ApiResponse("invalid class id",400));
        }
        return ResponseEntity.status(500).body(new ApiResponse("SERVER ERROR!",500));
    }
    @PostMapping("/changeMajor")
    public ResponseEntity changeMajor(@RequestParam String studentId,@RequestParam String major){
        int changeMajorCase =studentService.changeMajor(studentId,major);
        if(changeMajorCase==0){
            return ResponseEntity.status(201).body(new ApiResponse("major changed",201));
        } else if (changeMajorCase==-1){
            return ResponseEntity.status(400).body(new ApiResponse("invalid student id",400));
        } else if (changeMajorCase==1) {
            return ResponseEntity.status(400).body(new ApiResponse("It's the same major",400));
        }
        return ResponseEntity.status(500).body(new ApiResponse("SERVER ERROR!",500));
    }
    @PostMapping("/classStudent")
    public ResponseEntity studentList(@RequestParam String classId){
        ArrayList<Student> studentsList =studentService.studentList(classId);
        if(studentsList.size()>0){
            return ResponseEntity.status(200).body(studentsList);
        } else if (studentsList.size()==0){
            return ResponseEntity.status(400).body(new ApiResponse("class don't have student",400));
        } else if (studentsList==null) {
            return ResponseEntity.status(400).body(new ApiResponse("invalid class id",400));
        }
        return ResponseEntity.status(500).body(new ApiResponse("SERVER ERROR!",500));
    }
}
