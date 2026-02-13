package org.example.patterns;

import org.example.dto.GameType;
import org.example.model.DigitalGame;
import org.example.model.GameBase;
import org.example.model.PhysicalGame;

public class GameFactory {

    public static GameBase createGame(GameType type) {
        if (type == null) {
            throw new IllegalArgumentException("Game type cannot be null");
        }

        return switch (type) {
            case DIGITAL -> new DigitalGame();
            case PHYSICAL -> new PhysicalGame();
        };
    }
}
