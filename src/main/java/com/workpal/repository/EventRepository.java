package com.workpal.repository;

import com.workpal.interfaces.EventInterface;
import com.workpal.model.Event;
import com.workpal.util.JdcbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        return List.of();
    }

    @Override
    public boolean updateEvent(Event event) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteEvent(int id) throws SQLException {
        return false;
    }
}
