package repository;

import exception.DatabaseOperationException;
import exception.ResourceNotFoundException;
import model.*;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameRepository {
    private GenreRepository genreRepository = new GenreRepository();
    private DeveloperRepository developerRepository = new DeveloperRepository();

    public DigitalGame createDigital(DigitalGame game) throws DatabaseOperationException {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction

            String gameSql = "INSERT INTO games (title, release_year, publisher, genre_id, developer_id, game_type) " +
                    "VALUES (?, ?, ?, ?, ?, 'DIGITAL') RETURNING game_id";

            PreparedStatement gameStmt = conn.prepareStatement(gameSql);
            gameStmt.setString(1, game.getTitle());
            gameStmt.setInt(2, game.getReleaseYear());
            gameStmt.setString(3, game.getPublisher());
            gameStmt.setInt(4, game.getGenre().getGenreId());
            gameStmt.setInt(5, game.getDeveloper().getDeveloperId());

            ResultSet rs = gameStmt.executeQuery();
            if (rs.next()) {
                game.setGameId(rs.getInt("game_id"));
            }

            String digitalSql = "INSERT INTO digital_games (game_id, platform, download_size, activation_key) " +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement digitalStmt = conn.prepareStatement(digitalSql);
            digitalStmt.setInt(1, game.getGameId());
            digitalStmt.setString(2, game.getPlatform());
            digitalStmt.setDouble(3, game.getDownloadSize());
            digitalStmt.setString(4, game.getActivationKey());
            digitalStmt.executeUpdate();

            conn.commit(); // Commit transaction
            System.out.println(" Digital game created: " + game.getTitle());
            return game;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback on error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw new DatabaseOperationException("Failed to create digital game", e);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public PhysicalGame createPhysical(PhysicalGame game) throws DatabaseOperationException {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            String gameSql = "INSERT INTO games (title, release_year, publisher, genre_id, developer_id, game_type) " +
                    "VALUES (?, ?, ?, ?, ?, 'PHYSICAL') RETURNING game_id";

            PreparedStatement gameStmt = conn.prepareStatement(gameSql);
            gameStmt.setString(1, game.getTitle());
            gameStmt.setInt(2, game.getReleaseYear());
            gameStmt.setString(3, game.getPublisher());
            gameStmt.setInt(4, game.getGenre().getGenreId());
            gameStmt.setInt(5, game.getDeveloper().getDeveloperId());

            ResultSet rs = gameStmt.executeQuery();
            if (rs.next()) {
                game.setGameId(rs.getInt("game_id"));
            }

            String physicalSql = "INSERT INTO physical_games (game_id, condition, barcode, shelf_location) " +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement physicalStmt = conn.prepareStatement(physicalSql);
            physicalStmt.setInt(1, game.getGameId());
            physicalStmt.setString(2, game.getCondition());
            physicalStmt.setString(3, game.getBarcode());
            physicalStmt.setString(4, game.getShelfLocation());
            physicalStmt.executeUpdate();

            conn.commit();
            System.out.println(" Physical game created: " + game.getTitle());
            return game;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw new DatabaseOperationException("Failed to create physical game", e);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<GameBase> getAll() throws DatabaseOperationException {
        List<GameBase> games = new ArrayList<>();
        String sql = "SELECT g.*, gen.name as genre_name, gen.description as genre_desc, " +
                "d.name as dev_name, d.country, d.founded_year " +
                "FROM games g " +
                "JOIN genres gen ON g.genre_id = gen.genre_id " +
                "JOIN developers d ON g.developer_id = d.developer_id " +
                "ORDER BY g.game_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                GameBase game = buildGameFromResultSet(rs);
                games.add(game);
            }

        } catch (SQLException | ResourceNotFoundException e) {
            throw new DatabaseOperationException("Failed to get all games", e);
        }

        return games;
    }

    public GameBase getById(int id) throws DatabaseOperationException, ResourceNotFoundException {
        String sql = "SELECT g.*, gen.name as genre_name, gen.description as genre_desc, " +
                "d.name as dev_name, d.country, d.founded_year " +
                "FROM games g " +
                "JOIN genres gen ON g.genre_id = gen.genre_id " +
                "JOIN developers d ON g.developer_id = d.developer_id " +
                "WHERE g.game_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return buildGameFromResultSet(rs);
            } else {
                throw new ResourceNotFoundException("Game with ID " + id + " not found");
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get game by ID", e);
        }
    }

    public GameBase update(int id, GameBase game) throws DatabaseOperationException, ResourceNotFoundException {
        String sql = "UPDATE games SET title = ?, release_year = ?, publisher = ?, " +
                "genre_id = ?, developer_id = ? WHERE game_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, game.getTitle());
            pstmt.setInt(2, game.getReleaseYear());
            pstmt.setString(3, game.getPublisher());
            pstmt.setInt(4, game.getGenre().getGenreId());
            pstmt.setInt(5, game.getDeveloper().getDeveloperId());
            pstmt.setInt(6, id);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new ResourceNotFoundException("Game with ID " + id + " not found");
            }

            game.setGameId(id);
            System.out.println(" Game updated: " + game.getTitle());
            return game;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update game", e);
        }
    }

    public void delete(int id) throws DatabaseOperationException, ResourceNotFoundException {
        getById(id);

        String sql = "DELETE FROM games WHERE game_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println(" Game deleted (ID: " + id + ")");

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to delete game", e);
        }
    }

    private GameBase buildGameFromResultSet(ResultSet rs) throws SQLException, ResourceNotFoundException {
        // Build Genre
        Genre genre = new Genre(
                rs.getInt("genre_id"),
                rs.getString("genre_name"),
                rs.getString("genre_desc")
        );

        Developer developer = new Developer(
                rs.getInt("developer_id"),
                rs.getString("dev_name"),
                rs.getString("country"),
                rs.getInt("founded_year")
        );

        String gameType = rs.getString("game_type");
        int gameId = rs.getInt("game_id");

        if ("DIGITAL".equals(gameType)) {
            return getDigitalGameDetails(gameId, rs, genre, developer);
        } else {
            return getPhysicalGameDetails(gameId, rs, genre, developer);
        }
    }

    // Get Digital Game details
    private DigitalGame getDigitalGameDetails(int gameId, ResultSet gameRs, Genre genre, Developer developer)
            throws SQLException {
        String sql = "SELECT * FROM digital_games WHERE game_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, gameId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new DigitalGame(
                        gameId,
                        gameRs.getString("title"),
                        gameRs.getInt("release_year"),
                        gameRs.getString("publisher"),
                        genre,
                        developer,
                        rs.getString("platform"),
                        rs.getDouble("download_size"),
                        rs.getString("activation_key")
                );
            }
        }
        return null;
    }

    // Get Physical Game details
    private PhysicalGame getPhysicalGameDetails(int gameId, ResultSet gameRs, Genre genre, Developer developer)
            throws SQLException {
        String sql = "SELECT * FROM physical_games WHERE game_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, gameId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new PhysicalGame(
                        gameId,
                        gameRs.getString("title"),
                        gameRs.getInt("release_year"),
                        gameRs.getString("publisher"),
                        genre,
                        developer,
                        rs.getString("condition"),
                        rs.getString("barcode"),
                        rs.getString("shelf_location")
                );
            }
        }
        return null;
    }
}