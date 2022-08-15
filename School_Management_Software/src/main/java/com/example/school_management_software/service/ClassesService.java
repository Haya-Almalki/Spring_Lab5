package com.example.school_management_software.service;

import com.example.school_management_software.model.Classes;
import com.example.school_management_software.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClassesService {
    ArrayList<Classes> classes=new ArrayList<>();
    public ArrayList<Classes> getClasses(){
        return classes;
    }
    public void addClass(Classes class1){
        classes.add(class1);
    }
    public boolean updateClass(int index,Classes class1){
        if (index<0 || index>=classes.size()){
            return false;
        }
        classes.set(index,class1);
        return true;
    }
    public boolean deleteClass(String id){
        for (int i = 0; i <classes.size() ; i++) {
            if(id.equals(classes.get(i).getId())){
                classes.remove(i);
                return true;
            }
        }
        return false;
    }
    public Classes getClassById(String id){
        for (int i = 0; i <classes.size() ; i++) {
            if(id.equals(classes.get(i).getId())){
                return classes.get(i);
            }
        }
        return null;
    }
}
