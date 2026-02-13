# Game Library REST API (Spring Boot)

Проект преобразован из CLI/JDBC в **Spring Boot REST API** с архитектурой:

- Controller → Service → Repository → Database
- JSON request/response
- Global exception handling
- CRUD endpoints

## Реализованные паттерны

- **Singleton**: `patterns.LoggerSingleton`, `utils.DatabaseConnection`
- **Factory**: `patterns.GameFactory` (создаёт `DigitalGame` или `PhysicalGame`)
- **Builder**: `patterns.GameBuilder` (собирает сложный объект `GameBase` из `GameRequest`)

## Компонентные принципы

- **REP**: переиспользуемые модули (service/repository/patterns/utils)
- **CCP**: классы, изменяющиеся вместе, сгруппированы по пакетам
- **CRP**: контроллеры зависят только от service, service — от repository

## Технологии

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- H2 (in-memory, PostgreSQL mode)
- Jakarta Validation

## Структура

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

## Запуск

```bash
mvn spring-boot:run
```

## REST API

### Genres
- `GET /api/genres`
- `GET /api/genres/{id}`
- `POST /api/genres`

Пример:
```json
{
  "name": "RPG",
  "description": "Role-playing games"
}
```

### Developers
- `GET /api/developers`
- `GET /api/developers/{id}`
- `POST /api/developers`

Пример:
```json
{
  "name": "CD Projekt RED",
  "country": "Poland",
  "foundedYear": 2002
}
```

### Games
- `GET /api/games`
- `GET /api/games/{id}`
- `POST /api/games`
- `PUT /api/games/{id}`
- `DELETE /api/games/{id}`

#### DIGITAL game
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

#### PHYSICAL game
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
