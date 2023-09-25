package exercise;

import exercise.model.Person;
import exercise.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import org.springframework.http.MediaType;

import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@AutoConfigureMockMvc

// BEGIN
@Testcontainers
@Transactional
// END
public class AppTest {

    @Autowired
    private MockMvc mockMvc;

    // BEGIN
    @Autowired
    PersonRepository personRepository;

    @Container
    private static final PostgreSQLContainer<?> dataBase = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("test_db")
            .withUsername("sa")
            .withPassword("sa")
            .withInitScript("init.sql");

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", dataBase::getJdbcUrl);
        registry.add("spring.datasource.username", dataBase::getUsername);
        registry.add("spring.datasource.password", dataBase::getPassword);
    }

    @Test
    void getPeopleList() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                get("/people")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).contains("John");
        assertThat(response.getContentAsString()).contains("Jack");
        assertThat(response.getContentAsString()).contains("Jassica");
        assertThat(response.getContentAsString()).contains("Robert");
    }

    @Test
    void getPerson() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                //Static ID because we don't have some utils for get random person and it's test project
                get("/people/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).contains("John");
        assertThat(response.getContentAsString()).contains("Smith");
    }

    @Test
    void updatePerson() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                patch("/people/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"Jackson\", \"lastName\": \"Bind\"}")
        ).andReturn().getResponse();

        Person person = personRepository.findById(1);
        assertThat(person.getFirstName()).isEqualTo("Jackson");
        assertThat(person.getLastName()).isEqualTo("Bind");
    }

    @Test
    void deletePerson() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                delete("/people/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(personRepository.existsById(1L)).isFalse();
    }
    // END

    @Test
    void testCreatePerson() throws Exception {
        MockHttpServletResponse responsePost = mockMvc
            .perform(
                post("/people")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"firstName\": \"Jackson\", \"lastName\": \"Bind\"}")
            )
            .andReturn()
            .getResponse();

        assertThat(responsePost.getStatus()).isEqualTo(200);

        MockHttpServletResponse response = mockMvc
            .perform(get("/people"))
            .andReturn()
            .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Jackson", "Bind");
    }
}
