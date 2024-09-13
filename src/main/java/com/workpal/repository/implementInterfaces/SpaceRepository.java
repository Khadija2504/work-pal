package com.workpal.repository.implementInterfaces;

import com.workpal.repository.interfaces.SpaceInterface;
import com.workpal.model.Space;
import com.workpal.model.User;
import com.workpal.service.SessionUser;
import com.workpal.util.JdcbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpaceRepository implements SpaceInterface {
    public void saveSpace(Space space) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String query = "INSERT INTO spaces (name, description, manager_id, policies, type, price, tail) VALUES (?, ?, ?, ?, ?, ?, ?) ";
        PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, space.getName());
        statement.setString(2, space.getDescription());
        statement.setInt(3, space.getManager_id());
        statement.setString(4, space.getPolicies());
        statement.setString(5, space.getType());
        statement.setInt(6, space.getPrice());
        statement.setInt(7, space.getTail());
        statement.executeUpdate();
    }
    public List<Space> getAllSpaces() throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        User loggedInUser = SessionUser.getLoggedInUser();
        String sql = "SELECT * FROM spaces WHERE manager_id = ?";
        List<Space> spaces = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, loggedInUser.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    spaces.add(new Space(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("policies"),
                            rs.getInt("manager_id"),
                            rs.getString("type"),
                            rs.getInt("price"),
                            rs.getInt("tail")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching spaces", e);
        }

        return spaces;
    }

    public boolean updateSpace(Space space) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String sql = "UPDATE spaces SET name = ?, description = ?, policies = ?, type = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, space.getName());
            pstmt.setString(2, space.getDescription());
            pstmt.setString(3, space.getPolicies());
            pstmt.setString(4, space.getType());
            pstmt.setInt(5, space.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean deleteSpace(int id) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String sql = "DELETE FROM spaces WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public Space getSpace(int id) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String sql = "SELECT * FROM spaces WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Space(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("policies"),
                        rs.getInt("manager_id"),
                        rs.getString("type"),
                        rs.getInt("price"),
                        rs.getInt("tail")
                );
            }
        }
        return null;
    }
}
