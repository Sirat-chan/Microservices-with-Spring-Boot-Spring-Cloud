package com.formationudemy.springboot.controller;

import com.formationudemy.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    // http:localhost:8080/student

    @GetMapping("student")
    public ResponseEntity <Student> getStudent(){
        Student student = new Student(
                1,
                "test",
                "test"
        );
        return ResponseEntity.ok()
                .header("custom-header", "ramesh")
                .body(student);
    }

    // http://localhost:8080/students
    @GetMapping("students")
    public ResponseEntity <List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"test2","testy2"));
        students.add(new Student(1, "test3", "testy3"));
        students.add(new Student(3, "test4", "testy4"));
        return ResponseEntity.ok(students);
    }

    // Springboot REST API with path variable
    // {id} - URI template variable
    // http://localhost:8080/students/1

    @GetMapping("{id}")
    public ResponseEntity <Student> studentPathVariable(@PathVariable("id") int studentId){
        Student student = new Student(studentId, "test", "test");
        return ResponseEntity.ok(student);
    }

    // Spring boot REST API with Request Param
    // http://localhost:8080/students/query?id=1&firstName=test&lastName=test

    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // Spring boot REST API that handles HTTP POST Request
    // @PostMapping and @RequestBody

    @PostMapping("create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity <Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // Spring boot REST API that handles HTTP PUT request
    @PutMapping("{id}/update")
    public ResponseEntity <Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }
    // Spring boot REST API that handles HTTP DELETE Request

    @DeleteMapping("{id}/delete")
    public ResponseEntity <String> deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok( "Student deleted successfully!");
    }
}

