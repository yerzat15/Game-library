# Game Library REST API (Spring Boot)

This project is a **Spring Boot REST API** for managing a simple **Game Library** with:
- Developers
- Genres
- Games (Digital / Physical)

Architecture:
**Controller → Service → Repository → PostgreSQL**

---

## Tech Stack
- Java 23
- Spring Boot 3.2.5
- Spring Web
- Spring Data JPA (Hibernate)
- PostgreSQL
- Maven

---

## Project Structure (packages)
- `org.example.controller` — REST endpoints
- `org.example.service` — business logic
- `org.example.repository` — JPA repositories
- `org.example.model` — JPA entities
- `org.example.dto` — request/response DTOs
- `org.example.exception` — custom exceptions + GlobalExceptionHandler
- `org.example.patterns` — patterns (Builder / Factory / Singleton)
- `org.example.utils` — utility classes

---
#How to Run
##Option A: Run from IntelliJ

Open org.example.EndtermApiApplication

Click ▶ Run

##Option B: Run with Maven

From the project folder (where pom.xml is located):

mvn spring-boot:run


If mvn is not in PATH:

& "C:\Users\User\Downloads\apache-maven-3.9.12-bin\apache-maven-3.9.12\bin\mvn.cmd" spring-boot:run


Server runs on:

http://localhost:8080
