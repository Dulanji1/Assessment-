# Simple To-Do (Spring Boot + MySQL + Simple HTML)

## What
A minimal to-do app implementing the assessment:
- Create tasks (title + description)
- List latest 5 incomplete tasks
- Mark tasks as done (they disappear)

## Tech
- Java 17, Spring Boot 3
- MySQL (container)
- Simple static HTML UI served from Spring Boot `resources/static`
- Docker + docker-compose provided

## Run (with Docker)
Make sure Docker and docker-compose are installed.

From project root (where docker-compose.yml is located):

```bash
docker-compose up --build
```

- Backend will be available at `http://localhost:8080`
- UI at `http://localhost:8080/`

MySQL:
- Host: `db` (container network)
- Port mapped: `3306` on host
- Database: `todo_db`
- Root password: `password`

## Build locally (without Docker)
You need Java 17 and Maven.

```bash
mvn package
java -jar target/todo-0.0.1-SNAPSHOT.jar
```

## Notes
- Database schema is auto-created by Hibernate (spring.jpa.hibernate.ddl-auto=update)
- For production, change password and `ddl-auto` strategy accordingly.
