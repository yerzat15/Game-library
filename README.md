# Game Library REST API (Spring Boot)

The project was transformed from CLI/JDBC into a **Spring Boot REST API** with the following architecture:

* Controller → Service → Repository → Database
* JSON requests/responses
* Global exception handling
* CRUD endpoints

## Implemented Patterns

* **Singleton**: `patterns.LoggerSingleton`, `utils.DatabaseConnection`
* **Factory**: `patterns.GameFactory` (creates `DigitalGame` or `PhysicalGame`)
* **Builder**: `patterns.GameBuilder` (builds a complex `GameBase` object from `GameRequest`)

## Component Principles

* **REP**: reusable modules (service/repository/patterns/utils)
* **CCP**: classes that change together are grouped into packages
* **CRP**: controllers depend only on service, service depends on repository

## Technologies

* Java 17
* Spring Boot 3
* Spring Web
* Spring Data JPA
* H2 (in-memory, PostgreSQL mode)
* Jakarta Validation

## Structure

```text
src/main/java/
├── controller/
├── service/
├── repository/
├── model/
├── dto/
├── exception/
├── patterns/
├── utils/
├── config/
├── Application.java
└── Main.java
```

## Run

```bash
mvn spring-boot:run
```

## REST API

### Genres

* `GET /api/genres`
* `GET /api/genres/{id}`
* `POST /api/genres`

Example:

```json
{
  "name": "RPG",
  "description": "Role-playing games"
}
```

### Developers

* `GET /api/developers`
* `GET /api/developers/{id}`
* `POST /api/developers`

Example:

```json
{
  "name": "CD Projekt RED",
  "country": "Poland",
  "foundedYear": 2002
}
```

### Games

* `GET /api/games`
* `GET /api/games/{id}`
* `POST /api/games`
* `PUT /api/games/{id}`
* `DELETE /api/games/{id}`

#### Digital Game (DIGITAL)

```json
{
  "type": "DIGITAL",
  "title": "Hades",
  "releaseYear": 2020,
  "publisher": "Supergiant Games",
  "genreId": 1,
  "developerId": 1,
  "platform": "Steam",
  "downloadSize": 15.5,
  "activationKey": "ABC-123"
}
```

#### Physical Game (PHYSICAL)

```json
{
  "type": "PHYSICAL",
  "title": "The Last of Us Part II",
  "releaseYear": 2020,
  "publisher": "Sony",
  "genreId": 1,
  "developerId": 1,
  "condition": "Used",
  "barcode": "999-111",
  "shelfLocation": "A-12"
}
```
