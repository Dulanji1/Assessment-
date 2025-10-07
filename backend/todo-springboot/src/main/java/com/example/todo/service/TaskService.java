package com.example.todo.service;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public Task create(Task t) {
        t.setCompleted(false);
        return repo.save(t);
    }

    public List<Task> latestFive() {
        return repo.findTop5ByCompletedFalseOrderByCreatedAtDesc();
    }

    public Optional<Task> markDone(Long id) {
        Optional<Task> ot = repo.findById(id);
        ot.ifPresent(t -> {
            t.setCompleted(true);
            repo.save(t);
        });
        return ot;
    }
}
