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

## Database (PostgreSQL)

### 1) Create database
Create a database named `gamelibrary` in PostgreSQL:

```sql
CREATE DATABASE gamelibrary;
2) Configure application.properties
File: src/main/resources/application.properties

Example:

spring.datasource.url=jdbc:postgresql://localhost:5432/gamelibrary
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
How to Run
Option A: Run from IntelliJ
Open org.example.EndtermApiApplication

Click ▶ Run

Option B: Run with Maven
From the project folder (where pom.xml is located):

mvn spring-boot:run
If mvn is not in PATH:

& "C:\Users\User\Downloads\apache-maven-3.9.12-bin\apache-maven-3.9.12\bin\mvn.cmd" spring-boot:run
Server runs on:

http://localhost:8080
API Endpoints
Developers
POST /api/developers

GET /api/developers

GET /api/developers/{id}

PUT /api/developers/{id}

DELETE /api/developers/{id}

Genres
POST /api/genres

GET /api/genres

GET /api/genres/{id}

PUT /api/genres/{id}

DELETE /api/genres/{id}

Games
POST /api/games

GET /api/games (sorted by title A→Z)

GET /api/games/{id}

PUT /api/games/{id}

DELETE /api/games/{id}

Extra format:

GET /api/games/lines — newline-delimited JSON (one object per line)

Example Requests
1) Create Developer
POST /api/developers

{
  "name": "Valve",
  "country": "USA",
  "foundedYear": 1996
}
2) Create Genre
POST /api/genres

{
  "name": "Action",
  "description": "Fast-paced action games"
}
3) Create Digital Game
POST /api/games

{
  "title": "Half-Life",
  "price": 9.99,
  "releaseYear": 1998,
  "developerId": 1,
  "genreId": 1,
  "type": "DIGITAL",
  "platform": "PC",
  "downloadSizeGb": 2.5
}
4) Create Physical Game
POST /api/games

{
  "title": "GTA V",
  "price": 19.99,
  "releaseYear": 2013,
  "developerId": 2,
  "genreId": 1,
  "type": "PHYSICAL",
  "mediaType": "Blu-ray Disc",
  "stock": 15
}
5) Update Game
PUT /api/games/1

{
  "title": "Half-Life Updated",
  "price": 7.99,
  "releaseYear": 1998,
  "developerId": 1,
  "genreId": 1,
  "type": "DIGITAL",
  "platform": "PC",
  "downloadSizeGb": 2.7
}
6) Delete Game
DELETE /api/games/1
```
