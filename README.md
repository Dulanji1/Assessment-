# ToDo Task App - Fullstack Assessment

This repository contains a simple ToDo application with:
- **Postgres** database
- **Spring Boot** (Java) backend
- **Static frontend** (HTML/CSS/Vanilla JS) served by nginx
- Docker Compose to run all services

## Features
- Create tasks (title + description)
- Show only most recent 5 incomplete tasks
- Mark task as Done (completed) — completed tasks are hidden
- All components runnable with Docker Compose

## How to run (assumes Docker & docker-compose installed)
1. Clone repository
2. From repository root run:
```bash
docker-compose up --build
```
3. Frontend available at: http://localhost:3000  
   Backend API at: http://localhost:8080/api/tasks

## API Endpoints
- `GET /api/tasks` — returns latest 5 incomplete tasks (most recent first)
- `POST /api/tasks` — create a task `{ "title": "...", "description": "..." }`
- `POST /api/tasks/{id}/done` — mark task as completed

## Project layout
- `backend/` — Spring Boot app (Maven)
- `frontend/` — static HTML/CSS/JS + nginx Dockerfile
- `docker-compose.yml` — orchestrates services
- `design.doc` — design document (Word/RTF compatible)
- `todo-assessment.zip` — packaged project (generated)

## Notes for evaluator
- Backend includes unit tests (JUnit) and an integration test scaffold.
- Database schema: `task` table with id, title, description, completed, created_at.
- The backend uses Spring Data JPA and PostgreSQL driver.

Enjoy! — Completed deliverable for the assessment.
