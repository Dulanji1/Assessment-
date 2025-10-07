package com.example.todo.controller;

import com.example.todo.model.Task;
import com.example.todo.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin
public class TaskController {
    private final TaskService svc;

    public TaskController(TaskService svc) { this.svc = svc; }

    @GetMapping
    public List<Task> list() {
        return svc.latestFive();
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task t) {
        Task created = svc.create(t);
        return ResponseEntity.created(URI.create("/api/tasks/" + created.getId())).body(created);
    }

    @PostMapping("/{id}/done")
    public ResponseEntity<Object> done(@PathVariable Long id) {
        var opt = svc.markDone(id);
        return opt.map(t -> ResponseEntity.noContent().build())
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
