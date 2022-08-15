package com.example.school_management_software.service;

import com.example.school_management_software.model.Classes;
import com.example.school_management_software.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
@RequiredArgsConstructor
public class StudentService {
    private ArrayList<Student> students=new ArrayList<>();
    private final ClassesService classesService;
    public ArrayList<Student> getStudents() {
        return students;
    }
    public void addStudent(Student student){
        students.add(student);
    }
    public boolean updateStudent(int index, Student student){
        if(index<0|| index>= students.size()){
            return false;
        }
        students.set(index,student);
        return true;
    }
    public boolean deleteStudent(String id){
        for (int i = 0; i <students.size(); i++) {
            if(students.get(i).getId().equals(id)){
                students.remove(i);
                return true;
            }
        }
        return false;
    }
    public Student getStudentById(String studentId) {
        for (int i = 0; i <students.size(); i++) {
            if(students.get(i).getId().equals(studentId)){
                return students.get(i);
            }
        }
        return null;
    }
//Create endpoint that takes student id and class id and add it to the classList
    public int addClass(String studentId,String classId){
        Student student=getStudentById(studentId);
        Classes class1=classesService.getClassById(classId);
        if(student==null){
            return -1;
        }
        if(class1==null){
            return 1;
        }
        student.getClassList().add(class1);
        return 0;
    }
//Create endpoint that takes student id and major and change the student major
// ( changing the major will drop all the classes that the student attended to )
    public int changeMajor(String id, String major){
        Student student=getStudentById(id);
        if(student==null){
            return -1;
        }
        if(student.getMajor().equals(major)){
            return 1;
        }
        student.setMajor(major);
        student.setClassList(new ArrayList<>());
        return 0;
    }
    //Create endpoint that takes class id and return the student list
    public ArrayList<Student> studentList(String classId){
        Classes class1=classesService.getClassById(classId);
        if(class1==null){
            return null;
        }
        ArrayList<Student> students1=new ArrayList<>();
        for (int i = 0; i <students.size() ; i++) {
            for (int j = 0; j <students.get(i).getClassList().size() ; j++) {
                if (students.get(i).getClassList().get(j).getId().equals(classId)) {
                    students1.add(students.get(i));
                }
            }
        }
        return students1;
    }
}