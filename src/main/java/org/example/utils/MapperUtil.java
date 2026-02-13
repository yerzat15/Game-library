package org.example.utils;

import org.example.dto.DeveloperResponse;
import org.example.dto.GameResponse;
import org.example.dto.GameType;
import org.example.dto.GenreResponse;
import org.example.model.*;

public class MapperUtil {

    public static DeveloperResponse toResponse(Developer developer) {
        DeveloperResponse response = new DeveloperResponse();
        response.setId(developer.getId());
        response.setName(developer.getName());
        response.setCountry(developer.getCountry());
        response.setFoundedYear(developer.getFoundedYear());
        return response;
    }

    public static GenreResponse toResponse(Genre genre) {
        GenreResponse response = new GenreResponse();
        response.setId(genre.getId());
        response.setName(genre.getName());
        response.setDescription(genre.getDescription());
        return response;
    }

    public static GameResponse toResponse(GameBase game) {
        GameResponse response = new GameResponse();

        response.setId(game.getId());
        response.setTitle(game.getTitle());
        response.setPrice(game.getPrice());
        response.setReleaseYear(game.getReleaseYear());

        response.setDeveloperId(game.getDeveloper().getId());
        response.setDeveloperName(game.getDeveloper().getName());

        response.setGenreId(game.getGenre().getId());
        response.setGenreName(game.getGenre().getName());

        if (game instanceof DigitalGame dg) {
            response.setType(GameType.DIGITAL);
            response.setPlatform(dg.getPlatform());
            response.setDownloadSizeGb(dg.getDownloadSizeGb());
        } else if (game instanceof PhysicalGame pg) {
            response.setType(GameType.PHYSICAL);
            response.setMediaType(pg.getMediaType());
            response.setStock(pg.getStock());
        }

        return response;
    }
}
