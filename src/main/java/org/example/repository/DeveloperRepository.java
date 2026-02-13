package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.model.Developer;

import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {

    Optional<Developer> findByName(String name);

}
