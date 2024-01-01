package ru.hogwarts.scool.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.scool.model.Faculty;
import ru.hogwarts.scool.repository.FacultyRepository;
import ru.hogwarts.scool.service.FacultyService;



import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = FacultyController.class)
class FacultyControllerWebMvcTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @SpyBean
    private FacultyService facultyService;
    @InjectMocks
    private FacultyController facultyController;
@Test
    public void saveFacultyTest() throws JSONException {
    String name = "Muggles";
    String color = "black";
    JSONObject facultyObject = new JSONObject();
    facultyObject.put("name", name);
    facultyObject.put("color",color);

    Faculty faculty = new Faculty();
    faculty.setName(name);
    faculty.setColor(color);

//    when(FacultyRepository.save(any())).thenReturn(faculty);

    }


    @Test
    void getFacultyInfo() {
    }

    @Test
    void createFaculty() {
    }

    @Test
    void updateFaculty() {
    }

    @Test
    void deleteFaculty() {
    }

    @Test
    void filterFacultyByColorOrName() {
    }
}