package org.example.controller;

import org.example.dto.DeveloperRequest;
import org.example.dto.DeveloperResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.example.service.DeveloperService;

import java.util.List;

@RestController
@RequestMapping("/api/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeveloperResponse create(@Valid @RequestBody DeveloperRequest request) {
        return developerService.create(request);
    }

    @GetMapping
    public List<DeveloperResponse> getAll() {
        return developerService.getAll();
    }

    @GetMapping("/{id}")
    public DeveloperResponse getById(@PathVariable Integer id) {
        return developerService.getById(id);
    }

    @PutMapping("/{id}")
    public DeveloperResponse update(@PathVariable Integer id,
                                    @Valid @RequestBody DeveloperRequest request) {
        return developerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        developerService.delete(id);
    }
}
