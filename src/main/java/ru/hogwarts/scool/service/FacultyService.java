package ru.hogwarts.scool.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.scool.model.Faculty;
import ru.hogwarts.scool.repository.FacultyRepository;

import java.util.*;

import org.slf4j.Logger;


@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty createFaculty(Faculty faculty) {
        logger.info("Был вызван метод createFaculty");
        return facultyRepository.save(faculty);
    }

    public Faculty readFaculty(long id) {
        logger.info("Был вызван метод readFaculty");
        return facultyRepository.findById(id).get();
    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Был вызван метод updateFaculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Был вызван метод deleteFaculty");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByColorOrNameIgnoreCase(String color, String name) {
        logger.info("Был вызван метод findByColorOrNameIgnoreCase");
        return facultyRepository.findByColorOrNameIgnoreCase(color, name);
    }

    public String facultyLongestName() {
        logger.info("Был вызван метод facultyWithLongestName");
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElse(null);

    }
}
