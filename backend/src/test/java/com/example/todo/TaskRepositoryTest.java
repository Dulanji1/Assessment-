package com.example.todo;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository repo;

    @Test
    void saveAndQuery() {
        Task t = new Task();
        t.setTitle("Test");
        t.setDescription("desc");
        repo.save(t);
        List<com.example.todo.model.Task> tasks = repo.findTop5ByCompletedFalseOrderByCreatedAtDesc();
        assertThat(tasks).isNotEmpty();
        assertThat(tasks.get(0).getTitle()).isEqualTo("Test");
    }
}
