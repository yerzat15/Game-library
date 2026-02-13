package org.example.service.interfaces;

import org.example.model.Developer;
import java.util.List;

public interface IDeveloperService {
    Developer getDeveloperById(int id);
    List<Developer> getAllDevelopers();
}