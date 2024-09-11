package com.workpal.repository;

import com.workpal.interfaces.UserInterface;
import com.workpal.model.User;
import com.workpal.util.JdcbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements UserInterface {

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
                case "manager" -> "INSERT INTO managers (name, email, password, address, phone, user_id, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
                case "member" -> "INSERT INTO members (name, email, password, address, phone, user_id, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
                default -> throw new SQLException("Invalid role");
            };
            PreparedStatement statementTow = connection.prepareStatement(roleQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            statementTow.setString(1, user.getName());
            statementTow.setString(2, user.getEmail());
            statementTow.setString(3, user.getPassword());
            statementTow.setString(4, user.getAddress());
            statementTow.setString(5, user.getPhone());
            statementTow.setInt(6, userId);
            statementTow.setString(7, user.getRole());
            statementTow.executeUpdate();
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

    public String getUserRole(int userId, Connection connection) throws SQLException {
        String roleQuery = "SELECT 'admin' AS role FROM admin WHERE user_id = ? " +
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
