package org.example.service;

import org.example.dto.GenreRequest;
import org.example.dto.GenreResponse;
import org.example.exception.ResourceNotFoundException;
import org.example.model.Genre;
import org.springframework.stereotype.Service;
import org.example.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public GenreResponse create(GenreRequest request) {
        Genre genre = new Genre();
        genre.setName(request.getName());
        genre.setDescription(request.getDescription());

        Genre saved = genreRepository.save(genre);
        return toResponse(saved);
    }

    public List<GenreResponse> getAll() {
        return genreRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public GenreResponse getById(Integer id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found: " + id));
        return toResponse(genre);
    }

    public GenreResponse update(Integer id, GenreRequest request) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found: " + id));

        genre.setName(request.getName());
        genre.setDescription(request.getDescription());

        Genre saved = genreRepository.save(genre);
        return toResponse(saved);
    }

    public void delete(Integer id) {
        if (!genreRepository.existsById(id)) {
            throw new ResourceNotFoundException("Genre not found: " + id);
        }
        genreRepository.deleteById(id);
    }

    public Genre getEntityById(Integer id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found: " + id));
    }

    private GenreResponse toResponse(Genre genre) {
        GenreResponse response = new GenreResponse();
        response.setId(genre.getId());
        response.setName(genre.getName());
        response.setDescription(genre.getDescription());
        return response;
    }
}
