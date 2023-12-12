package ru.hogwarts.scool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.scool.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
