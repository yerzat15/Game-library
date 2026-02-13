package org.example.patterns;

import org.example.dto.GameRequest;
import org.example.dto.GameType;
import org.example.exception.BadRequestException;
import org.example.model.*;

public class GameBuilder {

    private final GameBase game;

    private GameBuilder(GameBase game) {
        this.game = game;
    }

    public static GameBuilder from(GameRequest request) {
        if (request == null) {
            throw new BadRequestException("Request cannot be null");
        }
        GameBase base = GameFactory.createGame(request.getType());
        return new GameBuilder(base).applyCommon(request);
    }

    private GameBuilder applyCommon(GameRequest request) {
        game.setTitle(request.getTitle());
        game.setPrice(request.getPrice());
        game.setReleaseYear(request.getReleaseYear());
        return this;
    }

    public GameBuilder withRelations(Developer developer, Genre genre) {
        game.setDeveloper(developer);
        game.setGenre(genre);
        return this;
    }

    public GameBuilder applyTypeSpecific(GameRequest request) {
        if (request.getType() == GameType.DIGITAL) {
            DigitalGame dg = (DigitalGame) game;
            dg.setPlatform(request.getPlatform());
            dg.setDownloadSizeGb(request.getDownloadSizeGb());

            if (dg.getPlatform() == null || dg.getPlatform().isBlank()) {
                throw new BadRequestException("platform is required for DIGITAL game");
            }
            if (dg.getDownloadSizeGb() == null) {
                throw new BadRequestException("downloadSizeGb is required for DIGITAL game");
            }

        } else if (request.getType() == GameType.PHYSICAL) {
            PhysicalGame pg = (PhysicalGame) game;
            pg.setMediaType(request.getMediaType());
            pg.setStock(request.getStock());

            if (pg.getStock() == null) {
                throw new BadRequestException("stock is required for PHYSICAL game");
            }
            if (pg.getMediaType() == null || pg.getMediaType().isBlank()) {
                throw new BadRequestException("mediaType is required for PHYSICAL game");
            }
        }
        return this;
    }

    public GameBase build() {
        if (game.getDeveloper() == null) throw new BadRequestException("developer is required");
        if (game.getGenre() == null) throw new BadRequestException("genre is required");
        return game;
    }
}
