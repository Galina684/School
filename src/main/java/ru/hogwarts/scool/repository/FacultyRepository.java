package ru.hogwarts.scool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.scool.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}
