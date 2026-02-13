package org.example.service;

import org.example.dto.GameRequest;
import org.example.dto.GameResponse;
import org.example.dto.GameType;
import org.example.exception.BadRequestException;
import org.example.exception.ResourceNotFoundException;
import org.example.model.*;
import org.example.patterns.GameBuilder;
import org.example.patterns.LoggerSingleton;
import org.example.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final DeveloperService developerService;
    private final GenreService genreService;

    public GameService(GameRepository gameRepository,
                       DeveloperService developerService,
                       GenreService genreService) {
        this.gameRepository = gameRepository;
        this.developerService = developerService;
        this.genreService = genreService;
    }

    public GameResponse create(GameRequest request) {
        Developer developer = developerService.getEntityById(request.getDeveloperId());
        Genre genre = genreService.getEntityById(request.getGenreId());

        GameBase game = GameBuilder.from(request)
                .withRelations(developer, genre)
                .applyTypeSpecific(request)
                .build();

        GameBase saved = gameRepository.save(game);
        LoggerSingleton.getInstance().info("Created game id=" + saved.getId());

        return mapToResponse(saved);
    }

    public List<GameResponse> getAll() {
        return gameRepository.findAll(
                        org.springframework.data.domain.Sort.by(
                                org.springframework.data.domain.Sort.Direction.ASC, "title"
                        )
                ).stream()
                .map(this::mapToResponse)
                .collect(java.util.stream.Collectors.toList());
    }
    public String getAllLines() {
        return getAll().stream()
                .map(this::toJsonLine)
                .collect(java.util.stream.Collectors.joining("\n"));
    }

    private String toJsonLine(GameResponse r) {
        // вручную, чтобы было ровно в одну строку и в понятном порядке
        return "{"
                + "\"id\":" + r.getId()
                + ",\"title\":\"" + escape(r.getTitle()) + "\""
                + ",\"price\":" + r.getPrice()
                + ",\"releaseYear\":" + r.getReleaseYear()
                + ",\"developerId\":" + r.getDeveloperId()
                + ",\"developerName\":\"" + escape(r.getDeveloperName()) + "\""
                + ",\"genreId\":" + r.getGenreId()
                + ",\"genreName\":\"" + escape(r.getGenreName()) + "\""
                + ",\"type\":\"" + r.getType() + "\""
                + ",\"platform\":" + toJsonNullableString(r.getPlatform())
                + ",\"downloadSizeGb\":" + (r.getDownloadSizeGb() == null ? "null" : r.getDownloadSizeGb().toString())
                + ",\"mediaType\":" + toJsonNullableString(r.getMediaType())
                + ",\"stock\":" + (r.getStock() == null ? "null" : r.getStock())
                + "}";
    }

    private String toJsonNullableString(String s) {
        return (s == null) ? "null" : "\"" + escape(s) + "\"";
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    public GameResponse getById(Integer id) {
        GameBase game = gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found: " + id));
        return mapToResponse(game);
    }

    public GameResponse update(Integer id, GameRequest request) {
        GameBase existing = gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found: " + id));

        if (existing instanceof DigitalGame && request.getType() != GameType.DIGITAL) {
            throw new BadRequestException("Cannot change game type (DIGITAL -> PHYSICAL)");
        }
        if (existing instanceof PhysicalGame && request.getType() != GameType.PHYSICAL) {
            throw new BadRequestException("Cannot change game type (PHYSICAL -> DIGITAL)");
        }

        Developer developer = developerService.getEntityById(request.getDeveloperId());
        Genre genre = genreService.getEntityById(request.getGenreId());

        existing.setTitle(request.getTitle());
        existing.setPrice(request.getPrice());
        existing.setReleaseYear(request.getReleaseYear());
        existing.setDeveloper(developer);
        existing.setGenre(genre);

        if (existing instanceof DigitalGame dg) {
            dg.setPlatform(request.getPlatform());
            dg.setDownloadSizeGb(request.getDownloadSizeGb());

            if (dg.getPlatform() == null || dg.getPlatform().isBlank()) {
                throw new BadRequestException("platform is required for DIGITAL game");
            }
            if (dg.getDownloadSizeGb() == null) {
                throw new BadRequestException("downloadSizeGb is required for DIGITAL game");
            }

        } else if (existing instanceof PhysicalGame pg) {
            pg.setMediaType(request.getMediaType());
            pg.setStock(request.getStock());

            if (pg.getMediaType() == null || pg.getMediaType().isBlank()) {
                throw new BadRequestException("mediaType is required for PHYSICAL game");
            }
            if (pg.getStock() == null) {
                throw new BadRequestException("stock is required for PHYSICAL game");
            }
        }

        GameBase saved = gameRepository.save(existing);
        LoggerSingleton.getInstance().info("Updated game id=" + saved.getId());

        return mapToResponse(saved);
    }

    public void delete(Integer id) {
        if (!gameRepository.existsById(id)) {
            throw new ResourceNotFoundException("Game not found: " + id);
        }
        gameRepository.deleteById(id);
        LoggerSingleton.getInstance().info("Deleted game id=" + id);
    }

    private GameResponse mapToResponse(GameBase game) {
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
