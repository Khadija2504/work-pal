package com.workpal.repository;

import com.workpal.interfaces.SpaceInterface;
import com.workpal.model.Space;
import com.workpal.model.User;
import com.workpal.service.SessionManager;
import com.workpal.util.JdcbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

public class SpaceRepository implements SpaceInterface {
    public void saveSpace(Space space) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String query = "INSERT INTO spaces (name, description, manager_id, policies, type) VALUES (?, ?, ?, ?, ?) ";
        PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, space.getName());
        statement.setString(2, space.getDescription());
        statement.setInt(3, space.getManager_id());
        statement.setString(4, space.getPolicies());
        statement.setString(5, space.getType());
        statement.executeUpdate();
    }
    public List<Space> getAllSpaces() throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        User loggedInUser = SessionManager.getLoggedInUser();
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
                            rs.getString("type")
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
}
