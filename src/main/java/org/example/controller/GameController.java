package org.example.controller;

import org.example.dto.GameRequest;
import org.example.dto.GameResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.example.service.GameService;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameResponse create(@Valid @RequestBody GameRequest request) {
        return gameService.create(request);
    }

    @GetMapping
    public List<GameResponse> getAll() {
        return gameService.getAll();
    }

    @GetMapping(value = "/lines", produces = "application/x-ndjson")
    public String getAllLines() {
        return gameService.getAllLines();
    }

    @GetMapping("/{id}")
    public GameResponse getById(@PathVariable Integer id) {
        return gameService.getById(id);
    }

    @PutMapping("/{id}")
    public GameResponse update(@PathVariable Integer id, @Valid @RequestBody GameRequest request) {
        return gameService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        gameService.delete(id);
    }
}
