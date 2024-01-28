package ru.hogwarts.scool.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hogwarts.scool.model.Faculty;
import ru.hogwarts.scool.repository.FacultyRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

class FacultyServiceTest {

    FacultyService out;

    FacultyRepository facultyRepository;

    @BeforeEach
    void setUp() {
        FacultyService out = new FacultyService(facultyRepository);
    }

    @Test
    void createFaculty() {
      Faculty expected = new Faculty(3L, "Ravenclaw", "blue");
      facultyRepository.save(expected);

      assertEquals(expected, out.createFaculty(new Faculty(3L,"Ravenclaw","blue" )));

    }

    @Test
    void readFaculty() {

    }

    @Test
    void updateFaculty() {
    }

    @Test
    void deleteFaculty() {

    }
}