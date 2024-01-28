package ru.hogwarts.scool.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.scool.model.Student;
import ru.hogwarts.scool.repository.StudentRepository;

import java.util.Collection;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTestRestTemplate {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;
    @Autowired
    private StudentRepository studentRepository;


    @Autowired
    private TestRestTemplate restTemplate;

    private final Student STUDENT = new Student(0, "STUDENT", 15);

    @BeforeEach
    public void StudentSetUp() {
        studentRepository.save(STUDENT);
    }

    @Test
    void contexLoads() {
        org.assertj.core.api.Assertions.assertThat(studentController).isNotNull();
        studentRepository.delete(STUDENT);
    }

    @Test
    void getStudentInfo() throws Exception {

        org.assertj.core.api.Assertions.assertThat(this.restTemplate.getForObject("/student/" + STUDENT.getId(), Student.class))
                .isEqualTo(STUDENT);
        studentRepository.delete(STUDENT);

    }

    @Test
    void createStudent() throws Exception {
        Student s = new Student();
        s.setName("Vasya");
        s.setAge(10);
        studentRepository.save(s);
        org.assertj.core.api.Assertions.assertThat(this.restTemplate.postForObject("/student/", s, Student.class))
                .isNotNull();
        studentRepository.delete(STUDENT);
        studentRepository.delete(s);
    }

    @Test
    void updateStudent() {
        this.restTemplate.put("/student", STUDENT, Student.class);
        assertThat(this.restTemplate.getForObject("/student/" + STUDENT.getId(), Student.class))
                .isEqualTo(STUDENT);
        studentRepository.delete(STUDENT);
    }

    @Test
    void deleteStudent() {
        this.restTemplate.postForObject("/student", STUDENT, Student.class);
        this.restTemplate.delete("/student/" + STUDENT.getId(), Student.class);
        assertThat(this.restTemplate.getForObject("/student/" + STUDENT.getId(), Student.class))
                .isEqualTo(new Student(0, null, 0));

    }

    @Test
    void filterStudentAge() {
        Student s = new Student();
        s.setName("Vasya");
        s.setAge(8);
        studentRepository.save(s);
        org.assertj.core.api.Assertions.assertThat(this.restTemplate.getForObject("/student/age?age=8", Collection.class).size())
                .isEqualTo(1);
        studentRepository.delete(STUDENT);
        studentRepository.delete(s);
    }

    @Test
    void filterStudentAgeBetween() {
        Student s = new Student();
        s.setName("Vasya");
        s.setAge(7);
        studentRepository.save(s);
        int size = this.restTemplate.getForObject("/student/age-between?ageMin=7&ageMax=9", Collection.class).size();
        assertThat(size).isEqualTo(1);
        studentRepository.delete(STUDENT);
        studentRepository.delete(s);
    }
}