package com.workpal.repository.implementInterfaces;

import com.workpal.model.*;
import com.workpal.repository.interfaces.SubsMemberInterface;
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

public class SubsMemberRepository implements SubsMemberInterface {
    @Override
    public void saveReservation(SubsMember subsMember, SubsPayment payment) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String query = "INSERT INTO subs_members (member_id, subscription_id) VALUES (?, ?) ";
        PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setInt(1, subsMember.getMember_id());
        statement.setInt(2, subsMember.getSubscription_id());
        statement.executeUpdate();
        String sql = "INSERT INTO subs_payments (member_id, card_info, subs_id, payment_type) VALUES  (?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, payment.getMember_id());
        stmt.setString(2, payment.getCard_info());
        stmt.setInt(3, payment.setSubs_id());
        stmt.setString(4, payment.getPayment_type());
        stmt.executeUpdate();
    }

    @Override
    public List<Subscription> getAllSubsMembers() throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        User loggedInUser = SessionUser.getLoggedInUser();

        String sql = "SELECT s.id AS subscription_id, s.name, s.description, s.price, s.space_id, s.manager_id, s.type, " +
                "sm.member_id, sm.availability " +
                "FROM subscriptions s " +
                "JOIN subs_members sm ON s.id = sm.subscription_id " +
                "WHERE sm.member_id = ? ";

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
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getString("type"),
                                rs.getInt("price"),
                                rs.getInt("space_id"),
                                rs.getInt("manager_id")
                        );
                        subscriptionMap.put(subscriptionId, subscription);
                        subscriptions.add(subscription);
                    }

                    SubsMember subsMember = new SubsMember(
                            loggedInUser.getId(),
                            subscriptionId,
                            rs.getInt("member_id"),
                            rs.getBoolean("availability") ? "Available" : "Unavailable"
                    );

                    subscription.getSubsMembers().add(subsMember);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching subscriptions with members: " + e.getMessage(), e);
        }

        return subscriptions;
    }


    @Override
    public boolean deleteSubsMember(int id) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String sql = "DELETE FROM subs_members WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public List<Subscription> getAllSubs() throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String sql = "SELECT s.id AS subscription_id, s.name AS subscription_name, s.description AS subscription_description, " +
                "s.type, s.price, s.space_id, s.manager_id, sv.id AS service_id, sv.name AS service_name, sv.description AS service_description " +
                "FROM subscriptions s " +
                "LEFT JOIN subs_services ss ON s.id = ss.subs_id " +
                "LEFT JOIN services sv ON ss.service_id = sv.id " +
                "ORDER BY s.id ASC";

        List<Subscription> subscriptions = new ArrayList<>();
        Map<Integer, Subscription> subscriptionMap = new HashMap<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

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
                            rs.getInt("space_id"),
                            rs.getInt("manager_id")
                    );
                    subscriptionMap.put(subscriptionId, subscription);
                    subscriptions.add(subscription);
                }

                int serviceId = rs.getInt("service_id");
                if (serviceId > 0) {
                    Service service = new Service(
                            serviceId,
                            rs.getString("service_name"),
                            rs.getString("service_description"),
                            rs.getInt("manager_id")
                    );
                    subscription.getServices().add(service);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching subscriptions with service: " + e.getMessage(), e);
        }

        return subscriptions;
    }


}
