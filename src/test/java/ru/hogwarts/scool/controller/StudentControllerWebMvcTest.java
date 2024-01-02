package ru.hogwarts.scool.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.scool.model.Student;
import ru.hogwarts.scool.repository.AvatarRepository;
import ru.hogwarts.scool.repository.StudentRepository;
import ru.hogwarts.scool.service.AvatarService;
import ru.hogwarts.scool.service.StudentService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StudentController.class)
class StudentControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private AvatarRepository avatarRepository;

    @SpyBean
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private long id = 1;
    private String name = "New student";
    private int age = 15;

    @Test
    public void createStudentTest() throws Exception {

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);
        Student student = new Student(id, name, age);

        when(studentRepository.save(any())).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    void getStudentInfo() throws Exception {

        Student student = new Student(id, name, age);
        when(studentRepository.findById(id)).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }


    @Test
    void updateStudent() throws Exception {
        int updAge = 16;
        JSONObject studentObject = new JSONObject();
        studentObject.put("id", id);
        studentObject.put("name", name);
        studentObject.put("age", updAge);

        Student student = new Student(id, name, age);
        Student updStudent = new Student(id, name, updAge);

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(updStudent);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(updAge));

    }

    @Test
    void deleteStudent() throws Exception {

        Student student = new Student(id, name, age);
        when(studentRepository.findById(any())).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void filterStudentAge() throws Exception {
        long id2 = 2;
        String name2 = "Student2";
        List<Student> studentList = List.of(
                new Student(id, name, age),
                new Student(id2, name2, age)
        );

        when(studentRepository.findByAge(anyInt())).thenReturn(studentList);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/age?age=15"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        [
                        {"id":1,"name":"New student","age":15},
                        {"id":2,"name":"Student2","age":15}
                        ]"""));
    }

    @Test
    void filterStudentAgeBetween() throws Exception {
        long id2 = 2;
        String name2 = "Student2";
        int age2 = 20;
        List<Student> studentList = List.of(
                new Student(id, name, age),
                new Student(id2, name2, age2)
        );

        when(studentRepository.findByAgeBetween(anyInt(), anyInt())).thenReturn(studentList);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/age-between?ageMin=10&ageMax=20"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        [
                        {"id":1,"name":"New student","age":15},
                        {"id":2,"name":"Student2","age":20}
                        ]"""));
    }
}