package org.example.service.interfaces;

import org.example.model.Genre;
import java.util.List;

public interface IGenreService {
    Genre getGenreById(int id);
    List<Genre> getAllGenres();
}