package ru.hogwarts.scool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.scool.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAge(int age);
}
