# GameVault API - Game Library Management System
Project Overview
GameVault API is a comprehensive Java-based application for managing a digital and physical game library. The system demonstrates advanced Object-Oriented Programming principles including inheritance, polymorphism, interfaces, and exception handling, while utilizing JDBC for database operations with PostgreSQL.
The application provides complete CRUD (Create, Read, Update, Delete) functionality for managing games, genres, and developers through a command-line interface.
Purpose
This project serves as a game library management system that allows users to:

Catalog both digital and physical games
Track game information including titles, publishers, release years, genres, and developers
Manage platform-specific details for digital games (Steam, Epic Games, GOG)
Track physical game conditions and storage locations
Perform validation on all input data
Handle errors gracefully through custom exception hierarchy

Entity Relationships
Core Entities
GameBase (Abstract Class)

Base class for all games
Contains common attributes: title, release year, publisher
Implements composition with Genre and Developer

DigitalGame (extends GameBase)

Represents digital game copies
Additional attributes: platform, download size, activation key
Implements Playable interface

PhysicalGame (extends GameBase)

Represents physical game copies
Additional attributes: condition, barcode, shelf location
Implements Tradeable interface

Genre

Represents game genres (RPG, Action, Adventure, etc.)
Referenced by games through composition

Developer

Represents game development studios
Contains company information and founding year
Referenced by games through composition

Database Schema
The system uses five main tables:
genres

genre_id (Primary Key)
name (Unique)
description

developers

developer_id (Primary Key)
name
country
founded_year

games

game_id (Primary Key)
title
release_year
publisher
genre_id (Foreign Key to genres)
developer_id (Foreign Key to developers)
game_type (DIGITAL or PHYSICAL)

digital_games

game_id (Primary Key, Foreign Key to games)
platform
download_size
activation_key

physical_games

game_id (Primary Key, Foreign Key to games)
condition
barcode
shelf_location

OOP Design Documentation
Abstract Class and Inheritance
GameBase serves as the abstract base class implementing core game functionality:

Abstract methods: displayInfo(), getGameDetails()
Concrete method: isNewRelease()
All game types inherit from this base

Subclasses:

DigitalGame: Adds digital distribution specific features
PhysicalGame: Adds physical media specific features

Both subclasses override abstract methods to provide type-specific implementations.
Interfaces
Playable Interface

Methods: canPlay(), startGame()
Implemented by: DigitalGame
Purpose: Represents games that can be launched

Tradeable Interface

Methods: getMarketValue(), isAvailableForTrade()
Implemented by: PhysicalGame
Purpose: Represents items that can be traded or sold

Polymorphism
The system demonstrates polymorphism through:

GameBase references can point to either DigitalGame or PhysicalGame objects
displayInfo() method behaves differently based on actual object type
Collections of GameBase can contain mixed game types
Runtime method resolution based on object type

Composition and Aggregation
Games use composition with:

Genre: Each game has one genre
Developer: Each game has one developer

This represents "has-a" relationships where games contain references to these entities.
Encapsulation
All model classes use:

Private fields
Public getter and setter methods
Validation in setters where appropriate
Data hiding principles

Multi-Layer Architecture
Repository Layer
Handles all database operations using JDBC:

GameRepository: CRUD operations for games
GenreRepository: Genre management
DeveloperRepository: Developer management
Uses PreparedStatement for SQL injection prevention
Manages database transactions

Service Layer
Contains business logic and validation:

GameService: Validates game data, enforces business rules
GenreService: Genre validation, duplicate checking
DeveloperService: Developer data validation
Coordinates between controllers and repositories

Controller Layer
Manages user interaction:

GameController: CLI interface for all operations
Handles user input
Displays formatted output
Catches and displays errors appropriately

Exception Handling
Custom exception hierarchy:

InvalidInputException: Base for validation errors
DuplicateResourceException: Extends InvalidInputException for duplicate entries
ResourceNotFoundException: For missing database records
DatabaseOperationException: For database-related errors

Sample Data
The database includes:
Genres: RPG, Action, Adventure, Strategy, Shooter, Horror, Racing, Puzzle, Simulation, Fighting
Developers: CD Projekt RED, Rockstar Games, Nintendo, Valve Corporation, FromSoftware, Supergiant Games, Bethesda Game Studios, Naughty Dog, Capcom, Epic Games
Sample Games:

Digital: The Witcher 3, Cyberpunk 2077, Portal 2, Hades, Half-Life: Alyx, Skyrim
Physical: The Legend of Zelda: Breath of the Wild, Red Dead Redemption 2, Dark Souls III, The Last of Us Part II, Resident Evil Village, Gran Turismo 7

Instructions to Compile and Run
Prerequisites

Java Development Kit (JDK) 11 or higher
PostgreSQL database server
PostgreSQL JDBC Driver (postgresql-42.6.0.jar)
IntelliJ IDEA (recommended) or any Java IDE

Database Setup

Install and start PostgreSQL
Create database named "GameLibrary Managment System"
Execute the schema.sql file to create tables:

sql-- Execute in pgAdmin or psql
\i path/to/schema.sql

Insert sample data using the provided INSERT statements

Project Setup

Clone or download the project
Open project in IntelliJ IDEA
Add PostgreSQL JDBC driver:

Right-click on postgresql-42.6.0.jar in lib folder
Select "Add as Library"
Or via File > Project Structure > Modules > Dependencies > Add JAR


Configure database connection in DatabaseConnection.java:

javaprivate static final String URL = "jdbc:postgresql://localhost:5432/GameLibrary Managment System";
private static final String USER = "postgres";
private static final String PASSWORD = "your_password";
