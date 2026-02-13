package org.example.service;

import org.example.dto.DeveloperRequest;
import org.example.dto.DeveloperResponse;
import org.example.exception.ResourceNotFoundException;
import org.example.model.Developer;
import org.springframework.stereotype.Service;
import org.example.repository.DeveloperRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public DeveloperResponse create(DeveloperRequest request) {
        Developer developer = new Developer();
        developer.setName(request.getName());
        developer.setCountry(request.getCountry());
        developer.setFoundedYear(request.getFoundedYear());

        Developer saved = developerRepository.save(developer);
        return toResponse(saved);
    }

    public List<DeveloperResponse> getAll() {
        return developerRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public DeveloperResponse getById(Integer id) {
        Developer developer = developerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Developer not found: " + id));
        return toResponse(developer);
    }

    public DeveloperResponse update(Integer id, DeveloperRequest request) {
        Developer developer = developerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Developer not found: " + id));

        developer.setName(request.getName());
        developer.setCountry(request.getCountry());
        developer.setFoundedYear(request.getFoundedYear());

        Developer saved = developerRepository.save(developer);
        return toResponse(saved);
    }

    public void delete(Integer id) {
        if (!developerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Developer not found: " + id);
        }
        developerRepository.deleteById(id);
    }

    public Developer getEntityById(Integer id) {
        return developerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Developer not found: " + id));
    }

    private DeveloperResponse toResponse(Developer developer) {
        DeveloperResponse response = new DeveloperResponse();
        response.setId(developer.getId());
        response.setName(developer.getName());
        response.setCountry(developer.getCountry());
        response.setFoundedYear(developer.getFoundedYear());
        return response;
    }
}
