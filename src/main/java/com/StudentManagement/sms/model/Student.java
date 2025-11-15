package com.StudentManagement.sms.model;

public class Student {
    private String id;
    private String name;
    private int age;
    private String department;
    private String phone;
    private String email;

    public Student(){

    }

    public Student(String id, String name,int age, String departent, String phone, String email){
        this.id=id;
        this.name=name;
        this.age=age;
        this.department=departent;
        this.phone=phone;
        this.email=email;
    }

    //getters & setters

    public String getId(){ return id; }
    public void setId(String id){ this.id=id; }

    public String getName(){ return name;}
    public void setName(String name){ this.name = name;}

    public int getAge(){ return age;}
    public void setAge(){ this.age=age;}

    public String getDepartment(){ return department;}
    public void setDepartment(String department){ this.department=department;}

    public String getPhone(){return phone;}
    public void setPhone(String phone){this.phone=phone;}

    public String getEmail(){return email;}
    public void setEmail(String email){ this.email=email;}

    //user defined toCSV() function for file saving
    public String toCSV(){
        return String.join(",",id,name,String.valueOf(age),department,phone,email);
    }

    //parsing the csv line into Student, returns null if invalid
    public static Student fromCSV(String line){
        if(line == null || line.trim().isEmpty()) return null;
        String[] tokens = line.split(",",-1);
        if(tokens.length < 6) return null;
        try {
            String id = tokens[0].trim();
            String name = tokens[1].trim();
            int age =Integer.parseInt(tokens[2].trim());
            String department = tokens[3].trim();
            String phone = tokens[4].trim();
            String email = tokens[5].trim();
            return new Student(id,name,age,department,phone,email);
        } catch (NumberFormatException e){
            return null;
        }
    }

    @Override
    public String toString(){
        return "Student{" +
                "id='" +id + '\''+
                ", name='" + name + '\'' +
                ".department='" + department + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';

    }

    //Iske uper hi bhai
}
