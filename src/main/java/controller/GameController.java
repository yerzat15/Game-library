package controller;

import exception.*;
import model.*;
import service.GameService;
import service.GenreService;
import service.DeveloperService;

import java.util.List;
import java.util.Scanner;

public class GameController {
    private GameService gameService = new GameService();
    private GenreService genreService = new GenreService();
    private DeveloperService developerService = new DeveloperService();
    private Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        System.out.println("\n=============================================");
        System.out.println("         GameVault API - Main Menu     ");
        System.out.println("=============================================");
        System.out.println("1. View All Games");
        System.out.println("2. View Game by ID");
        System.out.println("3. Add Digital Game");
        System.out.println("4. Add Physical Game");
        System.out.println("5. Update Game");
        System.out.println("6. Delete Game");
        System.out.println("7. View All Genres");
        System.out.println("8. View All Developers");
        System.out.println("9. Demonstrate Polymorphism");
        System.out.println("10. Demonstrate Interfaces");
        System.out.println("0. Exit");
        System.out.print("\nEnter choice: ");
    }

    public void viewAllGames() {
        try {
            List<GameBase> games = gameService.getAllGames();

            if (games.isEmpty()) {
                System.out.println("\n No games found in the library.");
                return;
            }

            System.out.println("\n=============================================");
            System.out.println("             All Games Library          ");
            System.out.println("=============================================");

            for (GameBase game : games) {
                System.out.println("\n" + game.getGameDetails());
                System.out.println("  Genre: " + game.getGenre().getName());
                System.out.println("  Developer: " + game.getDeveloper().getName());
                System.out.println("  Year: " + game.getReleaseYear());
            }

        } catch (DatabaseOperationException e) {
            System.out.println("\n Error: " + e.getMessage());
        }
    }

    public void viewGameById() {
        try {
            System.out.print("\nEnter Game ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            GameBase game = gameService.getGameById(id);

            System.out.println("\n=============================================");
            System.out.println("             Game Details              ");
            System.out.println("=============================================");
            game.displayInfo();

        } catch (NumberFormatException e) {
            System.out.println("\n Invalid ID format!");
        } catch (ResourceNotFoundException e) {
            System.out.println("\n " + e.getMessage());
        } catch (DatabaseOperationException e) {
            System.out.println("\n Database error: " + e.getMessage());
        }
    }

    public void addDigitalGame() {
        try {
            System.out.println("\n=============================================");
            System.out.println("         Add Digital Game             ");
            System.out.println("=============================================");

            System.out.print("Title: ");
            String title = scanner.nextLine();

            System.out.print("Release Year: ");
            int year = Integer.parseInt(scanner.nextLine());

            System.out.print("Publisher: ");
            String publisher = scanner.nextLine();

            System.out.print("Genre ID: ");
            int genreId = Integer.parseInt(scanner.nextLine());
            Genre genre = genreService.getGenreById(genreId);

            System.out.print("Developer ID: ");
            int devId = Integer.parseInt(scanner.nextLine());
            Developer developer = developerService.getDeveloperById(devId);

            System.out.print("Platform (Steam/Epic/GOG): ");
            String platform = scanner.nextLine();

            System.out.print("Download Size (GB): ");
            double size = Double.parseDouble(scanner.nextLine());

            System.out.print("Activation Key: ");
            String key = scanner.nextLine();

            DigitalGame game = new DigitalGame(0, title, year, publisher, genre, developer,
                    platform, size, key);

            DigitalGame created = gameService.createDigitalGame(game);
            System.out.println("\n Digital game created successfully! ID: " + created.getGameId());

        } catch (NumberFormatException e) {
            System.out.println("\n Invalid input format!");
        } catch (InvalidInputException e) {
            System.out.println("\n Validation error: " + e.getMessage());
        } catch (ResourceNotFoundException e) {
            System.out.println("\n " + e.getMessage());
        } catch (DatabaseOperationException e) {
            System.out.println("\n Database error: " + e.getMessage());
        }
    }

    public void addPhysicalGame() {
        try {
            System.out.println("\n=============================================");
            System.out.println("          Add Physical Game             ");
            System.out.println("=============================================");

            System.out.print("Title: ");
            String title = scanner.nextLine();

            System.out.print("Release Year: ");
            int year = Integer.parseInt(scanner.nextLine());

            System.out.print("Publisher: ");
            String publisher = scanner.nextLine();

            System.out.print("Genre ID: ");
            int genreId = Integer.parseInt(scanner.nextLine());
            Genre genre = genreService.getGenreById(genreId);

            System.out.print("Developer ID: ");
            int devId = Integer.parseInt(scanner.nextLine());
            Developer developer = developerService.getDeveloperById(devId);

            System.out.print("Condition (New/Used/Collector Edition): ");
            String condition = scanner.nextLine();

            System.out.print("Barcode: ");
            String barcode = scanner.nextLine();

            System.out.print("Shelf Location: ");
            String location = scanner.nextLine();

            PhysicalGame game = new PhysicalGame(0, title, year, publisher, genre, developer,
                    condition, barcode, location);

            PhysicalGame created = gameService.createPhysicalGame(game);
            System.out.println("\n Physical game created successfully! ID: " + created.getGameId());

        } catch (NumberFormatException e) {
            System.out.println("\n Invalid input format!");
        } catch (InvalidInputException e) {
            System.out.println("\n Validation error: " + e.getMessage());
        } catch (ResourceNotFoundException e) {
            System.out.println("\n " + e.getMessage());
        } catch (DatabaseOperationException e) {
            System.out.println("\n Database error: " + e.getMessage());
        }
    }

    public void updateGame() {
        try {
            System.out.print("\nEnter Game ID to update: ");
            int id = Integer.parseInt(scanner.nextLine());

            GameBase existing = gameService.getGameById(id);

            System.out.println("\nCurrent title: " + existing.getTitle());
            System.out.print("New Title (or press Enter to keep): ");
            String title = scanner.nextLine();
            if (title.isEmpty()) title = existing.getTitle();

            System.out.println("Current year: " + existing.getReleaseYear());
            System.out.print("New Year (or press Enter to keep): ");
            String yearStr = scanner.nextLine();
            int year = yearStr.isEmpty() ? existing.getReleaseYear() : Integer.parseInt(yearStr);

            System.out.println("Current publisher: " + existing.getPublisher());
            System.out.print("New Publisher (or press Enter to keep): ");
            String publisher = scanner.nextLine();
            if (publisher.isEmpty()) publisher = existing.getPublisher();

            existing.setTitle(title);
            existing.setReleaseYear(year);
            existing.setPublisher(publisher);

            gameService.updateGame(id, existing);
            System.out.println("\n Game updated successfully!");

        } catch (NumberFormatException e) {
            System.out.println("\n Invalid input format!");
        } catch (InvalidInputException e) {
            System.out.println("\n Validation error: " + e.getMessage());
        } catch (ResourceNotFoundException e) {
            System.out.println("\n " + e.getMessage());
        } catch (DatabaseOperationException e) {
            System.out.println("\n Database error: " + e.getMessage());
        }
    }

    public void deleteGame() {
        try {
            System.out.print("\nEnter Game ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());

            GameBase game = gameService.getGameById(id);
            System.out.println("\nAre you sure you want to delete: " + game.getTitle() + "? (yes/no)");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("yes")) {
                gameService.deleteGame(id);
                System.out.println("\n Game deleted successfully!");
            } else {
                System.out.println("\n Deletion cancelled.");
            }

        } catch (NumberFormatException e) {
            System.out.println("\n Invalid ID format!");
        } catch (ResourceNotFoundException e) {
            System.out.println("\n " + e.getMessage());
        } catch (DatabaseOperationException e) {
            System.out.println("\n Database error: " + e.getMessage());
        }
    }

    public void viewAllGenres() {
        try {
            List<Genre> genres = genreService.getAllGenres();

            System.out.println("\n=============================================");
            System.out.println("               All Genres               ");
            System.out.println("=============================================");

            for (Genre genre : genres) {
                System.out.println("\n[" + genre.getGenreId() + "] " + genre.getName());
                System.out.println("  Description: " + genre.getDescription());
            }

        } catch (DatabaseOperationException e) {
            System.out.println("\n Error: " + e.getMessage());
        }
    }

    public void viewAllDevelopers() {
        try {
            List<Developer> developers = developerService.getAllDevelopers();

            System.out.println("\n=============================================");
            System.out.println("             All Developers             ");
            System.out.println("=============================================");

            for (Developer dev : developers) {
                System.out.println("\n[" + dev.getDeveloperId() + "] " + dev.getName());
                System.out.println("  Country: " + dev.getCountry());
                System.out.println("  Founded: " + dev.getFoundedYear());
            }

        } catch (DatabaseOperationException e) {
            System.out.println("\n Error: " + e.getMessage());
        }
    }

    public void demonstratePolymorphism() {
        try {
            System.out.println("\n=============================================");
            System.out.println("         Polymorphism Demonstration      ");
            System.out.println("=============================================");

            List<GameBase> games = gameService.getAllGames();

            System.out.println("\n Calling displayInfo() on base class reference:");
            System.out.println("   (Same method, different implementations)\n");

            for (GameBase game : games) {
                // Polymorphism: calling overridden method
                game.displayInfo();
                System.out.println("---");
            }

            System.out.println("\n Calling abstract method getGameDetails():\n");
            for (GameBase game : games) {
                System.out.println(game.getGameDetails());
            }

        } catch (DatabaseOperationException e) {
            System.out.println("\n Error: " + e.getMessage());
        }
    }

    public void demonstrateInterfaces() {
        try {
            System.out.println("\n========================================");
            System.out.println("        Interface Demonstration         ");
            System.out.println("========================================");

            List<GameBase> games = gameService.getAllGames();

            System.out.println("\n Playable Interface (Digital Games):\n");
            for (GameBase game : games) {
                if (game instanceof DigitalGame) {
                    DigitalGame dg = (DigitalGame) game;
                    System.out.println(" " + dg.getTitle());
                    System.out.println("   Can Play? " + dg.canPlay());
                    dg.startGame();
                    System.out.println();
                }
            }

            System.out.println("\n Tradeable Interface (Physical Games):\n");
            for (GameBase game : games) {
                if (game instanceof PhysicalGame) {
                    PhysicalGame pg = (PhysicalGame) game;
                    System.out.println(" " + pg.getTitle());
                    System.out.println("   Market Value: $" + pg.getMarketValue());
                    System.out.println("   Available for Trade? " + pg.isAvailableForTrade());
                    System.out.println();
                }
            }

        } catch (DatabaseOperationException e) {
            System.out.println("\n Error: " + e.getMessage());
        }
    }

    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        viewAllGames();
                        break;
                    case 2:
                        viewGameById();
                        break;
                    case 3:
                        addDigitalGame();
                        break;
                    case 4:
                        addPhysicalGame();
                        break;
                    case 5:
                        updateGame();
                        break;
                    case 6:
                        deleteGame();
                        break;
                    case 7:
                        viewAllGenres();
                        break;
                    case 8:
                        viewAllDevelopers();
                        break;
                    case 9:
                        demonstratePolymorphism();
                        break;
                    case 10:
                        demonstrateInterfaces();
                        break;
                    case 0:
                        System.out.println("\n Closing GameVault API...");
                        running = false;
                        break;
                    default:
                        System.out.println("\n Invalid choice! Please try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("\n Please enter a valid number!");
            }
        }

        scanner.close();
    }
}