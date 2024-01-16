package ru.hogwarts.scool.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.scool.model.Student;
import ru.hogwarts.scool.repository.StudentRepository;

import java.util.*;


@Service
public class StudentService {
    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;

    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);

    }

    public Student readStudent(long id) {
        return studentRepository.findById(id).get();
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> filterAge(int age) {

        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Double findByAverageAge() {
        return studentRepository.findByAverageAge();
    }

    public Integer findAllStudents() {
        return studentRepository.findAllStudents();
    }

    public Collection<Student> findByLastStudents() {
        return studentRepository.findByLastStudents();
    }
}

