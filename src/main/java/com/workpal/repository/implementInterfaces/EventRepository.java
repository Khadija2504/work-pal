package com.workpal.repository.implementInterfaces;

import com.workpal.repository.interfaces.EventInterface;
import com.workpal.model.Event;
import com.workpal.model.User;
import com.workpal.service.SessionUser;
import com.workpal.util.JdcbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventRepository implements EventInterface {
    @Override
    public void saveEvent(Event event) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String query = "INSERT INTO events (name, description, date, type, location, policies, places_num, manager_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
        PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, event.getName());
        statement.setString(2, event.getDescription());
        statement.setDate(3, event.getDate());
        statement.setString(4, event.getType());
        statement.setString(5, event.getLocation());
        statement.setString(6, event.getPolicies());
        statement.setInt(7, event.getPlaces_num());
        statement.setInt(8, event.getManager_id());
        statement.executeUpdate();
    }

    @Override
    public List<Event> getAllEvents() throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        User loggedInUser = SessionUser.getLoggedInUser();
        String sql = "SELECT * FROM events WHERE manager_id = ?";
        List<Event> events = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, loggedInUser.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    events.add(new Event(
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("location"),
                            rs.getDate("date"),
                            rs.getString("type"),
                            rs.getString("policies"),
                            rs.getInt("places_num"),
                            rs.getInt("manager_id"),
                            rs.getInt("id")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching events", e);
        }

        return events;
    }

    @Override
    public boolean updateEvent(Event event) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String sql = "UPDATE events SET name = ?, description = ?, date = ? , type = ?, location = ? , policies = ?, places_num = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, event.getName());
            pstmt.setString(2, event.getDescription());
            pstmt.setDate(3, event.getDate());
            pstmt.setString(4, event.getType());
            pstmt.setString(5, event.getLocation());
            pstmt.setString(6, event.getPolicies());
            pstmt.setInt(7, event.getPlaces_num());
            pstmt.setInt(8, event.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public boolean deleteEvent(int id) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String sql = "DELETE FROM events WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
