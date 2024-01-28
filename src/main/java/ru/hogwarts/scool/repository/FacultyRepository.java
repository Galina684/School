package ru.hogwarts.scool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.scool.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Collection<Faculty> findByColorOrNameIgnoreCase(String color, String name);


}
