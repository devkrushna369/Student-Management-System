package com.StudentManagement.sms.util;
import com.StudentManagement.sms.model.Student;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class FileUtil {
    private FileUtil(){};

    public static List<Student> loadStudents(Path path){
        try {
            if (Files.notExists(path)){
                 Files.createDirectories(path.getParent()==null ? Paths.get("."): path.getParent());
                 Files.createFile(path);
                 return new ArrayList<>();
            }
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            List<Student> students = new ArrayList<>();
            for( String line : lines){
                Student s = Student.fromCSV(line);
                if(s != null) students.add(s);
            }
            return students;

        } catch (IOException e){
            System.out.println("Failed To Load Students FromFile : "+ e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveStudents(List<Student> list, Path path){
        try{
            List<String> lines = new ArrayList<>();
            for(Student s : list){
                lines.add(s.toCSV());
            }
            if(path.getParent() != null && Files.notExists(path.getParent())){
                Files.createDirectories(path.getParent());
            }
            Files.write(path, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE
            , StandardOpenOption.TRUNCATE_EXISTING);


        } catch (IOException e){
            System.out.println("Failed to save students to file : " + e.getMessage());
        }
    }
}

