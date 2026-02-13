package org.example.service.interfaces;

import org.example.dto.GameRequest;
import org.example.model.GameBase;

import java.util.List;

public interface IGameService {
    List<GameBase> findAll();
    GameBase findById(Long id);
    GameBase create(GameRequest request);
    GameBase update(Long id, GameRequest request);
    void delete(Long id);
}
