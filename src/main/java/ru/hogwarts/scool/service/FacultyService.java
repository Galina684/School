package ru.hogwarts.scool.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.scool.model.Faculty;
import ru.hogwarts.scool.repository.FacultyRepository;

import java.util.*;

@Service
public class FacultyService {
 private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty readFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty updateFaculty(Faculty faculty) {
      return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
         facultyRepository.deleteById(id);
    }

    public Collection<Faculty> filterColor(String color) {
        return facultyRepository.findByColor(color);
    }


}
