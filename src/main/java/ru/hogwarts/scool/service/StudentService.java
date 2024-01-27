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

    public void studentsPrintParallel() {
        logger.info("Был вызван метод studentsPrintParallel");
        long start = System.currentTimeMillis();
        List<Student> students = studentRepository.findAll();
        logger.info("главный поток метод studentsPrint");
        print(students.get(0));
        print(students.get(1));
        logger.info("второй поток метод studentsPrint");
        new Thread(() -> {
            print(students.get(2));
            print(students.get(3));
        }).start();
        logger.info("третий поток метод studentsPrint");
        new Thread(() -> {
            print(students.get(4));
            print(students.get(5));
        }).start();
        long finish = System.currentTimeMillis();
        logger.info("Время вычисления: " + (finish - start));

    }

    private void print(Student student) {
        System.out.println(Thread.currentThread() + " " + student);
    }

    public void studentsPrintSynchronized() {
        logger.info("Был вызван метод studentsPrintSynchronized");
        long start = System.currentTimeMillis();
        List<Student> students = studentRepository.findAll();
        logger.info("главный поток метод studentsPrintSynchronized");
        printSynchronized(students.get(0));
        printSynchronized(students.get(1));
        logger.info("второй поток метод studentsPrintSynchronized");
        new Thread(() -> {
            printSynchronized(students.get(2));
            printSynchronized(students.get(3));
        }).start();
        logger.info("третий поток метод studentsPrintSynchronized");
        new Thread(() -> {
            printSynchronized(students.get(4));
            printSynchronized(students.get(5));
        }).start();
        long finish = System.currentTimeMillis();
        logger.info("Время вычисления: " + (finish - start));
    }

    private synchronized void printSynchronized(Student student) {
        System.out.println(Thread.currentThread() + " " + student);
    }

}

