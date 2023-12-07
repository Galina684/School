package ru.hogwarts.scool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.scool.model.Student;
import ru.hogwarts.scool.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {

        Student student = studentService.readStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }
    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        Student foundStudent = studentService.updateStudent(student);
        if(foundStudent==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundStudent);
    }
    @DeleteMapping("{id}")
    public Student deleteStudent(@PathVariable Long id){
        return studentService.deleteStudent(id);
    }
}
