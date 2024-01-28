package ru.hogwarts.scool.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.scool.model.Student;
import ru.hogwarts.scool.service.StudentService;

import java.util.Collection;
import java.util.Collections;


@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable long id) {

        Student student = studentService.readStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable Long id) {
        Student foundStudent = studentService.updateStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/age")
    public ResponseEntity<Collection<Student>> filterStudentAge(Integer age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.filterAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/age-between")
    public ResponseEntity<Collection<Student>> filterStudentAgeBetween(@RequestParam Integer ageMin, @RequestParam Integer ageMax) {
        if (ageMin > 0) {
            return ResponseEntity.ok(studentService.findByAgeBetween(ageMin, ageMax));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/average-age")
    public Double findByAverageAge() {
        return studentService.findByAverageAge();
    }

    @GetMapping("/stream-average-age")
    public Double streamAverageAge() {
        return studentService.streamAverageAge();
    }

    @GetMapping("/find-all")
    public Integer findAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/last-student")
    public Collection<Student> findByLastStudents() {
        return studentService.findByLastStudents();
    }

    @GetMapping("/name-start-A")
    public Collection<String> startLetterName() {
        return studentService.startLetterName();
    }

    @GetMapping("/print-parallel")
    public void printNames() {
        studentService.studentsPrintParallel();
    }

    @GetMapping("/print-synchronized")
    public void printNamesSynchronized() {
        studentService.studentsPrintSynchronized();
    }
}
