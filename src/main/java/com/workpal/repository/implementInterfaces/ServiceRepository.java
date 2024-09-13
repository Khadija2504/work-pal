package com.workpal.repository.implementInterfaces;

import com.workpal.repository.interfaces.ServiceInterface;
import com.workpal.model.Service;
import com.workpal.model.User;
import com.workpal.service.SessionUser;
import com.workpal.util.JdcbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepository implements ServiceInterface {
    @Override
    public void saveService(Service service) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String query = "INSERT INTO services (name, description, manager_id) VALUES (?, ?, ?) ";
        PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, service.getName());
        statement.setString(2, service.getDescription());
        statement.setInt(3, service.getManager_id());
        statement.executeUpdate();
    }

    @Override
    public List<Service> getAllServices() throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        User loggedInUser = SessionUser.getLoggedInUser();
        String sql = "SELECT * FROM services WHERE manager_id = ?";
        List<Service> services = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, loggedInUser.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    services.add(new Service(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getInt("manager_id")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching services", e);
        }

        return services;
    }

    @Override
    public boolean updateService(Service service) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String sql = "UPDATE services SET name = ?, description = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, service.getName());
            pstmt.setString(2, service.getDescription());
            pstmt.setInt(3, service.getId());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public boolean deleteService(int id) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String sql = "DELETE FROM services WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
