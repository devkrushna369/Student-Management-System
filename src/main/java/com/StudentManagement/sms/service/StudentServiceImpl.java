package com.StudentManagement.sms.service;
import com.StudentManagement.sms.model.Student;
import com.StudentManagement.sms.service.StudentService;
import com.StudentManagement.sms.util.FileUtil;

import java.nio.file.Path;
import java.util.*;

public class StudentServiceImpl implements StudentService {
    private final Map<String, Student> students = new HashMap<>();
    private final Path storagepath;

    public StudentServiceImpl(Path storagepath){
        this.storagepath=storagepath;

        List<Student> loaded = FileUtil.loadStudents(storagepath);
        if(loaded != null){
            for(Student s : loaded){
                students.put(s.getId(),s);
            }
        }
    }

    @Override
    public boolean addStudent(Student student){
        if(student == null || student.getId()==null || student.getId().isBlank()){
            return false;
        }
        if(students.containsKey(student.getId())) return false;
        students.put(student.getId(), student);
        FileUtil.saveStudents(new ArrayList<>(students.values()), storagepath);
        return true;
    }

    @Override
    public Student getStudentById(String id){
        return students.get(id);
    }

    @Override
    public List<Student> getAllStudents(){
        return new ArrayList<>(students.values());
    }

    @Override
    public boolean updateStudent(String id, Student updated){
        if(!students.containsKey(id)) return false;

        updated.setId(id);
        students.put(id, updated);
        FileUtil.saveStudents(new ArrayList<>(students.values()), storagepath);
        return true;
    }

    @Override
    public boolean deleteStudent(String id){
        students.remove(id);
        FileUtil.saveStudents(new ArrayList<>(students.values()),storagepath);
        return true;
    }

}
