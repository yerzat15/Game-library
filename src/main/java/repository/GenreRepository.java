package repository;

import exception.DatabaseOperationException;
import exception.ResourceNotFoundException;
import model.Genre;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreRepository {

    // CREATE
    public Genre create(Genre genre) throws DatabaseOperationException {
        String sql = "INSERT INTO genres (name, description) VALUES (?, ?) RETURNING genre_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, genre.getName());
            pstmt.setString(2, genre.getDescription());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                genre.setGenreId(rs.getInt("genre_id"));
            }

            System.out.println(" Genre created: " + genre.getName());
            return genre;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to create genre", e);
        }
    }

    // READ ALL
    public List<Genre> getAll() throws DatabaseOperationException {
        List<Genre> genres = new ArrayList<>();
        String sql = "SELECT * FROM genres ORDER BY genre_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Genre genre = new Genre(
                        rs.getInt("genre_id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
                genres.add(genre);
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get genres", e);
        }

        return genres;
    }

    // READ BY ID
    public Genre getById(int id) throws DatabaseOperationException, ResourceNotFoundException {
        String sql = "SELECT * FROM genres WHERE genre_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Genre(
                        rs.getInt("genre_id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
            } else {
                throw new ResourceNotFoundException("Genre with ID " + id + " not found");
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get genre by ID", e);
        }
    }

    // UPDATE
    public Genre update(int id, Genre genre) throws DatabaseOperationException, ResourceNotFoundException {
        String sql = "UPDATE genres SET name = ?, description = ? WHERE genre_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, genre.getName());
            pstmt.setString(2, genre.getDescription());
            pstmt.setInt(3, id);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new ResourceNotFoundException("Genre with ID " + id + " not found");
            }

            genre.setGenreId(id);
            System.out.println(" Genre updated: " + genre.getName());
            return genre;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update genre", e);
        }
    }

    // DELETE
    public void delete(int id) throws DatabaseOperationException, ResourceNotFoundException {
        String sql = "DELETE FROM genres WHERE genre_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new ResourceNotFoundException("Genre with ID " + id + " not found");
            }

            System.out.println(" Genre deleted (ID: " + id + ")");

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to delete genre", e);
        }
    }
}