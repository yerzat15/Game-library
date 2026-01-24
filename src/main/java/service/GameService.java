package service;

import exception.*;
import model.*;
import repository.GameRepository;
import repository.GenreRepository;
import repository.DeveloperRepository;
import java.util.ArrayList;
import java.util.List;

public class GameService {
    private GameRepository gameRepository = new GameRepository();
    private GenreRepository genreRepository = new GenreRepository();
    private DeveloperRepository developerRepository = new DeveloperRepository();

    private void validateTitle(String title) throws InvalidInputException {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidInputException("Game title cannot be empty!");
        }
        if (title.length() < 2) {
            throw new InvalidInputException("Game title must be at least 2 characters!");
        }
    }

    private void validateReleaseYear(int year) throws InvalidInputException {
        if (year < 1970 || year > 2025) {
            throw new InvalidInputException("Release year must be between 1970 and 2025!");
        }
    }

    private void validatePublisher(String publisher) throws InvalidInputException {
        if (publisher == null || publisher.trim().isEmpty()) {
            throw new InvalidInputException("Publisher cannot be empty!");
        }
    }

    private void validateGenre(Genre genre) throws InvalidInputException, DatabaseOperationException {
        if (genre == null || genre.getGenreId() <= 0) {
            throw new InvalidInputException("Valid genre is required!");
        }
        try {
            genreRepository.getById(genre.getGenreId());
        } catch (ResourceNotFoundException e) {
            throw new InvalidInputException("Genre with ID " + genre.getGenreId() + " does not exist!");
        }
    }

    private void validateDeveloper(Developer developer) throws InvalidInputException, DatabaseOperationException {
        if (developer == null || developer.getDeveloperId() <= 0) {
            throw new InvalidInputException("Valid developer is required!");
        }
        try {
            developerRepository.getById(developer.getDeveloperId());
        } catch (ResourceNotFoundException e) {
            throw new InvalidInputException("Developer with ID " + developer.getDeveloperId() + " does not exist!");
        }
    }

    private void validateDigitalGame(DigitalGame game) throws InvalidInputException {
        if (game.getPlatform() == null || game.getPlatform().trim().isEmpty()) {
            throw new InvalidInputException("Platform cannot be empty!");
        }
        if (game.getDownloadSize() <= 0) {
            throw new InvalidInputException("Download size must be greater than 0!");
        }
    }

    private void validatePhysicalGame(PhysicalGame game) throws InvalidInputException {
        if (game.getCondition() == null || game.getCondition().trim().isEmpty()) {
            throw new InvalidInputException("Condition cannot be empty!");
        }
    }

    public DigitalGame createDigitalGame(DigitalGame game)
            throws InvalidInputException, DatabaseOperationException {
        validateTitle(game.getTitle());
        validateReleaseYear(game.getReleaseYear());
        validatePublisher(game.getPublisher());
        validateGenre(game.getGenre());
        validateDeveloper(game.getDeveloper());
        validateDigitalGame(game);

        return gameRepository.createDigital(game);
    }

    public PhysicalGame createPhysicalGame(PhysicalGame game)
            throws InvalidInputException, DatabaseOperationException {
        validateTitle(game.getTitle());
        validateReleaseYear(game.getReleaseYear());
        validatePublisher(game.getPublisher());
        validateGenre(game.getGenre());
        validateDeveloper(game.getDeveloper());
        validatePhysicalGame(game);

        return gameRepository.createPhysical(game);
    }

    public List<GameBase> getAllGames() throws DatabaseOperationException {
        return gameRepository.getAll();
    }

    public GameBase getGameById(int id) throws DatabaseOperationException, ResourceNotFoundException {
        if (id <= 0) {
            throw new ResourceNotFoundException("Game ID must be positive!");
        }
        return gameRepository.getById(id);
    }

    public GameBase updateGame(int id, GameBase game)
            throws InvalidInputException, ResourceNotFoundException, DatabaseOperationException {
        validateTitle(game.getTitle());
        validateReleaseYear(game.getReleaseYear());
        validatePublisher(game.getPublisher());
        validateGenre(game.getGenre());
        validateDeveloper(game.getDeveloper());

        return gameRepository.update(id, game);
    }

    public void deleteGame(int id) throws DatabaseOperationException, ResourceNotFoundException {
        if (id <= 0) {
            throw new ResourceNotFoundException("Game ID must be positive!");
        }
        gameRepository.delete(id);
    }

    public List<GameBase> getNewReleases() throws DatabaseOperationException {
        List<GameBase> allGames = gameRepository.getAll();
        List<GameBase> newReleases = new ArrayList<>();

        for (GameBase game : allGames) {
            if (game.isNewRelease()) {
                newReleases.add(game);
            }
        }
        return newReleases;
    }
}