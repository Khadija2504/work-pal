package com.workpal.interfaces;

import com.workpal.model.Event;

import java.sql.SQLException;
import java.util.List;

public interface EventInterface {
    void saveEvent(Event event) throws SQLException;
    List<Event> getAllEvents() throws SQLException;
    boolean updateEvent(Event event) throws SQLException;
    boolean deleteEvent(int id) throws SQLException;
}
