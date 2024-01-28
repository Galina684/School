package ru.hogwarts.scool.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.scool.model.Faculty;
import ru.hogwarts.scool.model.Student;
import ru.hogwarts.scool.repository.FacultyRepository;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacultyControllerTestRestTemplate {

    @LocalServerPort
    private int port;
    @Autowired
    private FacultyController facultyController;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private FacultyRepository facultyRepository;
    private final Faculty FACULTY = new Faculty(0, "New", "black");

    @BeforeEach
    void setUp() {
        facultyRepository.save(FACULTY);
    }


    @Test
    void contexLoads() {
        assertNotNull(facultyController);
        facultyRepository.delete(FACULTY);
    }


    @Test
    void getFacultyInfo() {
        assertThat(this.restTemplate.getForObject("/faculty/" + FACULTY.getId(), Faculty.class))
                .isEqualTo(FACULTY);
        facultyRepository.delete(FACULTY);

    }

    @Test
    void createFaculty() {

        assertThat(this.restTemplate.postForObject("/faculty", FACULTY, Faculty.class))
                .isNotNull();
        facultyRepository.delete(FACULTY);
    }

    @Test
    void updateFaculty() {
        Faculty f = new Faculty();
        f.setName("gggg");
        f.setColor("pink");
        facultyRepository.save(f);

        this.restTemplate.put("/faculty", f, Faculty.class);
        assertThat(this.restTemplate.getForObject("/faculty/" + f.getId(), Faculty.class))
                .isEqualTo(f);
        facultyRepository.delete(FACULTY);
        facultyRepository.delete(f);
    }

    @Test
    void deleteFaculty() {
        Faculty f = new Faculty();
        f.setName("gggg");
        f.setColor("pink");
        facultyRepository.save(f);
        this.restTemplate.postForObject("/faculty", f, Faculty.class);
        this.restTemplate.delete("/faculty/" + f.getId(), Faculty.class);
        assertThat(this.restTemplate.getForObject("/faculty/" + f.getId(), Faculty.class))
                .isEqualTo(new Faculty(0, null, null));
        facultyRepository.delete(FACULTY);
    }

    @Test
    void filterFacultyByColorOrName() {
        assertThat(this.restTemplate.getForObject("/faculty?color=black", Collection.class).size())
                .isEqualTo(1);
        facultyRepository.delete(FACULTY);
    }
}