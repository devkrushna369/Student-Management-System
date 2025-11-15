package com.StudentManagement.sms.service;
import com.StudentManagement.sms.model.Student;
import  java.util.List;
// defining the CRUD operations that app supports
//we are going to implement all this operations in StudentServiceImpl.java
public interface StudentService {
    boolean addStudent(Student student);
    Student getStudentById(String id);
    List<Student> getAllStudents();
    boolean updateStudent(String id, Student updated);
    boolean deleteStudent(String id);
}
