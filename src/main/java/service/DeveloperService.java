package service;

import exception.*;
import model.Developer;
import repository.DeveloperRepository;

import java.util.List;

public class DeveloperService {
    private DeveloperRepository developerRepository = new DeveloperRepository();

    private void validateDeveloper(Developer developer) throws InvalidInputException {
        if (developer.getName() == null || developer.getName().trim().isEmpty()) {
            throw new InvalidInputException("Developer name cannot be empty!");
        }
        if (developer.getFoundedYear() < 1900 || developer.getFoundedYear() > 2025) {
            throw new InvalidInputException("Founded year must be between 1900 and 2025!");
        }
    }

    public Developer createDeveloper(Developer developer)
            throws InvalidInputException, DatabaseOperationException {
        validateDeveloper(developer);
        return developerRepository.create(developer);
    }

    public List<Developer> getAllDevelopers() throws DatabaseOperationException {
        return developerRepository.getAll();
    }

    public Developer getDeveloperById(int id) throws DatabaseOperationException, ResourceNotFoundException {
        return developerRepository.getById(id);
    }

    public Developer updateDeveloper(int id, Developer developer)
            throws InvalidInputException, ResourceNotFoundException, DatabaseOperationException {
        validateDeveloper(developer);
        return developerRepository.update(id, developer);
    }

    public void deleteDeveloper(int id) throws DatabaseOperationException, ResourceNotFoundException {
        developerRepository.delete(id);
    }
}