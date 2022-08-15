package com.example.school_management_software.controller;

import com.example.school_management_software.model.ApiResponse;
import com.example.school_management_software.model.Teacher;
import com.example.school_management_software.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    @GetMapping
    public ResponseEntity getTeachers(){
        ArrayList<Teacher> teachers=teacherService.getTeachers();
        return ResponseEntity.status(200).body(teachers);
    }

    @PostMapping("/add")
    public ResponseEntity addTeacher(@RequestBody @Valid Teacher teacher, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(201).body(new ApiResponse("Teacher added",201));
    }

    @PutMapping("/{index}")
    public ResponseEntity updateTeacher(@PathVariable int index,@RequestBody @Valid Teacher teacher, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        if(teacherService.updateTeacher(index, teacher)) {
            return ResponseEntity.status(201).body(new ApiResponse("Teacher updated", 201));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong index",400));
    }
    @DeleteMapping
    public ResponseEntity deleteTeacher(@RequestParam String id){
        if(teacherService.deleteTeacher(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Teacher deleted", 200));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong id",400));
    }
    @PostMapping("/addClass")
    public ResponseEntity addClass(@RequestParam String teacherId, @RequestParam String classId){
        int addClassCase =teacherService.addClass(teacherId,classId);
        if(addClassCase==0){
            return ResponseEntity.status(201).body(new ApiResponse("Class added to teacher",201));
        } else if (addClassCase==-1){
            return ResponseEntity.status(400).body(new ApiResponse("invalid teacher id",400));
        } else if (addClassCase==1) {
            return ResponseEntity.status(400).body(new ApiResponse("invalid class id",400));
        }
        return ResponseEntity.status(500).body(new ApiResponse("SERVER ERROR!",500));
    }
    //Create endpoint that take class id and return the teacher name for that class
    @PostMapping("/classTeacher")
    public ResponseEntity teacherList(@RequestParam String classId){
        ArrayList<String>teacherList =teacherService.teacherList(classId);
        if(teacherList.size()>0){
            return ResponseEntity.status(200).body(teacherList);
        } else if (teacherList.size()==0){
            return ResponseEntity.status(400).body(new ApiResponse("class don't have teachers",400));
        } else if (teacherList==null) {
            return ResponseEntity.status(400).body(new ApiResponse("invalid class id",400));
        }
        return ResponseEntity.status(500).body(new ApiResponse("SERVER ERROR!",500));
    }
}
