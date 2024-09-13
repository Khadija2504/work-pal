package com.workpal.repository.implementInterfaces;

import com.workpal.model.Service;
import com.workpal.model.SubsService;
import com.workpal.repository.interfaces.SubscriptionInterface;
import com.workpal.model.Subscription;
import com.workpal.model.User;
import com.workpal.service.ServiceService;
import com.workpal.service.SessionUser;
import com.workpal.util.JdcbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionRepository implements SubscriptionInterface {
    @Override
    public void saveSubscription(Subscription subs, SubsService subsService) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String query = "INSERT INTO subscriptions (name, description, type, price, manager_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

        statement.setString(1, subs.getName());
        statement.setString(2, subs.getDescription());
        statement.setString(3, subs.getType());
        statement.setInt(4, subs.getPrice());
        statement.setInt(5, subs.getManager_id());
        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int subs_id = generatedKeys.getInt(1);

            String qry = "INSERT INTO subs_services (service_id, manager_id, subs_id) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(qry);
            stmt.setInt(1, subsService.getService_id());
            stmt.setInt(2, subs.getManager_id());
            stmt.setInt(3, subs_id);
            stmt.executeUpdate();
        }
    }


    @Override
    public List<Subscription> getAllSubscriptions() throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        User loggedInUser = SessionUser.getLoggedInUser();

        String sql = "SELECT s.id AS subscription_id, s.name AS subscription_name, s.description AS subscription_description, " +
                "s.type, s.price, s.manager_id, sv.id AS service_id, sv.name AS service_name, sv.description AS service_description " +
                "FROM subscriptions s " +
                "JOIN subs_services ss ON s.id = ss.subs_id " +
                "JOIN services sv ON ss.service_id = sv.id " +
                "WHERE s.manager_id = ?";

        List<Subscription> subscriptions = new ArrayList<>();
        Map<Integer, Subscription> subscriptionMap = new HashMap<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, loggedInUser.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int subscriptionId = rs.getInt("subscription_id");

                    Subscription subscription = subscriptionMap.get(subscriptionId);
                    if (subscription == null) {
                        subscription = new Subscription(
                                subscriptionId,
                                rs.getString("subscription_name"),
                                rs.getString("subscription_description"),
                                rs.getString("type"),
                                rs.getInt("price"),
                                rs.getInt("manager_id")
                        );
                        subscriptionMap.put(subscriptionId, subscription);
                        subscriptions.add(subscription);
                    }

                    Service service = new Service(
                            rs.getInt("service_id"),
                            rs.getString("service_name"),
                            rs.getString("service_description"),
                            loggedInUser.getId()
                    );
                    subscription.getServices().add(service);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching subscriptions with services", e);
        }

        return subscriptions;
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
