package com.example.school_management_software.service;

import com.example.school_management_software.model.Classes;
import com.example.school_management_software.model.Student;
import com.example.school_management_software.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TeacherService {
    ArrayList<Teacher>teachers=new ArrayList<>();
    private final ClassesService classesService;

    public ArrayList<Teacher> getTeachers(){
        return teachers;
    }
    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }
    public boolean updateTeacher(int index,Teacher teacher){
        if (index<0 || index>=teachers.size()){
            return false;
        }
        teachers.set(index,teacher);
        return true;
    }
    public boolean deleteTeacher(String id){
        for (int i = 0; i <teachers.size() ; i++) {
            if(id.equals(teachers.get(i).getId())){
                teachers.remove(i);
                return true;
            }
        }
        return false;
    }
    public Teacher getTeacherById(String id){
        for (int i = 0; i <teachers.size() ; i++) {
            if(id.equals(teachers.get(i).getId())){
                return teachers.get(i);
            }
        }
        return null;
    }
    //Create endpoint that takes teacher id and class id and add it to the classList
    public int addClass(String teacherId,String classId){
        Teacher teacher=getTeacherById(teacherId);
        Classes class1=classesService.getClassById(classId);
        if(teacher==null){
            return -1;
        }
        if(class1==null){
            return 1;
        }
        teacher.getClassList().add(class1);
        return 0;
    }
    //Create endpoint that take class id and return the teacher name for that class
    public ArrayList<String> teacherList(String classId){
        Classes class1=classesService.getClassById(classId);
        if(class1==null){
            return null;
        }
        ArrayList<String> teachersList=new ArrayList<>();
        for (int i = 0; i <teachers.size() ; i++) {
            for (int j = 0; j <teachers.get(i).getClassList().size() ; j++) {
                if (teachers.get(i).getClassList().get(j).getId().equals(classId)) {
                    teachersList.add(teachers.get(i).getName());
            }
            }
        }
        return teachersList;
    }
}
