package com.workpal.repository;

import com.workpal.model.User;
import com.workpal.util.JdcbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    public void saveUser(User user) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String query = "INSERT INTO users (name, email, password, address, phone) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getName());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getAddress());
        statement.setString(5, user.getPhone());
        statement.executeUpdate();

        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            int userId = rs.getInt(1);

            String roleQuery = switch (user.getRole().toLowerCase()) {
                case "admin" -> "INSERT INTO admins (user_id) VALUES (?)";
                case "manager" -> "INSERT INTO managers (user_id) VALUES (?)";
                case "member" -> "INSERT INTO members (user_id) VALUES (?)";
                default -> throw new SQLException("Invalid role");
            };

            PreparedStatement roleStatement = connection.prepareStatement(roleQuery);
            roleStatement.setInt(1, userId);
            roleStatement.executeUpdate();
        }
    }

    public User getUserByEmail(String email) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String query = "SELECT * FROM users WHERE email = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("phone");

            String role = getUserRole(id, connection);

            return new User(id, name, email, password, address, phone, role);
        }
        return null;
    }

    private String getUserRole(int userId, Connection connection) throws SQLException {
        String roleQuery = "SELECT 'admin' AS role FROM admins WHERE user_id = ? " +
                "UNION ALL SELECT 'manager' FROM managers WHERE user_id = ? " +
                "UNION ALL SELECT 'member' FROM members WHERE user_id = ?";

        PreparedStatement roleStatement = connection.prepareStatement(roleQuery);
        roleStatement.setInt(1, userId);
        roleStatement.setInt(2, userId);
        roleStatement.setInt(3, userId);
        ResultSet roleResultSet = roleStatement.executeQuery();

        if (roleResultSet.next()) {
            return roleResultSet.getString("role");
        }
        return "unknown";
    }
}
