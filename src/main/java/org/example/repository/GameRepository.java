package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.model.GameBase;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameBase, Integer> {

    List<GameBase> findByTitleContainingIgnoreCase(String title);

    List<GameBase> findByReleaseYear(Integer releaseYear);

}
