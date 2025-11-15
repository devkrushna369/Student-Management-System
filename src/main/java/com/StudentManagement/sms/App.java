package com.StudentManagement.sms;

import com.StudentManagement.sms.model.Student;
import com.StudentManagement.sms.service.StudentService;
import com.StudentManagement.sms.service.StudentServiceImpl;

import javax.xml.transform.Source;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Console-driven application entry point.
 */
public class App {
    private static final Path STORAGE = Paths.get("src\\main\\resources\\students.txt");

    public static void main(String[] args) {
        StudentService service = new StudentServiceImpl(STORAGE);
        Scanner sc = new Scanner(System.in);

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Enter Choice : ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    addStudentFlow(sc, service);
                    break;
                case "2":
                    viewByIdFow(sc, service);
                    break;
                case "3":
                    viewAllFlow(service);
                    break;
                case "4":
                    updateFlow(sc, service);
                    break;
                case "5":
                    deleteFlow(sc, service);
                    break;
                case "6":
                    System.out.println("Exiting saved changes...if any.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. try again.");
            }
            System.out.println();
        }
        sc.close();
    }

    private static void printMenu(){
        System.out.println("----Student Management System");
        System.out.println("1. Add Student");
        System.out.println("2. View Student by ID");
        System.out.println("3. View All Students");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Save & Exit");

        System.out.println("------------------------------------");

    }

    private static void addStudentFlow(Scanner sc, StudentService service){
        System.out.println("Add Student: ");
        System.out.print("ID: ");
        String id = sc.nextLine().trim();
        if(service.getStudentById(id) != null){
            System.out.println("A student with this Id already exists.");
            return;
        }
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        System.out.println("Age: ");
        int age = readInt(sc);
        System.out.println("Department: ");
        String dept = sc.nextLine().trim();
        System.out.println("phone: ");
        String phone = sc.nextLine().trim();
        System.out.println("email: ");
        String email = sc.nextLine().trim();

        Student s = new Student(id, name, age, dept, phone, email);
        boolean ok = service.addStudent(s);
        System.out.println(ok ? "Student added." : "Failed to add student.");
    }
    private static void viewAllFlow(StudentService service) {
        List<Student> all = service.getAllStudents();
        if (all.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("All students:");
            for (Student s : all) {
                System.out.println(s);
            }
        }
    }
    private static void viewByIdFow(Scanner sc, StudentService service){
        System.out.println("Enter Id: ");
        String id = sc.nextLine().trim();
        Student s = service.getStudentById(id);
        if(s == null) System.out.println("Not found");
        else System.out.println(s);
    }

    private static void updateFlow(Scanner sc, StudentService service) {
        System.out.print("Enter ID to update: ");
        String id = sc.nextLine().trim();
        Student existing = service.getStudentById(id);
        if (existing == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println("Leave field empty to keep current value.");

        System.out.print("Name [" + existing.getName() + "]: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) name = existing.getName();

        System.out.print("Age [" + existing.getAge() + "]: ");
        String ageStr = sc.nextLine().trim();
        int age = ageStr.isEmpty() ? existing.getAge() : Integer.parseInt(ageStr);

        System.out.print("Department [" + existing.getDepartment() + "]: ");
        String dept = sc.nextLine().trim();
        if (dept.isEmpty()) dept = existing.getDepartment();

        System.out.print("Phone [" + existing.getPhone() + "]: ");
        String phone = sc.nextLine().trim();
        if (phone.isEmpty()) phone = existing.getPhone();

        System.out.print("Email [" + existing.getEmail() + "]: ");
        String email = sc.nextLine().trim();
        if (email.isEmpty()) email = existing.getEmail();

        Student updated = new Student(id, name, age, dept, phone, email);
        boolean ok = service.updateStudent(id, updated);
        System.out.println(ok ? "Student updated." : "Failed to update.");
    }

    private static void deleteFlow(Scanner sc, StudentService service) {
        System.out.print("Enter ID to delete: ");
        String id = sc.nextLine().trim();
        boolean ok = service.deleteStudent(id);
        System.out.println(ok ? "Deleted." : "Not found.");
    }

    private static int readInt(Scanner sc) {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid integer: ");
            }
        }
    }


}
