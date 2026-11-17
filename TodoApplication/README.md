# Todo List Management System

Java 17 + Spring Boot 3 + Spring Data JPA + MySQL

## Run
1. Install MySQL and update `spring.datasource.username/password` in `src/main/resources/application.properties`.
   (The database `todo_db` is created automatically.)
2. `mvn spring-boot:run`
3. API base URL: `http://localhost:8080/api/tasks`

## Endpoints
| Method | Endpoint | Purpose |
|---|---|---|
| POST | /api/tasks | Create task |
| GET | /api/tasks | Get all tasks |
| GET | /api/tasks?status=PENDING | Filter by status |
| GET | /api/tasks/{id} | Get task |
| PUT | /api/tasks/{id} | Update task |
| DELETE | /api/tasks/{id} | Delete task |
| PATCH | /api/tasks/{id}/complete | Mark completed |
| GET | /api/tasks/search?title=test | Search by title |

Status: PENDING, IN_PROGRESS, COMPLETED. Priority: LOW, MEDIUM, HIGH.

## Sample
```
curl -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" -d '{
  "title": "Learn Spring Boot",
  "description": "Complete REST API development",
  "status": "PENDING",
  "priority": "HIGH",
  "dueDate": "2026-11-20"
}'
```
