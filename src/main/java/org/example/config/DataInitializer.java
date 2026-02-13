package org.example.config;

import org.example.model.Developer;
import org.example.model.Genre;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.example.repository.DeveloperRepository;
import org.example.repository.GenreRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedBaseData(GenreRepository genreRepository, DeveloperRepository developerRepository) {
        return args -> {
            if (genreRepository.count() == 0) {
                Genre action = new Genre();
                action.setName("Action");
                action.setDescription("Action games");
                genreRepository.save(action);
            }

            if (developerRepository.count() == 0) {
                Developer valve = new Developer();
                valve.setName("Valve");
                valve.setCountry("USA");
                valve.setFoundedYear(1996);
                developerRepository.save(valve);
            }
        };
    }
}