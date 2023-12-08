package ru.hogwarts.scool.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.scool.model.Faculty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacultyService {
    private final Map<Long, Faculty> facultys = new HashMap<>();
    private long counterFacultys = 0;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++counterFacultys);
        facultys.put(counterFacultys, faculty);
        return faculty;
    }

    public Faculty readFaculty(Long id) {
        return facultys.get(id);
    }

    public Faculty updateFaculty(Faculty faculty) {
        if (facultys.containsKey(faculty.getId())) {
            facultys.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFaculty(Long id) {
        return facultys.remove(id);
    }

    public List<Faculty> filterGolor(String color) {
        List<Faculty> facultyList = new ArrayList<>();
        for (Faculty faculty : facultys.values()) {
            if (faculty.getColor().equals(color)) {
                facultyList.add(faculty);
            }
        }
        return facultyList;
    }


}
