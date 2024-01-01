package ru.hogwarts.scool;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.scool.controller.StudentController;
import ru.hogwarts.scool.model.Student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ScoolApplicationTests {
//	@LocalServerPort
//private int port;
//	@Autowired
//	private StudentController studentController;
//
//	@Autowired
//	private TestRestTemplate restTemplate;
//	@Test
//	void contextLoads() {
//		assertNotNull(studentController);
//	}
//	@Test
//	void createStudent() throws Exception {
//		Student student = new Student(11, "Фывфвфв", 20 );
//
//		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/students", student, String.class))
//				.isNotNull();
//	}

}
