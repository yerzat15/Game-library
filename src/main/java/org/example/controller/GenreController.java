package org.example.controller;

import org.example.dto.GenreRequest;
import org.example.dto.GenreResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.example.service.GenreService;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenreResponse create(@Valid @RequestBody GenreRequest request) {
        return genreService.create(request);
    }

    @GetMapping
    public List<GenreResponse> getAll() {
        return genreService.getAll();
    }

    @GetMapping("/{id}")
    public GenreResponse getById(@PathVariable Integer id) {
        return genreService.getById(id);
    }

    @PutMapping("/{id}")
    public GenreResponse update(@PathVariable Integer id,
                                @Valid @RequestBody GenreRequest request) {
        return genreService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        genreService.delete(id);
    }
}
