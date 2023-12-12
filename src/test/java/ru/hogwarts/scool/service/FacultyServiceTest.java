package ru.hogwarts.scool.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.scool.model.Faculty;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

class FacultyServiceTest {
    FacultyService out;
    private final Faculty griffindor = new Faculty(1L, "Griffindor", "red");
    private final Faculty hufflepuff = new Faculty(2L, "Hufflepuff", "yellow");
//    private final Faculty ravenclaw = new Faculty(3L, "Ravenclaw", "blue");
//    private final Faculty slytherin = new Faculty(4L, "Slytherin", "green");

    @BeforeEach
    void setUp() {
        out = new FacultyService();
        out.createFaculty(griffindor);
        out.createFaculty(hufflepuff);
    }

    @Test
    void createFaculty() {
Faculty expected = new Faculty(3L, "Ravenclaw", "blue");

        assertThat(out.createFaculty(new Faculty(3L,"Ravenclaw","blue" )))
        .isEqualTo(expected);

    }

    @Test
    void readFaculty() {
        Faculty expected = new Faculty(3L, "Ravenclaw", "blue");
  //      long counter = 0;
//        expected.setId(counter++);
//        assertThat(out.readFaculty(3L))
//               .isEqualTo(expected);

       // assertEquals(out.readFaculty(3L),3L);
    }

    @Test
    void updateFaculty() {
    }

    @Test
    void deleteFaculty() {

    }
}