package ru.hogwarts.scool.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.scool.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAge(int age);

    Collection<Student> findByAgeBetween(int min, int max);

    @Query(value = "select count(*) from student", nativeQuery = true)
    Integer findAllStudents();

    @Query(value = "select  avg(age) as avgAge from student", nativeQuery = true)
    Double findByAverageAge();

    @Query(value = "select * from student order by id desc limit 5", nativeQuery = true)
    List<Student> findByLastStudents();
}
