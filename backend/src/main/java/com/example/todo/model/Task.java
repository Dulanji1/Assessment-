package com.example.todo.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "task")
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String title;

    @Column(columnDefinition="text")
    private String description;

    @Column(nullable=false)
    private boolean completed = false;

    @Column(name = "created_at", nullable=false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    // getters and setters
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}
    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}
    public boolean isCompleted(){return completed;}
    public void setCompleted(boolean completed){this.completed = completed;}
    public OffsetDateTime getCreatedAt(){return createdAt;}
    public void setCreatedAt(OffsetDateTime createdAt){this.createdAt = createdAt;}
}
