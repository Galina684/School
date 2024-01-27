package ru.hogwarts.scool.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.scool.model.Student;
import ru.hogwarts.scool.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class StudentService {
    private final StudentRepository studentRepository;

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;

    }

    public Student createStudent(Student student) {
        logger.info("Был вызван метод createStudent");
        return studentRepository.save(student);

    }

    public Student readStudent(long id) {
        logger.info("Был вызван метод readStudent");
        return studentRepository.findById(id).get();
    }

    public Student updateStudent(Student student) {
        logger.info("Был вызван метод updateStudent");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Был вызван метод deleteStudent");
        studentRepository.deleteById(id);
    }

    public Collection<Student> filterAge(int age) {
        logger.info("Был вызван метод filterAge");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Был вызван метод findByAgeBetween");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Double findByAverageAge() {
        logger.info("Был вызван метод findByAverageAge");
        return studentRepository.findByAverageAge();
    }

    public Double streamAverageAge() {
        logger.info("Был вызван метод streamAverageAge");
        return studentRepository.findAll().stream()
                .mapToDouble(i -> (double) i.getAge())
                .average()
                .orElseThrow(() -> new RuntimeException("Ошибка вычисления среднего возраста"));
    }

    public Integer findAllStudents() {
        logger.info("Был вызван метод findAllStudents");
        return studentRepository.findAllStudents();
    }

    public Collection<Student> findByLastStudents() {
        logger.info("Был вызван метод findByLastStudents");
        return studentRepository.findByLastStudents();
    }

    public Collection<String> startLetterName() {
        logger.info("Был вызван метод startLetterName");
        return studentRepository.findAll().parallelStream()
                .map(Student::getName)
                .filter(name -> name.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());

    }
}

