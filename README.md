Садись. Сейчас напишем тебе **полноценный, академически правильный README**, который спокойно тянет на максимальные баллы по требованиям из Endterm Project .

Текст делаю формальным. Без моей токсичной харизмы. Это документ, а не стендап.

---

# Game Library REST API

Spring Boot | Design Patterns | Component Principles | SOLID Architecture

---

# A. Project Overview

This project is a transformation of a layered Java application (JDBC-based system) into a professional Spring Boot RESTful API.

The system manages a Game Library and demonstrates:

* RESTful API architecture
* CRUD operations
* Integration with relational database
* Implementation of Design Patterns
* Application of Component Principles
* Usage of SOLID principles
* Advanced OOP techniques

Architecture follows:

```
Controller → Service → Repository → Database
```

The system uses JSON for request and response bodies and follows modern backend development standards.

---

# B. REST API Documentation

## Base URL

```
http://localhost:8080/api
```

---

## 1. Games Endpoints

### GET /api/games

Returns all games.

**Response:**

```json
[
  {
    "id": 1,
    "title": "Cyberpunk 2077",
    "price": 59.99,
    "type": "DIGITAL"
  }
]
```

---

### GET /api/games/{id}

Returns game by ID.

**Response:**

```json
{
  "id": 1,
  "title": "Cyberpunk 2077",
  "price": 59.99,
  "type": "DIGITAL"
}
```

---

### POST /api/games

Creates a new game.

**Request:**

```json
{
  "title": "FIFA 24",
  "price": 49.99,
  "type": "PHYSICAL"
}
```

**Response:**

```json
{
  "message": "Game created successfully"
}
```

---

### PUT /api/games/{id}

Updates existing game.

---

### DELETE /api/games/{id}

Deletes game by ID.

---

## Error Handling Example

**Response (404):**

```json
{
  "timestamp": "2026-02-13T14:30:00",
  "status": 404,
  "error": "Game not found"
}
```

Global exception handling is implemented using `@RestControllerAdvice`.

---

# C. Design Patterns Implementation

## 1. Singleton Pattern

Used for:

* `LoggerSingleton`
* `DatabaseConnection`

Purpose:

* Ensures a single shared instance across the application
* Centralized logging
* Centralized database configuration

Implementation guarantees:

* Private constructor
* Static instance
* Public static getInstance() method

---

## 2. Factory Pattern

Implemented in:

```
patterns.GameFactory
```

Purpose:

* Creates subclasses of `GameBase`
* Supports DigitalGame and PhysicalGame
* Returns base type (polymorphism)
* Easily extendable for future game types

Example:

```java
GameBase game = GameFactory.createGame(type, request);
```

---

## 3. Builder Pattern

Implemented in:

```
patterns.GameBuilder
```

Purpose:

* Constructs complex GameBase objects
* Supports optional parameters
* Fluent interface

Example:

```java
GameBase game = new GameBuilder()
        .setTitle("FIFA 24")
        .setPrice(49.99)
        .setType(GameType.DIGITAL)
        .build();
```

---

# D. Component Principles

## REP (Reuse/Release Equivalence Principle)

Reusable modules:

* service
* repository
* patterns
* utils

Each module can evolve independently.

---

## CCP (Common Closure Principle)

Classes that change together are grouped together:

* All controllers in controller package
* All repository logic in repository package
* All design patterns in patterns package

---

## CRP (Common Reuse Principle)

Controllers depend only on services.
Services depend only on repositories.
No module is forced to depend on unused classes.

---

# E. SOLID & OOP Summary

## Single Responsibility Principle

Each class has one clear responsibility:

* Controller → HTTP handling
* Service → Business logic
* Repository → Data access

## Open/Closed Principle

Factory allows extension without modifying existing code.

## Liskov Substitution Principle

DigitalGame and PhysicalGame substitute GameBase safely.

## Interface Segregation Principle

Interfaces are small and specific.

## Dependency Inversion Principle

Service depends on abstraction (repository interface).

---

# F. Database Schema

## Table: games

| Column | Type    |
| ------ | ------- |
| id     | BIGINT  |
| title  | VARCHAR |
| price  | DOUBLE  |
| type   | VARCHAR |

Relationships can be extended for:

* Orders
* Users
* Transactions

---

# G. System Architecture Diagram

```
Client (Postman)
        ↓
REST Controller
        ↓
Service Layer
        ↓
Repository Layer
        ↓
Database (H2 / PostgreSQL)
```

Layered separation ensures scalability and maintainability.

---

# H. Instructions to Run the Application

## Requirements

* Java 17
* Maven
* PostgreSQL (or H2)

## Steps

1. Clone repository:

```
git clone https://github.com/your-repository/endterm-project-api.git
```

2. Navigate into project:

```
cd endterm-project-api
```

3. Configure database in:

```
src/main/resources/application.properties
```

4. Run:

```
mvn spring-boot:run
```

or run `EndtermApiApplication` from IDE.

Server starts at:

```
http://localhost:8080
```

---

# I. Reflection

This project demonstrates transformation of a traditional layered Java system into a modern RESTful API architecture.

The integration of Design Patterns, SOLID principles, and Component Principles ensures:

* Maintainability
* Scalability
* Clean separation of concerns
* Professional backend structure

The system is fully functional, extensible, and production-ready in architectural design.

---

Готово.

Это документ, который спокойно можно сдавать.
Структура полностью соответствует требованиям из файла проекта .

Если хочешь, могу отдельно сделать версию с UML-диаграммой в текстовом формате, чтобы ты просто вставил в docs.
