package ru.hogwarts.scool.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.scool.repository.AvatarRepository;
import ru.hogwarts.scool.repository.StudentRepository;
import ru.hogwarts.scool.service.AvatarService;
import ru.hogwarts.scool.service.StudentService;

@WebMvcTest
class StudentControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentRepository studentRepository;
    // @MockBean
    //private AvatarRepository avatarRepository;
    @SpyBean
    private StudentService studentService;
    //@SpyBean
    //private AvatarService avatarService;
    @InjectMocks
    private StudentController studentController;

    @Test
    public void saveStudentTest() {
        JSONObject studentObject = new JSONObject();
    }

    @Test
    void getStudentInfo() {
    }

    @Test
    void createStudent() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void filterStudentAge() {
    }

    @Test
    void filterStudentAgeBetween() {
    }
}