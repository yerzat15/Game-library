package service;

import exception.*;
import model.Genre;
import repository.GenreRepository;

import java.util.List;

public class GenreService {
    private GenreRepository genreRepository = new GenreRepository();

    private void validateGenre(Genre genre) throws InvalidInputException {
        if (genre.getName() == null || genre.getName().trim().isEmpty()) {
            throw new InvalidInputException("Genre name cannot be empty!");
        }
        if (genre.getName().length() < 2) {
            throw new InvalidInputException("Genre name must be at least 2 characters!");
        }
    }

    private void checkDuplicate(String name) throws DuplicateResourceException, DatabaseOperationException {
        List<Genre> genres = genreRepository.getAll();
        for (Genre g : genres) {
            if (g.getName().equalsIgnoreCase(name)) {
                throw new DuplicateResourceException("Genre '" + name + "' already exists!");
            }
        }
    }

    public Genre createGenre(Genre genre)
            throws InvalidInputException, DuplicateResourceException, DatabaseOperationException {
        validateGenre(genre);
        checkDuplicate(genre.getName());
        return genreRepository.create(genre);
    }

    public List<Genre> getAllGenres() throws DatabaseOperationException {
        return genreRepository.getAll();
    }

    public Genre getGenreById(int id) throws DatabaseOperationException, ResourceNotFoundException {
        return genreRepository.getById(id);
    }

    public Genre updateGenre(int id, Genre genre)
            throws InvalidInputException, ResourceNotFoundException, DatabaseOperationException {
        validateGenre(genre);
        return genreRepository.update(id, genre);
    }

    public void deleteGenre(int id) throws DatabaseOperationException, ResourceNotFoundException {
        genreRepository.delete(id);
    }
}