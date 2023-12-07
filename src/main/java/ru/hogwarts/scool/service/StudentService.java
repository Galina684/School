package ru.hogwarts.scool.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.scool.model.Student;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private long counterStudents = 0;

    public Student createStudent (Student student){
        student.setId(++counterStudents);
        students.put(counterStudents, student);
        return student;
    }
    public Student readStudent(Long id){
        return students.get(id);
    }

    public Student updateStudent(Student student){
        if(students.containsKey(student.getId())){
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudent(Long id){
        return students.remove(id);
    }

}
