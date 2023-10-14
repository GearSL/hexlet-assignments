package exercise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// END
@SpringBootTest
@AutoConfigureMockMvc
// BEGIN
class TaskControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    Faker faker;
    @Autowired
    ObjectMapper om;

    @Test
    public void indexTest() throws Exception {
        Task task1 = taskRepository.save(createTaskObject());
        Task task2 = taskRepository.save(createTaskObject());

        MockHttpServletResponse response = mockMvc.perform(
                get("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn().getResponse();
        String body = response.getContentAsString();

        assertThatJson(body).isArray()
                .element(0)
                .isObject()
                .containsEntry("id", task1.getId())
                .containsEntry("title", task1.getTitle())
                .containsEntry("description", task1.getDescription());

        assertThatJson(body).isArray()
                .element(1)
                .isObject()
                .containsEntry("id", task2.getId())
                .containsEntry("title", task2.getTitle())
                .containsEntry("description", task2.getDescription());
    }

    @Test
    public void createTest() throws Exception {
        Task task = createTaskObject();

        MockHttpServletResponse response = mockMvc.perform(
                post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(task))
        ).andExpect(status().isCreated()).andReturn().getResponse();
        String body = response.getContentAsString();

        assertThatJson(body).isObject()
                .containsEntry("title", task.getTitle())
                .containsEntry("description", task.getDescription());
    }

    @Test
    public void updateTest() throws Exception {
        Task task = createTaskObject();
        Task updateTask = createTaskObject();

        long savedTaskId = taskRepository.save(task).getId();

        MockHttpServletResponse response = mockMvc.perform(
                put("/tasks/{id}", savedTaskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(updateTask))
        ).andExpect(status().isOk()).andReturn().getResponse();

        Task taskAfterUpdate = taskRepository.findById(savedTaskId).orElseThrow();

        assertThat(taskAfterUpdate.getTitle()).isEqualTo(updateTask.getTitle());
        assertThat(taskAfterUpdate.getDescription()).isEqualTo(updateTask.getDescription());
    }

    @Test
    public void deleteTest() throws Exception {
        Task task = createTaskObject();
        long savedTaskId = taskRepository.save(task).getId();

        MockHttpServletResponse response = mockMvc.perform(
                delete("/tasks/{id}", savedTaskId)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn().getResponse();

        assertThat(taskRepository.existsById(savedTaskId)).isFalse();
    }

    public Task createTaskObject() {

        return Instancio.of(Task.class)
                .ignore(Select.field("id"))
                .supply(Select.field("title"), () -> faker.lorem().word())
                .supply(Select.field("description"), () -> faker.lorem().paragraph())
                .create();
    }
}
// END