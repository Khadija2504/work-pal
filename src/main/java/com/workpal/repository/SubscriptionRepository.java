package com.workpal.repository;

import com.workpal.interfaces.SubscriptionInterface;
import com.workpal.model.Event;
import com.workpal.model.Subscription;
import com.workpal.model.User;
import com.workpal.service.SessionManager;
import com.workpal.util.JdcbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionRepository implements SubscriptionInterface {
    @Override
    public void saveSubscription(Subscription subs) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String query = "INSERT INTO subscriptions (name, description, type, price, manager_id) VALUES ( ?, ?, ?, ?, ?) ";
        PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, subs.getName());
        statement.setString(2, subs.getDescription());
        statement.setString(3, subs.getType());
        statement.setInt(4, subs.getPrice());
        statement.setInt(5, subs.getManager_id());
        statement.executeUpdate();
    }

    @Override
    public List<Subscription> getAllSubscriptions() throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        User loggedInUser = SessionManager.getLoggedInUser();
        String sql = "SELECT * FROM subscriptions WHERE manager_id = ?";
        List<Subscription> subs = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, loggedInUser.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    subs.add(new Subscription(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("type"),
                            rs.getInt("price"),
                            rs.getInt("manager_id")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching subscriptions", e);
        }

        return subs;
    }

    @Override
    public boolean updateSubscription(Subscription subs) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String sql = "UPDATE subscriptions SET name = ?, description = ?, type = ?, price = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, subs.getName());
            pstmt.setString(2, subs.getDescription());
            pstmt.setString(3, subs.getType());
            pstmt.setInt(4, subs.getPrice());
            pstmt.setInt(5, subs.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public boolean deleteSubscription(int id) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String sql = "DELETE FROM subscriptions WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
