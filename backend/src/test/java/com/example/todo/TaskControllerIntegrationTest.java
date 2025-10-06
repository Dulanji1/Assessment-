package com.example.todo;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TaskRepository repo;

    @Test
    void createAndList() throws Exception {
        repo.deleteAll();
        mvc.perform(post("/api/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{"title":"ITask","description":"desc"}"))
            .andExpect(status().isOk());

        mvc.perform(get("/api/tasks"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value("ITask"));
    }
}
