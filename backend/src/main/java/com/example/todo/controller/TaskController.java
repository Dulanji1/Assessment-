package com.example.todo.controller;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository repo;

    public TaskController(TaskRepository repo){ this.repo = repo; }

    @GetMapping
    public List<Task> listLatest(){ return repo.findTop5ByCompletedFalseOrderByCreatedAtDesc(); }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task t){
        if(t.getTitle()==null || t.getTitle().isBlank()){
            return ResponseEntity.badRequest().build();
        }
        t.setCompleted(false);
        Task saved = repo.save(t);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/{id}/done")
    public ResponseEntity<Void> markDone(@PathVariable Long id){
        return repo.findById(id).map(task->{
            task.setCompleted(true);
            repo.save(task);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
