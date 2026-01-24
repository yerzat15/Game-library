package repository;

import exception.DatabaseOperationException;
import exception.ResourceNotFoundException;
import model.Developer;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperRepository {

    public Developer create(Developer developer) throws DatabaseOperationException {
        String sql = "INSERT INTO developers (name, country, founded_year) VALUES (?, ?, ?) RETURNING developer_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, developer.getName());
            pstmt.setString(2, developer.getCountry());
            pstmt.setInt(3, developer.getFoundedYear());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                developer.setDeveloperId(rs.getInt("developer_id"));
            }

            System.out.println("Developer created: " + developer.getName());
            return developer;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to create developer", e);
        }
    }

    public List<Developer> getAll() throws DatabaseOperationException {
        List<Developer> developers = new ArrayList<>();
        String sql = "SELECT * FROM developers ORDER BY developer_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Developer developer = new Developer(
                        rs.getInt("developer_id"),
                        rs.getString("name"),
                        rs.getString("country"),
                        rs.getInt("founded_year")
                );
                developers.add(developer);
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get developers", e);
        }

        return developers;
    }

    public Developer getById(int id) throws DatabaseOperationException, ResourceNotFoundException {
        String sql = "SELECT * FROM developers WHERE developer_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Developer(
                        rs.getInt("developer_id"),
                        rs.getString("name"),
                        rs.getString("country"),
                        rs.getInt("founded_year")
                );
            } else {
                throw new ResourceNotFoundException("Developer with ID " + id + " not found");
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get developer by ID", e);
        }
    }

    public Developer update(int id, Developer developer) throws DatabaseOperationException, ResourceNotFoundException {
        String sql = "UPDATE developers SET name = ?, country = ?, founded_year = ? WHERE developer_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, developer.getName());
            pstmt.setString(2, developer.getCountry());
            pstmt.setInt(3, developer.getFoundedYear());
            pstmt.setInt(4, id);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new ResourceNotFoundException("Developer with ID " + id + " not found");
            }

            developer.setDeveloperId(id);
            System.out.println(" Developer updated: " + developer.getName());
            return developer;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update developer", e);
        }
    }

    public void delete(int id) throws DatabaseOperationException, ResourceNotFoundException {
        String sql = "DELETE FROM developers WHERE developer_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new ResourceNotFoundException("Developer with ID " + id + " not found");
            }

            System.out.println(" Developer deleted (ID: " + id + ")");

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to delete developer", e);
        }
    }
}